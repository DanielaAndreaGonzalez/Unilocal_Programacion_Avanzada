package co.edu.uniquindio.UniLocal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ActualizacionUsuarioDTO(
       @NotBlank String id,
       //Ejemplo de restriccion numerica
       //@Max(100) @Min(0) int edad
       @NotBlank @Length(max = 100) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank @Email String email,
        @NotBlank String ciudadResidencia
) {
}