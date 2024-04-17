package co.edu.uniquindio.UniLocal.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RecuperacionPasswordDTO(
        @NotBlank String codigoSeguridad,
        @NotBlank @Length(min=7) String nuevaPassword
) {
    
}