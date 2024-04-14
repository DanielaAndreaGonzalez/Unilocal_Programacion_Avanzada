package co.edu.uniquindio.UniLocal.servicios.interfaces;

import co.edu.uniquindio.UniLocal.dto.ItemComentarioDTO;
import co.edu.uniquindio.UniLocal.dto.RegistroComentarioDTO;

public interface ComentarioServicio {

    String crearComentario(RegistroComentarioDTO registroComentarioDTO) throws Exception;

    void listarComentariosNegocio();

    void responderComentario();

    void calcularPromedioCalificaciones();
}