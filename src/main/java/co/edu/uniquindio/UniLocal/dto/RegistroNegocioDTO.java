package co.edu.uniquindio.UniLocal.dto;

import co.edu.uniquindio.UniLocal.entidades.Horario;
import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;

import java.util.List;

public record RegistroNegocioDTO (
    String nombre,
    String descripcion,
    List<String> imagenes,
    List<Horario> horarios,
    List<String> telefonos,
    Ubicacion ubicacion,
    TipoNegocio tipoNegocio,
    String codigoCliente
){

}

