package co.edu.uniquindio.UniLocal.documentos;

import co.edu.uniquindio.UniLocal.entidades.Cuenta;
import co.edu.uniquindio.UniLocal.enums.EstadoRegistro;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("moderador")
@Getter
@Setter
public class Moderador extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String cedula;
    private String nickname;
    private String fotoPerfil;
    private List<String> telefono;
    private String ciudad;


    //constructor con todos los argumentos
    public Moderador(String email, String password, String nombre, EstadoRegistro estado, String codigo, String cedula, String nickname, String fotoPerfil, List<String> telefono, String ciudad) {
        super(email, password, nombre, estado);
        this.codigo = codigo;
        this.cedula = cedula;
        this.nickname = nickname;
        this.fotoPerfil = fotoPerfil;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public Moderador()
    {
        super();
    }
}
