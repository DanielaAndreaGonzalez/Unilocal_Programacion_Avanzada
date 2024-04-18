package co.edu.uniquindio.UniLocal.Test;

import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.AutorizarNegocioDTO;
import co.edu.uniquindio.UniLocal.dto.NegocioDTO;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.AutorizacionException;
import co.edu.uniquindio.UniLocal.repositorio.NegocioRepo;
import co.edu.uniquindio.UniLocal.servicios.implementaciones.ModeradorServicioImpl;
import co.edu.uniquindio.UniLocal.servicios.implementaciones.NegocioServicioImpl;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ModeradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ModeradorServicioTest {

    @Autowired
    private ModeradorServicioImpl moderadorServicio;

    @Autowired
    private NegocioRepo negocioRepo;
    @Test
    public void testAutorizarNegocio() throws AutorizacionException {
        // Preparar datos para el test
        AutorizarNegocioDTO autorizarNegocioDTO = new AutorizarNegocioDTO(
                "Negocio5",
                "Cliente2",
                "Se autoriza negocio",
                "moderador3",
                new Date()
        );

        // Ejecutar el método a probar
        assertDoesNotThrow(() -> moderadorServicio.autorizarNegocio(autorizarNegocioDTO));
    }

    @Test
    public void testRechazarNegocio() throws AutorizacionException {
        // Preparar datos para el test
        AutorizarNegocioDTO autorizarNegocioDTO = new AutorizarNegocioDTO(
                "Negocio3",
                "Cliente1",
                "se rechaza negocio",
                "moderador1",
                new Date()
        );

        // Ejecutar el método a probar
        assertDoesNotThrow(() -> moderadorServicio.rechazarNegocio(autorizarNegocioDTO));
    }
    @Test
    public void testObtenerLugaresPendientesAutorizar() throws AutorizacionException {
        // Ejecutar el método a probar
        List<NegocioDTO> lugaresPendientes = moderadorServicio.obtenerLugaresPendientesAutorizar();

        // Verificar el resultado
        Assertions.assertEquals(1, lugaresPendientes.size()); // Verificar que se recuperaron los negocios pendientes
    }

}
