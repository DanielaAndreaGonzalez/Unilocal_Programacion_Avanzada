package co.edu.uniquindio.UniLocal.servicios.interfaces;

import co.edu.uniquindio.UniLocal.dto.ItemComentarioDTO;
import co.edu.uniquindio.UniLocal.dto.RegistroComentarioDTO;
import co.edu.uniquindio.UniLocal.dto.RespuestaComentarioDTO;

import java.util.List;

public interface ComentarioServicio {

    String crearComentario(RegistroComentarioDTO registroComentarioDTO) throws Exception;

    List<ItemComentarioDTO> listarComentariosNegocio(String negocioId);

    void responderComentario(RespuestaComentarioDTO respuestaComentarioDTO);

    double calcularPromedioCalificaciones(String codigoNegocio);
}