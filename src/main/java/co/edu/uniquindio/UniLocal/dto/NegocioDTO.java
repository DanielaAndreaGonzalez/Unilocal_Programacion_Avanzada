package co.edu.uniquindio.UniLocal.dto;

import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import lombok.NoArgsConstructor;

import java.util.List;
public record NegocioDTO(
         String codigo,
         String nombre,
         String descripcion,
         String imagen,
         List<String>horario,
         List<String> telefono,
         List<String> comentario,
         String calificacion,
         EstadoNegocio estado,
         Ubicacion ubicacion,

         TipoNegocio tipoNegocio
) {

}
