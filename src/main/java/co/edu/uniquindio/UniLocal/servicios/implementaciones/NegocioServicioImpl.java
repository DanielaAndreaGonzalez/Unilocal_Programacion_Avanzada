package co.edu.uniquindio.UniLocal.servicios.implementaciones;
import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.UniLocal.repositorio.NegocioRepo;
import co.edu.uniquindio.UniLocal.servicios.interfaces.NegocioServicio;
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
                .categoria(registroNegocioDTO.categoria())
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
        negocio.setCategoria(actualizarNegocioDTO.categoria());
        negocio.setNombre(actualizarNegocioDTO.nombre());

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
