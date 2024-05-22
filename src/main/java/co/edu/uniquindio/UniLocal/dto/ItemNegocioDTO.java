package co.edu.uniquindio.UniLocal.dto;

import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;

public record ItemNegocioDTO(
        String codigo,

        String codigCliente,
        String nombre,
        String descripcion,
        String tipoNegocio,

        String imagenDestacada,

        Ubicacion ubicacion,

        double calificacionPromedio,

       String estadoNegocio

) {
}
