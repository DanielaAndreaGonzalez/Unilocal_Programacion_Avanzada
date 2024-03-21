package co.edu.uniquindio.UniLocal.dto;

import java.util.List;

public record RegistroNegocioDTO (
    String nombre,
    String descripcion,
    String imagen,
    List<String> horario,
    List<String> telefono,
    String categoria
){}

