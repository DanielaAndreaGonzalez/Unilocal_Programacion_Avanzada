package co.edu.uniquindio.UniLocal.Test;

import co.edu.uniquindio.UniLocal.dto.RegistroNegocioDTO;
import co.edu.uniquindio.UniLocal.dto.RegistroUsuarioDTO;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.NegocioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class NegocioServicioTest {

    @Autowired
    private NegocioServicio negocioServicio;

    @Test
    public void registrarTest() throws Exception {
//Se crea un objeto de tipo RegistroClienteDTO

        String [] horarios = {"6 am - 1 pm "," 1:30, 9 pm"};
        String [] telefono = {"322445566","314567891"};


        List<String> horarios2 = Arrays.asList(horarios);
        List<String> telefonos2 = Arrays.asList(telefono);

        RegistroNegocioDTO registroNegocioDTO = new RegistroNegocioDTO(

                "Mi cafecito",
                "Café y panadería",
                "miimagen",
                horarios2,
                telefonos2,
                "Cafeteria"
        );
        String codigo = negocioServicio.crearNegocio(registroNegocioDTO);
        Assertions.assertNotNull(codigo);

    }
}
