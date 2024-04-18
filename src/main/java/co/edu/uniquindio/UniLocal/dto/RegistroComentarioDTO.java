package co.edu.uniquindio.UniLocal.dto;

import java.util.Date;

public record RegistroComentarioDTO(
        String mensaje,
        String codigoCliente,
        String codigoNegocio,
        Date fechaRegistro,
        double calificacion
)
{}
