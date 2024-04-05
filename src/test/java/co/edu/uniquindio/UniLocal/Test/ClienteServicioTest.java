package co.edu.uniquindio.UniLocal.Test;

import co.edu.uniquindio.UniLocal.dto.EmailDTO;
import co.edu.uniquindio.UniLocal.dto.RegistroUsuarioDTO;
import co.edu.uniquindio.UniLocal.servicios.implementaciones.EmailServicioImpl;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private EmailServicio emailServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Test
    public void emailTest() throws Exception{

        emailServicio.enviarCorreo(new EmailDTO(
                "Prueba",
                "Prueba desde UniLocal ",
                "narvaezkevin82@gmail.com"
        ));
    }

    @Test
    public void registrarTest() throws Exception {
//Se crea un objeto de tipo RegistroClienteDTO
        RegistroUsuarioDTO registroClienteDTO = new RegistroUsuarioDTO(
                "Valentina",
                "mi foto",
                "valen",
                "valen@email.com",
                "vale123456",
                "Armenia"
        );
//Se registra el cliente
        String codigo = clienteServicio.registrarsCliente(registroClienteDTO);
        Assertions.assertNotNull(codigo);
    }


}
