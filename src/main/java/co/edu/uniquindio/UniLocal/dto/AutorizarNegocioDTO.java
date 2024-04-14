package co.edu.uniquindio.UniLocal.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record AutorizarNegocioDTO(
        @NotBlank String codigoNegocio,
        String nombre,
        String email,
        String observacion,
        String moderadorId,
        Date fechaAccion
) {
}
