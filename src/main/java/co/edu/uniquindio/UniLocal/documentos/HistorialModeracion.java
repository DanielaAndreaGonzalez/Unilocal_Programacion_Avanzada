package co.edu.uniquindio.UniLocal.documentos;

import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "historialModeracion")
@Getter
@Setter
public class HistorialModeracion {
    @Id
    private String id;

    private String lugarId;
    private String moderadorId;
    private Date fechaAccion;
    private EstadoNegocio estadoNegocio;
    private String razonRechazo;

    //constructor con todo
    public HistorialModeracion(String lugarId, String moderadorId, Date fechaAccion, EstadoNegocio estadoNegocio, String razonRechazo) {}
}
