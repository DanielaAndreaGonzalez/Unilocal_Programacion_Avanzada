package co.edu.uniquindio.UniLocal.controladores;

import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private ComentarioServicio comentarioServicio;
    @PostMapping("/crear-usuario")
    public ResponseEntity<MensajeDTO<String>> crearUsuario(@Valid @RequestBody RegistroUsuarioDTO usuario) {
        try {
            clienteServicio.registrarsCliente(usuario);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al registrar el cliente"));
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cliente registrado correctamente"));
    }

    //diferencia entre put y patch. Este ultimo se usa para actualizaciones pequeñas
    //como que un campo fue cambiado en la base de datos
    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO) {
        try {
            clienteServicio.actualizarCliente(actualizarClienteDTO);
        } catch (ResourceNotFoundException r) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al actualizar el cliente"));
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cliente actualizado correctamente"));
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String codigo) {
        try {
            clienteServicio.eliminarCuenta(codigo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al eliminar la cuenta"));
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cliente eliminado correctamente"));
    }

    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable String codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.obtenerCliente(codigo)));
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<MensajeDTO<List<ItemClienteDTO>>> listarClientes() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.listarClientes()));
    }

    @GetMapping("/listar-mejores-calificados/{tipoNegocio}")
    public ResponseEntity<MensajeDTO<List<NegocioDTO>>> obtenerNegociosMejorCalificados(@PathVariable TipoNegocio tipoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.obtenerNegociosMejorCalificados(tipoNegocio)));
    }

    @PostMapping("/agregar-favoritos")
    public  ResponseEntity<MensajeDTO<?>> agregarFavoritos(@RequestBody NegocioFavoritoDTO negocioFavoritoDTO) {
        try {
            return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.agregarAFavoritos(negocioFavoritoDTO)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al agregar a favoritos"));
        }
    }

    @PostMapping("/quitar-favoritos")
    public  ResponseEntity<MensajeDTO<?>> quitarFavoritos(@RequestBody NegocioFavoritoDTO negocioFavoritoDTO) {
        try {
            return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.quitarDeFavoritos(negocioFavoritoDTO)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al quitar de favoritos"));
        }
    }

    @GetMapping("/listar-negocios-favoritos/{clienteId}")
    public ResponseEntity<MensajeDTO<?>> listarNegociosFavoritos(@PathVariable String clienteId){
        try {
            return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.listarNegociosFavoritos(clienteId)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "El usuario no tiene favoritos"));
        }
    }

    @PostMapping("/crear-comentario")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@RequestBody RegistroComentarioDTO registroComentarioDTO) {
        try {
            return ResponseEntity.ok().body(new MensajeDTO<>(false, comentarioServicio.crearComentario(registroComentarioDTO)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al crear el comentario"));
        }
    }

    @PostMapping("/responder-comentario")
    public ResponseEntity<MensajeDTO<String>> responderComentario(@RequestBody RespuestaComentarioDTO respuestaComentarioDTO) {
        try {
            comentarioServicio.responderComentario(respuestaComentarioDTO);
            return ResponseEntity.ok().body(new MensajeDTO<>(false, "Respuesta enviada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al responder el comentario"));
        }
    }

    @GetMapping("/listar-comentarios-negocio/{negocioId}")
    public ResponseEntity<MensajeDTO<?>> listarComentariosNegocio(@PathVariable String negocioId) {
        try {
            return ResponseEntity.ok().body(new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(negocioId)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al listar los comentarios"));
        }
    }

    @PostMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@RequestBody CambioPasswordDTO cambioPasswordDTO)
    {
        try {
            clienteServicio.cambiarPassword(cambioPasswordDTO);
            return ResponseEntity.ok().body(new MensajeDTO<>(false,"Cambio de contraseña exitoso" ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error no se pudo cambiar la contraseña"));
        }
    }

    @PostMapping("/recuperar-password")
    public ResponseEntity<MensajeDTO<String>> recuperarPassword(@RequestBody RecuperacionPasswordDTO recuperacionPasswordDTO)
    {
        try {
            clienteServicio.recuperarPassword(recuperacionPasswordDTO);
            return ResponseEntity.ok().body(new MensajeDTO<>(false,"Cambio de contraseña exitoso" ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error no se pudo recuperar la contraseña"));
        }
    }

    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacion(@PathVariable String correo)throws Exception{
        clienteServicio.enviarLinkRecuperacion(correo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Se ha cambiado su contraseña"));
    }

    @GetMapping("/calcular-promedio-calificaciones/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Double>> calcularPromedioCalificaciones(@PathVariable String codigoNegocio) {
        try {
            return ResponseEntity.ok().body(new MensajeDTO<>(false, comentarioServicio.calcularPromedioCalificaciones(codigoNegocio)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, 0.0));
        }
    }

}
