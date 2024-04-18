package co.edu.uniquindio.UniLocal.Test;

import co.edu.uniquindio.UniLocal.documentos.Comentario;
import co.edu.uniquindio.UniLocal.dto.ItemComentarioDTO;
import co.edu.uniquindio.UniLocal.dto.RegistroComentarioDTO;
import co.edu.uniquindio.UniLocal.dto.RespuestaComentarioDTO;
import co.edu.uniquindio.UniLocal.repositorio.ClienteRepo;
import co.edu.uniquindio.UniLocal.repositorio.ComentarioRepo;
import co.edu.uniquindio.UniLocal.repositorio.NegocioRepo;
import co.edu.uniquindio.UniLocal.servicios.implementaciones.ComentarioServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class ComentarioServicioTest {

    @Autowired
    private ComentarioServicioImpl comentarioServicio;

    @Autowired
    private NegocioRepo negocioRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Test
    public void testCrearComentario() throws Exception {
        // Preparar datos para el test
        RegistroComentarioDTO registroComentarioDTO = new RegistroComentarioDTO(
                "Este es un comentario",
                "Cliente4",
                "Negocio3",
                new Date(),
                5
        );
        // Ejecutar el método a probar
        String codigoComentario = comentarioServicio.crearComentario(registroComentarioDTO);
        // Verificar el resultado
        Assertions.assertNotNull(codigoComentario);

    }

    @Test
    public void testResponderComentario() {
        // Preparar datos para el test
        RespuestaComentarioDTO respuestaComentarioDTO = new RespuestaComentarioDTO(
                "6620a93bb91fee7a5ce040dc",
                "Respuesta a comentario"
        );


        // Ejecutar el método a probar
        comentarioServicio.responderComentario(respuestaComentarioDTO);

        // Verificar el resultado
        Comentario comentarioActualizado = comentarioRepo.findById("6620a93bb91fee7a5ce040dc").orElse(null);
        Assertions.assertNotNull(comentarioActualizado);
    }

     @Test
    public void testListarComentariosNegocio() {
         String negocioId = "Negocio3";
         List<ItemComentarioDTO> comentariosNegocio = comentarioServicio.listarComentariosNegocio(negocioId);

         // Verificar el resultado
         Assertions.assertEquals(2, comentariosNegocio.size());

    }
}