package co.edu.uniquindio.UniLocal.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        @NotBlank
        String token
) {
}
