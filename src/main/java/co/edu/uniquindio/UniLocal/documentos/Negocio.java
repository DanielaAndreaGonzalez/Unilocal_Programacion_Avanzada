package co.edu.uniquindio.UniLocal.documentos;

import co.edu.uniquindio.UniLocal.entidades.Horario;
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
import java.util.Objects;

@Document("negocios")
@Getter
@Setter
public class Negocio {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nombre;
    private String descripcion;
    private List<String> imagenes;
    private List<Horario> horarios;
    private List<String> telefonos;
    private List<Comentario> comentarios;
    private EstadoNegocio estado;
    private Ubicacion ubicacion;
    private TipoNegocio tipoNegocio;
    @Builder
    public Negocio(String codigo, String nombre,String descripcion,List<String> imagenes,List<Horario> horarios,List<String> telefonos
            ,List<Comentario> comentarios, EstadoNegocio estado, Ubicacion ubicacion,TipoNegocio tipoNegocio) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.horarios = horarios;
        this.telefonos = telefonos;
        this.comentarios = comentarios;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.tipoNegocio = tipoNegocio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Negocio negocio = (Negocio) obj;
        return Objects.equals(codigo, negocio.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}