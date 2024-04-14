package co.edu.uniquindio.UniLocal.documentos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("comentarios")
@Getter
@Setter
public class Comentario {

    @Id
    private  String codigo;
    private String mensaje;
    private String respuesta;
    private String clienteId;
    private String fotoCliente;
    private Date fecha;
    private int calificacion;
}
