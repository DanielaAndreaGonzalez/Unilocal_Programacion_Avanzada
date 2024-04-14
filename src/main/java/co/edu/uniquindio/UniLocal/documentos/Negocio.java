package co.edu.uniquindio.UniLocal.documentos;

import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.EstadoRegistro;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("negocios")
@Getter
@Setter
public class Negocio {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nombre;
    private String descripcion;
    private String imagen;
    private List<String> horario;
    private List<String> telefono;
    private List<String> comentarios;
    private String calificacion;
    private EstadoNegocio estado;
    private Ubicacion ubicacion;
    private TipoNegocio tipoNegocio;
    @Builder
    public Negocio(String codigo, String nombre,String descripcion,String imagen,List<String> horario,List<String> telefono
            ,List<String> comentarios,String calificacion, EstadoNegocio estado, Ubicacion ubicacion,TipoNegocio tipoNegocio) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.horario = horario;
        this.telefono = telefono;
        this.comentarios = comentarios;
        this.calificacion = calificacion;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.tipoNegocio = tipoNegocio;
    }
}