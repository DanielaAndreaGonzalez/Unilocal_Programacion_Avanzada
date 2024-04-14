package co.edu.uniquindio.UniLocal.dto;

import java.util.Date;

public record RegistroComentarioDTO(
        String codigoComentario,
        String mensaje,
        String respuesta,
        String clienteId,
        Date fechaRegistro,
        int calificacion
)
{}
