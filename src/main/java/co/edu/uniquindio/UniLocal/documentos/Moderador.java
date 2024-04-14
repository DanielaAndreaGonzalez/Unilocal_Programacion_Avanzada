package co.edu.uniquindio.UniLocal.documentos;

import co.edu.uniquindio.UniLocal.entidades.Cuenta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("moderadores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Moderador extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String cedula;
    private String nickname;
    private String fotoPerfil;
    private List<String> telefono;
    private String ciudad;



}
