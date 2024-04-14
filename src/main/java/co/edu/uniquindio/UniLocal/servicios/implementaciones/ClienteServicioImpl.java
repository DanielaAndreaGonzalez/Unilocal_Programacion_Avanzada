package co.edu.uniquindio.UniLocal.servicios.implementaciones;

import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.EstadoRegistro;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.AutorizacionException;
import co.edu.uniquindio.UniLocal.excepciones.ExcepcionesGlobales;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.UniLocal.documentos.Cliente;
import co.edu.uniquindio.UniLocal.repositorio.ClienteRepo;
import co.edu.uniquindio.UniLocal.repositorio.NegocioRepo;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.UniLocal.utils.NegocioUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicio {
    private final ClienteRepo clienteRepo;

    private final EmailServicio emailServicio;

    private final NegocioRepo negocioRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo,EmailServicio emailServicio,NegocioRepo negocioRepo){
        this.clienteRepo = clienteRepo;
        this.emailServicio = emailServicio;
        this.negocioRepo = negocioRepo;
    }
    @Override
    public String registrarsCliente(RegistroUsuarioDTO registroUsuarioDTO) throws Exception {

        if(existeNickname(registroUsuarioDTO.nickname())) {
            throw new Exception("El nickname "+registroUsuarioDTO.nickname()+"ya está registrado");
        }

        if( existeEmail(registroUsuarioDTO.email())){
            throw new Exception("El correo ya se encuentra registrado");
        }

        //Se crea el objeto Cliente
        Cliente cliente = new Cliente();
//Se le asignan sus campos
        cliente.setNombre( registroUsuarioDTO.nombre() );
        cliente.setNickname( registroUsuarioDTO.nickname() );
        cliente.setCiudad( registroUsuarioDTO.ciudadResidencia() );
        cliente.setFotoPerfil( registroUsuarioDTO.fotoPerfil() );
        cliente.setEmail( registroUsuarioDTO.email() );

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( registroUsuarioDTO.password() );

        cliente.setPassword(passwordEncriptada);
        cliente.setEstado(EstadoRegistro.ACTIVO);
//Se guarda en la base de datos y obtenemos el objeto registrado
        Cliente clienteGuardado = clienteRepo.save(cliente);
//Retornamos el id (código) del cliente registrado
        return clienteGuardado.getCodigo();
    }

    public void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws ResourceNotFoundException {

        Cliente cliente =  obtenerClientePorIdCuenta(actualizarClienteDTO.id());

        cliente.setNombre( actualizarClienteDTO.nombre() );
        cliente.setFotoPerfil( actualizarClienteDTO.fotoPerfil() );
        cliente.setCiudad( actualizarClienteDTO.ciudadResidencia() );
        cliente.setEmail( actualizarClienteDTO.email() );
        clienteRepo.save(cliente);
    }
    @Override
    public void eliminarCliente(String idCuenta)throws ResourceNotFoundException {
        //Obtenemos el cliente que se quiere eliminar y le asignamos el estado
        //inactivo
        Cliente cliente = obtenerClientePorIdCuenta(idCuenta) ;
        cliente.setEstado(EstadoRegistro.INACTIVO);
        clienteRepo.save(cliente);
    }

    @Override
    public DetalleClienteDTO obtenerCliente(String idCuenta) throws ResourceNotFoundException {
        Cliente cliente =  obtenerClientePorIdCuenta(idCuenta);

        return new DetalleClienteDTO(
                                    cliente.getCodigo(),
                                    cliente.getNombre(),
                                    cliente.getFotoPerfil(),
                                    cliente.getNickname(),
                                    cliente.getEmail(),
                                    cliente.getCiudad()
        );
    }
    @Override
    public List<ItemClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepo.findAll();
        //Creamos una lista de DTO's de clientes
        List<ItemClienteDTO> items = new ArrayList<>();

       /* for(Cliente cliente: clientes){
            items.add(new ItemClienteDTO(
                     cliente.getCodigo(),
                     cliente.getNombre(),
                     cliente.getFotoPerfil(),
                    cliente.getNickname(),
                    cliente.getCiudad()
            ));
        }*/

        clientes.forEach(cliente -> {
            items.add(new ItemClienteDTO(
                    cliente.getCodigo(),
                    cliente.getNombre(),
                    cliente.getFotoPerfil(),
                    cliente.getNickname(),
                    cliente.getCiudad()
            ));
        });
        
        //items = clientes.stream().map(cliente -> new ItemClienteDTO(cliente.getCodigo(), cliente.getNombre(), cliente.getFotoPerfil(), cliente.getNickname(), cliente.getCiudad())).collect(Collectors.toList());

        return items;
    }

    @Override
    public List<NegocioDTO> obtenerNegociosMejorCalificados(TipoNegocio tipoNegocio) throws Exception {
        List<Negocio>  negociosMejorCalificaciones = negocioRepo.findNegociosMejorCalificacion(tipoNegocio);
        if(negociosMejorCalificaciones.isEmpty()){
            throw new Exception("No se encontraron negocios");
        }
        return NegocioUtils.convertirListaNegocioAListaNegocioDto( negociosMejorCalificaciones);
    }

    @Override
    public void eliminarCuenta(String idCuenta) throws ResourceNotFoundException {
        Cliente cliente = obtenerClientePorIdCuenta(idCuenta) ;
        cliente.setEstado(EstadoRegistro.INACTIVO);
        clienteRepo.save(cliente);
    }
    @Override
    public void enviarLinkRecuperacion(String email) throws Exception{
        String asunto = "Recuperar contraseña ";
        String cuerpo = "Hola! has solicitado cambiar tu contraseña, por favor ingresa  a este link  localhost:8080/api/auth/recuperar-contrasena ";
        EmailDTO correoAutorizacion = new EmailDTO(
                asunto,
                cuerpo,
                email
        );
        try {
            emailServicio.enviarCorreo(correoAutorizacion);
        } catch (Exception e) {
            throw new Exception("No se pudo restablecer la contraseña");
        }
    }
    @Override
    public void recuperarPassword(RecuperacionPasswordDTO RecuperacionPasswordDTO) {

    }
    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
    }

    @Override
    public boolean agregarAFavoritos(NegocioFavoritoDTO negocioFavoritoDTO) throws Exception {
        Cliente cliente = clienteRepo.findById(negocioFavoritoDTO.clienteId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Negocio negocio = negocioRepo.findById(negocioFavoritoDTO.negocioId()).orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        if (cliente.getFavoritos() == null) {
            cliente.setFavoritos(new ArrayList<>()); // Inicializa la lista si es nula
        }
        if (!cliente.getFavoritos().contains(negocio)) {
            cliente.getFavoritos().add(negocio);
        }
        clienteRepo.save(cliente);
        return true;
    }

    @Override
    public boolean quitarDeFavoritos(NegocioFavoritoDTO negocioFavoritoDTO) throws Exception {
        Cliente cliente = clienteRepo.findById(negocioFavoritoDTO.clienteId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Negocio negocio = negocioRepo.findById(negocioFavoritoDTO.negocioId()).orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        if (cliente.getFavoritos() != null && !cliente.getFavoritos().isEmpty()) {
            cliente.getFavoritos().remove(negocio);
            clienteRepo.save(cliente);
            return true;
        }
        return false;
    }

    @Override
    public List<NegocioDTO> listarNegociosFavoritos(String clienteId) throws Exception {

        Optional <Cliente> cliente = clienteRepo.findById(clienteId);

        if(cliente.isEmpty())
        {
            throw new Exception("No se encontró el cliente con id"+clienteId);
        }

        if(cliente.get().getFavoritos() == null || cliente.get().getFavoritos().isEmpty())
        {
            throw new Exception("No se encontró el cliente con id"+clienteId);
        }

        List<String> idFavoritos = cliente.get().getFavoritos().stream()
                                .map(Negocio::getCodigo)
                                .collect(Collectors.toList());
        List<Negocio> negociosFavoritos = negocioRepo.findAllById(idFavoritos);

        return negociosFavoritos.stream()
                .map(NegocioUtils::convertirANegocioDTO)
                .collect(Collectors.toList());
    }

    private Cliente obtenerClientePorIdCuenta(String idCuenta)throws ResourceNotFoundException
    {
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCuenta);

        if(optionalCliente.isEmpty())
        {
            throw new ResourceNotFoundException("No se encontró el cliente con id: "+idCuenta);
        }
        //Obtenemos el cliente que se quiere eliminar y le asignamos el estado
        //inactivo
        return optionalCliente.get();
    }
    private boolean existeEmail(String email) {
        //Para la actualización debe validar que el si el cliente cambia su email por otro, este nuevo
        //email no esté asignado a ningún otro cliente
        return clienteRepo.findByEmail(email).isPresent();
    }
    private boolean existeNickname(String nickname){
        return clienteRepo.findByNickname(nickname).isPresent();
    }
}
