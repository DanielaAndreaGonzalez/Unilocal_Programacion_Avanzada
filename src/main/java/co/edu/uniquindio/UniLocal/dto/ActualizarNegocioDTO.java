package co.edu.uniquindio.UniLocal.dto;

import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarNegocioDTO (
        @NotBlank String id,
        //Ejemplo de restriccion numerica
        //@Max(100) @Min(0) int edad
        @NotBlank @Length(max = 100) String nombre,

        @NotBlank @Length(max = 2000) String descripcion,

        @NotBlank String imagen,

        @NotBlank List<String> horario,
        @NotBlank List<String> telefono,
        @NotBlank Ubicacion ubicacion,
        @NotBlank TipoNegocio tipoNegocio
){
}
