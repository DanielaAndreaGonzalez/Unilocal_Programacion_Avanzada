package co.edu.uniquindio.UniLocal.controladores;

import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.UniLocal.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/negocio")
public class NegocioController {

    @Autowired
    private NegocioServicio negocioServicio;
    @PostMapping("/crear-negocio")
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody RegistroNegocioDTO registroNegocioDTO){
        try {
            negocioServicio.crearNegocio(registroNegocioDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al registrar el negocio"));
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Neogcio registrado correctamente"));
    }

    @PutMapping("/editar-negocio")
    public ResponseEntity<MensajeDTO<String>> actualizarNegocio(@Valid @RequestBody ActualizarNegocioDTO actualizarNegocioDTO) {
        try {
            negocioServicio.actualizarNegocio(actualizarNegocioDTO);
        } catch (Exception r ) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al actualizar el negocio"));
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio actualizado correctamente"));
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<MensajeDTO<List<NegocioDTO>>> listarNegocios() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegocios()));
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@PathVariable String codigo) throws Exception {
        try {
            negocioServicio.eliminarNegocio(codigo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al eliminar el negocio"));
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio eliminado correctamente"));
    }

    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<NegocioDTO>> obtenerNegocio(@PathVariable String codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerNegocio(codigo)));
    }

    @GetMapping("/obtener-por-nombre/{nombre}")
    public ResponseEntity<MensajeDTO<NegocioDTO>> obtenerNegocioPorNombre(@PathVariable String nombre) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerNegocioPorNombre(nombre)));
    }

    @GetMapping("/obtener-por-tipo-negocio/{tipoNegocio}")
    public ResponseEntity<MensajeDTO<List<NegocioDTO>>> obtenerNegocioPorTipoNegocio(@PathVariable TipoNegocio tipoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerNegocioPorTipoNegocio(tipoNegocio)));
    }

    @GetMapping("/obtener-por-ubicacion")
    public ResponseEntity<MensajeDTO<NegocioDTO>> obtenerNegocioPorUbicacion(@RequestBody Ubicacion ubicacion) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerNegocioPorUbicacion(ubicacion)));
    }

    @GetMapping("/listar-negocios-usuario/{codigoCliente}")
    public ResponseEntity<MensajeDTO<List<NegocioDTO>>> listarNegociosUsuario(@PathVariable String codigoCliente) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegociosUsuario(codigoCliente)));
    }
    @GetMapping("/listar-negocios-por-estado/{estadoNegocio}")
    public ResponseEntity<MensajeDTO<List<NegocioDTO>>> listarNegociosPorEstado(@PathVariable EstadoNegocio estadoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegociosPorEstado(estadoNegocio)));
    }
}
