package co.edu.uniquindio.UniLocal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InicioSesionDTO(
        @NotBlank String email,
        @NotBlank @Email String password

) {

}