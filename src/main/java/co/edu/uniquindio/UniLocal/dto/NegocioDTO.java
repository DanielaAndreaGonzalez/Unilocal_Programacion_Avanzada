package co.edu.uniquindio.UniLocal.dto;

import co.edu.uniquindio.UniLocal.documentos.Comentario;
import co.edu.uniquindio.UniLocal.entidades.Horario;
import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;

import java.util.List;
public record NegocioDTO(
         String codigo,
         String nombre,
         String descripcion,
         List<String> imagenes,
         List<Horario>horarios,
         List<String> telefonos,
         List<Comentario> comentarios,
         EstadoNegocio estado,
         Ubicacion ubicacion,

         TipoNegocio tipoNegocio
) {

}
