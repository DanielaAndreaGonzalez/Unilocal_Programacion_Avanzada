package co.edu.uniquindio.UniLocal.controladores;

import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ClienteServicio;
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




}
