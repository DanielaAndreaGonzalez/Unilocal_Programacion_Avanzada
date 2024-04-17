package co.edu.uniquindio.UniLocal.servicios.implementaciones;
import co.edu.uniquindio.UniLocal.documentos.Cliente;
import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.UniLocal.repositorio.ClienteRepo;
import co.edu.uniquindio.UniLocal.repositorio.NegocioRepo;
import co.edu.uniquindio.UniLocal.servicios.interfaces.NegocioServicio;
import co.edu.uniquindio.UniLocal.utils.NegocioUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NegocioServicioImpl implements NegocioServicio {

    private final NegocioRepo negocioRepo;

    private final ClienteRepo clienteRepo;

    public NegocioServicioImpl(NegocioRepo negocioRepo, ClienteRepo clienteRepo) {

        this.negocioRepo = negocioRepo;
        this.clienteRepo = clienteRepo;
    }

    @Override
    public String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception {

        Negocio negocio = Negocio
                .builder()
                .nombre(registroNegocioDTO.nombre())
                .descripcion(registroNegocioDTO.descripcion())
                .imagenes(registroNegocioDTO.imagenes())
                .horarios(registroNegocioDTO.horarios())
                .telefonos(registroNegocioDTO.telefonos())
                .estado(EstadoNegocio.PENDIENTE)
                .ubicacion(registroNegocioDTO.ubicacion())
                .tipoNegocio(registroNegocioDTO.tipoNegocio())
                .codigoCliente(registroNegocioDTO.codigoCliente())
                .build();
        //Se guarda en la base de datos y obtenemos el objeto registrado
        Negocio negocioGuardado = negocioRepo.save(negocio);
        //Retornamos el id (código) del cliente registrado
        return negocioGuardado.getCodigo();
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws ResourceNotFoundException {
        Negocio negocio = obtenerNegocioPorId(actualizarNegocioDTO.id());
        negocio.setImagenes(actualizarNegocioDTO.imagenes());
        negocio.setDescripcion(actualizarNegocioDTO.descripcion());
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setHorarios(actualizarNegocioDTO.horarios());
        negocio.setTelefonos(actualizarNegocioDTO.telefonos());
        negocio.setUbicacion(actualizarNegocioDTO.ubicacion());
        negocio.setTipoNegocio(actualizarNegocioDTO.tipoNegocio());
        negocio.setCodigoCliente(actualizarNegocioDTO.codigoCliente());
        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino
        //que actualiza el que ya existe
        negocioRepo.save(negocio);
    }

    @Override
    public void eliminarNegocio(String idNegocio) throws ResourceNotFoundException {
        //Obtenemos el cliente que se quiere eliminar y le asignamos el estado
        //inactivo
        Negocio negocio = obtenerNegocioPorId(idNegocio) ;
        negocio.setEstado(EstadoNegocio.INACTIVO);
        negocioRepo.save(negocio);
    }

    @Override
    public NegocioDTO obtenerNegocio(String codigo) throws ResourceNotFoundException {
        Negocio negocioEncontrado = obtenerNegocioPorId(codigo);
        return NegocioUtils.convertirANegocioDTO(negocioEncontrado);
    }

    @Override
    public List<NegocioDTO> listarNegocios() {
        List<Negocio> negocios =  negocioRepo.findAll();
        return NegocioUtils.convertirListaNegocioAListaNegocioDto(negocios);
    }

    @Override
    public List<NegocioDTO> listarNegociosUsuario(String codigoCliente) throws Exception{

        Optional <Cliente> clienteoptional =  clienteRepo.findById(codigoCliente);
        if (clienteoptional.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        List<Negocio> negociosUsuario = negocioRepo.findByCodigoCliente(codigoCliente);

        return NegocioUtils.convertirListaNegocioAListaNegocioDto(negociosUsuario);
    }

    @Override
    public  List<NegocioDTO> listarNegociosPorEstado(EstadoNegocio estadoNegocio) throws Exception{

        List<Negocio> negociosEncontrados = negocioRepo.findByEstado(estadoNegocio);
        if (negociosEncontrados.isEmpty()){
            throw new Exception("No se encontraron negocios con dicho estado");
        }
        return NegocioUtils.convertirListaNegocioAListaNegocioDto(negociosEncontrados);
    }

    @Override
    public NegocioDTO obtenerNegocioPorNombre(String nombreNegocio) throws ResourceNotFoundException {
        Optional<Negocio> negocioEncontrado = negocioRepo.findByNombre(nombreNegocio, EstadoNegocio.ACTIVO);
        if (negocioEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio con nombre: " + nombreNegocio);
        }
        return NegocioUtils.convertirANegocioDTO(negocioEncontrado.get());
    }

    @Override
    public List<NegocioDTO> obtenerNegocioPorTipoNegocio(TipoNegocio tipoNegocio) throws ResourceNotFoundException {
        List<Negocio> negociosEncontrado = negocioRepo.findByTipoNegocio(tipoNegocio, EstadoNegocio.ACTIVO);
        if (negociosEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio con tipoNegocio: " + tipoNegocio);
        }
        return NegocioUtils.convertirListaNegocioAListaNegocioDto(negociosEncontrado);
    }

    @Override
    public NegocioDTO obtenerNegocioPorUbicacion(Ubicacion ubicacion) throws ResourceNotFoundException {
        Optional<Negocio> negocioEncontrado = negocioRepo.findByUbicacion(ubicacion, EstadoNegocio.ACTIVO);
        if (negocioEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio con dicha unbicacion");
        }
        return NegocioUtils.convertirANegocioDTO(negocioEncontrado.get());
    }

    @Override
    public Negocio obtenerNegocioPorId(String idNegocio) throws ResourceNotFoundException {
        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);
        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio con id: " + idNegocio);
        }
        return optionalNegocio.get();
    }



}
