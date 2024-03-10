package co.edu.uniquindio.UniLocal.documentos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("negocios")
@Getter
@Setter
public class Negocio {

    private String codigo;
    private String nombre;

}