package co.edu.uniquindio.UniLocal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String correo,
        @NotBlank @Email String password
) {
}
