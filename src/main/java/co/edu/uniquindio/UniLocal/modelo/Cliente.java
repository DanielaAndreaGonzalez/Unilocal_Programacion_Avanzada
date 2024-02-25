package co.edu.uniquindio.UniLocal.modelo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document("clientes")
@Getter
@Setter
public class Cliente implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String cedula;
    private String nombre;
    private String email;
    private List<String> telefono;

    @Builder
    public Cliente(String cedula, String nombre, String email, List<String> telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

}