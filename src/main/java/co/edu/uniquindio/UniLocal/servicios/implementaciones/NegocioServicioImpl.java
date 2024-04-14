package co.edu.uniquindio.UniLocal.servicios.implementaciones;
import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.UniLocal.repositorio.NegocioRepo;
import co.edu.uniquindio.UniLocal.servicios.interfaces.NegocioServicio;
import co.edu.uniquindio.UniLocal.utils.NegocioUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class NegocioServicioImpl implements NegocioServicio {

    private final NegocioRepo negocioRepo;

    public NegocioServicioImpl(NegocioRepo negocioRepo) {
        this.negocioRepo = negocioRepo;
    }

    @Override
    public String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception {

        Negocio negocio = Negocio
                .builder()
                .nombre(registroNegocioDTO.nombre())
                .descripcion(registroNegocioDTO.descripcion())
                .imagen(registroNegocioDTO.imagen())
                .horario(registroNegocioDTO.horario())
                .telefono(registroNegocioDTO.telefono())
                .comentarios(registroNegocioDTO.comentarios())
                .calificacion(registroNegocioDTO.calificacion())
                .estado(registroNegocioDTO.estado())
                .ubicacion(registroNegocioDTO.ubicacion())
                .tipoNegocio(registroNegocioDTO.tipoNegocio())
                .build();
        //Se guarda en la base de datos y obtenemos el objeto registrado
        Negocio negocioGuardado = negocioRepo.save(negocio);
        //Retornamos el id (código) del cliente registrado
        return negocioGuardado.getCodigo();
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws ResourceNotFoundException {
        Negocio negocio = obtenerNegocioPorId(actualizarNegocioDTO.id());
        negocio.setImagen(actualizarNegocioDTO.imagen());
        negocio.setDescripcion(actualizarNegocioDTO.descripcion());
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setHorario(actualizarNegocioDTO.horario());
        negocio.setTelefono(actualizarNegocioDTO.telefono());
        negocio.setUbicacion(actualizarNegocioDTO.ubicacion());
        negocio.setTipoNegocio(actualizarNegocioDTO.tipoNegocio());

        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino
        //que actualiza el que ya existe
        negocioRepo.save(negocio);
    }

    @Override
    public void eliminarNegocio(String idNegocio)throws ResourceNotFoundException {
        //Obtenemos el cliente que se quiere eliminar y le asignamos el estado
        //inactivo
        Negocio negocio = obtenerNegocioPorId(idNegocio) ;
        negocio.setEstado(EstadoNegocio.INACTIVO);
        negocioRepo.save(negocio);
    }

    @Override
    public void obtenerNegocio() {

    }

    @Override
    public void filtrarPorEstado() {

    }

    @Override
    public void buscarNegocios() {

    }

    @Override
    public void listarNegociosUsuario() {

    }

    @Override
    public void listarNegociosPorEstado() {

    }

    @Override
    public void cambiarEstado() {

    }

    @Override
    public void registrarRevision() {

    }

    @Override
    public NegocioDTO obtenerNegocioPorNombre(String nombreNegocio) throws ResourceNotFoundException {
        Optional<Negocio> negocioEncontrado = negocioRepo.findByNombre(nombreNegocio);
        if (negocioEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio con nombre: " + nombreNegocio);
        }
        return NegocioUtils.convertirANegocioDTO(negocioEncontrado.get());
    }

    @Override
    public NegocioDTO obtenerNegocioPorTipoNegocio(TipoNegocio tipoNegocio) throws ResourceNotFoundException {
        Optional<Negocio> negocioEncontrado = negocioRepo.findByTipoNegocio(tipoNegocio);
        if (negocioEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio con tipoNegocio: " + tipoNegocio);
        }
        return NegocioUtils.convertirANegocioDTO(negocioEncontrado.get());
    }

    @Override
    public NegocioDTO obtenerNegocioPorUbicacion(Ubicacion ubicacion) throws ResourceNotFoundException {
        Optional<Negocio> negocioEncontrado = negocioRepo.findByUbicacion(ubicacion);
        if (negocioEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio ");
        }
        return NegocioUtils.convertirANegocioDTO(negocioEncontrado.get());
    }

    private Negocio obtenerNegocioPorId(String idNegocio) throws ResourceNotFoundException {
        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio con id: " + idNegocio);
        }
        //Obtenemos el cliente que se quiere eliminar y le asignamos el estado
        //inactivo
        return optionalNegocio.get();

    }
}
