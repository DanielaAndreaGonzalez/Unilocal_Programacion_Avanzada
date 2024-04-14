package co.edu.uniquindio.UniLocal.dto;

public record ItemComentarioDTO (

    String codigoComentario,
    String mensaje,
    String respuesta,
    String nombrecliente,
    String fotoCliente,
    String fechaFormato,
    int calificacion
    )
{}
