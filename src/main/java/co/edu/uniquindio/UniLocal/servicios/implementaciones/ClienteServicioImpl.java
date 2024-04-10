package co.edu.uniquindio.UniLocal.servicios.implementaciones;

import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.enums.EstadoRegistro;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.UniLocal.documentos.Cliente;
import co.edu.uniquindio.UniLocal.repositorio.ClienteRepo;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ClienteServicio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicio {


    private final ClienteRepo clienteRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo){
        this.clienteRepo = clienteRepo;
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
    public void eliminarCuenta(String idCliente) throws ResourceNotFoundException {

    }
    @Override
    public void iniciariSesion(InicioSesionDTO InicioSesionDTO) {

    }
    @Override
    public void enviarLinkRecuperacion(String email) {

    }
    @Override
    public void recuperarPassword(RecuperacionPasswordDTO RecuperacionPasswordDTO) {

    }
    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
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
