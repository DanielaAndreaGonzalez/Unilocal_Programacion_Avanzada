package co.edu.uniquindio.UniLocal.controladores;

import co.edu.uniquindio.UniLocal.dto.AutorizarNegocioDTO;
import co.edu.uniquindio.UniLocal.dto.MensajeDTO;
import co.edu.uniquindio.UniLocal.dto.NegocioDTO;
import co.edu.uniquindio.UniLocal.excepciones.AutorizacionException;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ModeradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderador")
public class ModeradorController {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @PostMapping("/autorizar-negocio")
    public ResponseEntity<MensajeDTO<String>> autorizarNegocio(@RequestBody AutorizarNegocioDTO autorizarNegocioDTO){
        try {
            moderadorServicio.autorizarNegocio(autorizarNegocioDTO);
        } catch (AutorizacionException e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al autorizar el negocio"));
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio autorizado correctamente"));
    }
    @PostMapping("/rechazar-negocio")
    public ResponseEntity<MensajeDTO<String>> rechazarNegocio(@RequestBody AutorizarNegocioDTO autorizarNegocioDTO){
        try {
            moderadorServicio.rechazarNegocio(autorizarNegocioDTO);
        } catch (AutorizacionException e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al rechazar el negocio"));
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio rechazado correctamente"));
    }

    @GetMapping("/obtener-lugares-pendientes-autorizar")
    public ResponseEntity<MensajeDTO<?>> obtenerLugaresPendientesAutorizar() {
        try {
            return ResponseEntity.ok().body(new MensajeDTO<>(false, moderadorServicio.obtenerLugaresPendientesAutorizar()));
        } catch (AutorizacionException e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

}
