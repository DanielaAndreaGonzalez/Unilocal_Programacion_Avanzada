package co.edu.uniquindio.UniLocal.dto;

import java.util.List;

public record ItemNegocioPendienteDto(
        String codigoNegocio,
        String codigoCliente,
        String imagenCliente,
        String nombreCliente,
        String nombreNegocio,
        String descripcionNegocio,
        String tipoNegocio,
        List<String> imagenesNegocio
) {
}
