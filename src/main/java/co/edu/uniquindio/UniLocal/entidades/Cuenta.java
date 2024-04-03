package co.edu.uniquindio.UniLocal.entidades;

import co.edu.uniquindio.UniLocal.enums.EstadoRegistro;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cuentas")
@Getter
@Setter
public class Cuenta {
    private String email;
    private String password;
    private String nombre;
    private EstadoRegistro estado;

    @Builder
    public Cuenta(String email, String password, String nombre,EstadoRegistro estado){
        this.email = email;
        this.password = password;
        this.nombre  = nombre;
        this.estado = estado;

    }


    public Cuenta() {

    }
}
