package co.edu.uniquindio.UniLocal.dto;

import co.edu.uniquindio.UniLocal.entidades.Horario;
import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarNegocioDTO (
        @NotBlank String codigo,
        //Ejemplo de restriccion numerica
        //@Max(100) @Min(0) int edad
        @NotBlank @Length(max = 100) String nombre,

        @NotBlank @Length(max = 2000) String descripcion,

        @NotEmpty List<String> imagenes,

        @NotEmpty List<Horario> horarios,
        @NotEmpty List<String> telefonos,
        Ubicacion ubicacion,
        TipoNegocio tipoNegocio,

        String codigoCliente
){
}
