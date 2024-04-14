package co.edu.uniquindio.UniLocal.dto;

import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;

import java.util.List;

public record RegistroNegocioDTO (
    String nombre,
    String descripcion,
    String imagen,
    List<String> horario,
    List<String> telefono,
    List<String> comentarios,
    String calificacion,
    EstadoNegocio estado,
    Ubicacion ubicacion,
    TipoNegocio tipoNegocio
){}

