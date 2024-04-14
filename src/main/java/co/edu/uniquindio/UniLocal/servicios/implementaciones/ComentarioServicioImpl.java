package co.edu.uniquindio.UniLocal.servicios.implementaciones;

import co.edu.uniquindio.UniLocal.documentos.Comentario;
import co.edu.uniquindio.UniLocal.dto.ItemComentarioDTO;
import co.edu.uniquindio.UniLocal.dto.RegistroComentarioDTO;
import co.edu.uniquindio.UniLocal.repositorio.ComentarioRepo;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ComentarioServicio;

public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    //contruc
    public ComentarioServicioImpl(ComentarioRepo comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public String crearComentario(RegistroComentarioDTO registroComentarioDTO) throws Exception{
        Comentario comentarioGuardado = comentarioRepo.save(crearComentarioDesdeItemComentarioDto(registroComentarioDTO));
        return comentarioGuardado.getCodigo();
    }

    private Comentario crearComentarioDesdeItemComentarioDto(RegistroComentarioDTO registroComentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setCodigo(registroComentarioDTO.codigoComentario());
        comentario.setMensaje(registroComentarioDTO.mensaje());
        comentario.setRespuesta(registroComentarioDTO.respuesta());
        comentario.setClienteId(registroComentarioDTO.clienteId());
        comentario.setFecha(registroComentarioDTO.fechaRegistro());
        comentario.setCalificacion(registroComentarioDTO.calificacion());
        return comentario;
    }

    @Override
    public void listarComentariosNegocio() {
        //Daniela tiene la consulta ojo por ojo no vaya a ir a hacer algo sin el cosos de daniela, ojo
        // ella mostro eso muchas veces ojo. esta obsecionada con esa consulta ojo
        //y ella ya lo habia hecho, ojo la cosa es que no le guardo ojo
    }

    @Override
    public void responderComentario() {

    }

    @Override
    public void calcularPromedioCalificaciones() {

    }
}
