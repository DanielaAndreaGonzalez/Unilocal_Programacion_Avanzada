package co.edu.uniquindio.UniLocal.Test;

import co.edu.uniquindio.UniLocal.documentos.Cliente;
import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.repositorio.ClienteRepo;
import co.edu.uniquindio.UniLocal.repositorio.NegocioRepo;
import co.edu.uniquindio.UniLocal.servicios.implementaciones.EmailServicioImpl;
import co.edu.uniquindio.UniLocal.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private EmailServicio emailServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AutenticacionServicio autenticacionServicio;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private NegocioRepo negocioRepo;

    @Test
    public void emailTest() throws Exception{

        emailServicio.enviarCorreo(new EmailDTO(
                "Prueba",
                "Prueba desde UniLocal ",
                "luisacahe5@gmail.com"
        ));
    }

    @Test
    public void autenticacionTest() throws Exception{

        autenticacionServicio.iniciarioSesionCliente(new LoginDTO(
                "juan@email.com",
                "123"
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
        assertNotNull(codigo);
    }

    @Test
    public void actualizarTest() throws Exception{
//Se crea un objeto de tipo ActualizarClienteDTO
        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "Cliente1",
                "Juan",
                "nueva foto",
                "juan@email.com",
                "Armenia"
        );
//Se actualiza el cliente
        clienteServicio.actualizarCliente(actualizarClienteDTO);
//Con el método obtenerCliente se obtiene el cliente con el id "Cliente1"
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente("Cliente1");
//Se verifica que la foto de perfil sea la misma que se actualizó
        Assertions.assertEquals("nueva foto", detalleClienteDTO.fotoPerfil());
    }

    @Test
    public void eliminarTest() throws Exception{
//Se elimina el cliente con el id "Cliente1"
        clienteServicio.eliminarCliente("Cliente1");
//Al intentar obtener el cliente con el id "Cliente1" se debe lanzar una excepción
        Assertions.assertThrows(Exception.class, () -> clienteServicio.obtenerCliente("Cliente1") );
    }

    @Test
    public void listarClientes(){
//Se obtiene la lista de clientes
        List<ItemClienteDTO> lista = clienteServicio.listarClientes();
//Se verifica que la lista no sea nula y que tenga 3 elementos
        Assertions.assertEquals(3, lista.size());
    }


    @Test
    public void testAgregarAFavoritos() throws Exception {
        // Supongamos que los IDs de cliente y negocio son conocidos y existen en la base de datos de prueba
        String idCliente = "661c090008b0af2fb61e9ac0";
        String idNegocio = "661c7f6b1a6e5165eaf9b538";

        // Crear el DTO de entrada
        NegocioFavoritoDTO negocioFavoritoDTO = new NegocioFavoritoDTO(idCliente, idNegocio);

        // Ejecutar el método de agregar a favoritos
        boolean resultado = clienteServicio.agregarAFavoritos(negocioFavoritoDTO);

        // Verificar que el resultado es verdadero
        Assertions.assertTrue(resultado);
    }


    @Test
    public void testQuitarFavoritos() throws Exception {
        // Supongamos que los IDs de cliente y negocio son conocidos y existen en la base de datos de prueba
        String idCliente = "661c090008b0af2fb61e9ac0";
        String idNegocio = "661c7f6b1a6e5165eaf9b538";

        // Crear el DTO de entrada
        NegocioFavoritoDTO negocioFavoritoDTO = new NegocioFavoritoDTO(idCliente, idNegocio);

        // Ejecutar el método de agregar a favoritos
        boolean resultado = clienteServicio.quitarDeFavoritos(negocioFavoritoDTO);
        // Verificar que el resultado es verdadero
        Assertions.assertTrue(resultado);
    }

    @Test
    public void testListarNegociosFavoritos() throws Exception {
        // Act: Invoca el método que deseas probar

        String idCliente= "661c090008b0af2fb61e9ac0";
        //La lista del cliente de favoritos está vacía

        // Assert: Verifica que se lanza la excepción esperada cuando no hay favoritos
        Exception exception = assertThrows(Exception.class, () -> {
            List<NegocioDTO> negociosFavoritos = clienteServicio.listarNegociosFavoritos(idCliente);
        }, "El cliente no tiene favoritos");

        // Verifica que el mensaje de la excepción sea el esperado
        assertTrue(exception.getMessage().contains("El cliente no tiene favoritos"));
    }
}



