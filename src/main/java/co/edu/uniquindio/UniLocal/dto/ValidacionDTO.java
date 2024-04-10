package co.edu.uniquindio.UniLocal.dto;

import lombok.Getter;
import lombok.Setter;


public record ValidacionDTO (
        String campo,
        String error
)
{
}
