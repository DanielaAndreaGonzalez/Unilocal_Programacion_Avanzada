package co.edu.uniquindio.UniLocal.Test;

import co.edu.uniquindio.UniLocal.dto.EmailDTO;
import co.edu.uniquindio.UniLocal.servicios.implementaciones.EmailServicioImpl;
import co.edu.uniquindio.UniLocal.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private EmailServicio emailServicio;

    @Test
    public void emailTest() throws Exception{

        emailServicio.enviarCorreo(new EmailDTO(
                "Prueba",
                "Prueba",
                "danielaandreagonzalezhenao@gmail.com"
        ));

    }

}
