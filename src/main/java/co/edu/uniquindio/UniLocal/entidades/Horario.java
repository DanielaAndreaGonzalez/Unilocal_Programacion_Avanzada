package co.edu.uniquindio.UniLocal.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Horario {
    private String dia;
    private String horaInicio;
    private String horaFin;
}
