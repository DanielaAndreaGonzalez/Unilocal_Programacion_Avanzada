package co.edu.uniquindio.UniLocal.documentos;

import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.EstadoRegistro;
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
    private String categoria;
    private List<String> comentario;
    private String calificacion;
    private EstadoNegocio estado;
    @Builder
    public Negocio(String codigo, String nombre,String descripcion,String imagen,List<String> horario,List<String> telefono
            ,String categoria,List<String> comentario,String calificacion, EstadoNegocio estado) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.horario = horario;
        this.telefono = telefono;
        this.categoria = categoria;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.estado = estado;

    }
}