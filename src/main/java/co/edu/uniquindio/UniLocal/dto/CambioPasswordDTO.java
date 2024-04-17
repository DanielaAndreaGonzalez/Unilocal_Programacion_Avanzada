package co.edu.uniquindio.UniLocal.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CambioPasswordDTO (
       @NotBlank @Length(min=7) String passwordNueva,
        @NotBlank String codigoCliente,
        @NotBlank String token
)
{}