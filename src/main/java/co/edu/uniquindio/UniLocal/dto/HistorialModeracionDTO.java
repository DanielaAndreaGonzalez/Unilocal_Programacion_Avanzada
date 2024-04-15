package co.edu.uniquindio.UniLocal.dto;

import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;

import java.util.Date ;

public record HistorialModeracionDTO(
          String lugarId ,
          String moderadorId ,
          Date fechaAccion ,
          EstadoNegocio estadoNegocio ,
          String observacion
) {
}
