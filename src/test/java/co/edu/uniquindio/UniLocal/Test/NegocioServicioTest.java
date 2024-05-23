package co.edu.uniquindio.UniLocal.Test;

import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.entidades.Horario;
import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.NegocioServicio;
import co.edu.uniquindio.UniLocal.utils.NegocioUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class NegocioServicioTest {

    @Autowired
    private NegocioServicio negocioServicio;

    @Test
    public void testCrearNegocio() throws Exception {
        // Crear el DTO de entrada con datos de ejemplo
        Ubicacion ubicacion = new Ubicacion( 7.4852, -48.66);
        Horario horario = new Horario("Lunes a Viernes", "8:00 am","8:00 pm");


        RegistroNegocioDTO registroNegocioDTO = new RegistroNegocioDTO(
                "Cafetería Central",
                "Una excelente cafetería en el centro",
                new ArrayList<>(), // Suponer lista de imágenes
                List.of(horario),
                List.of("123456789"),
                ubicacion,
                TipoNegocio.CAFE,
                "661c090008b0af2fb61e9ac0");

        // Ejecutar el método a probar
        String codigoNegocio = negocioServicio.crearNegocio(registroNegocioDTO);

        // Assert: Verifica los resultados esperados
        assertNotNull(codigoNegocio);

    }

    @Test
    public void testActualizarNegocio() throws Exception
    {
        Horario horarioLunes = new Horario("Lunes" ,"6 am", "5 pm");
        Horario horario = new Horario("Martes a Domingo" ,"8 am", "9 pm");
        Ubicacion ubicacion = new Ubicacion(4.5896,-74.8524);

        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(
                "662027d6ccd5a10396f1550e",
                "Cafetería Central de Occidente",
                "Una excelente cafetería en el centro",
                List.of("cafe.png"),
                List.of(horarioLunes,horario),
                List.of("3104998521","999888777"),
                ubicacion,
                TipoNegocio.CAFE,
                "661c090008b0af2fb61e9ac0"
        );
        negocioServicio.actualizarNegocio(actualizarNegocioDTO);
        Assertions.assertEquals("Cafetería Central de Occidente",negocioServicio.obtenerNegocio("662027d6ccd5a10396f1550e").nombre());
    }
    @Test
    public void testEliminarNegocio() throws Exception{
        String codigoNegocio = "662027d6ccd5a10396f1550e";
        negocioServicio.eliminarNegocio(codigoNegocio);
        Negocio negocio = negocioServicio.obtenerNegocioPorId(codigoNegocio);
        // Assert: Verificar que el negocio ha sido marcado como inactivo
        assertEquals(EstadoNegocio.INACTIVO, negocio.getEstado());
    }

    @Test
    public void testListarNegocios()
    {
        List<ItemNegocioDTO> negocios = negocioServicio.listarNegocios();
        Assertions.assertEquals(2, negocios.size());
    }


    @Test
    public void testListarNegociosUsuario() throws Exception {
        String codigoCliente = "661c090008b0af2fb61e9ac0";
        List<NegocioDTO> negociosDTO = negocioServicio.listarNegociosUsuario(codigoCliente);
        int numeroEsperadoDeNegocios = 2;
        assertEquals(numeroEsperadoDeNegocios, negociosDTO.size());
    }



}