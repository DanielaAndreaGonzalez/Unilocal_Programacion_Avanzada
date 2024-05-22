package co.edu.uniquindio.UniLocal.controladores;

import co.edu.uniquindio.UniLocal.dto.DetalleClienteDTO;
import co.edu.uniquindio.UniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.UniLocal.dto.MensajeDTO;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/publico")
public class PublicoController {

    @Autowired
    private NegocioServicio negocioServicio;

    @GetMapping("/listar-ciudades")
    public ResponseEntity<MensajeDTO<List<String>>> obtenerCiudades() throws Exception {
        List<String> ciudades = new ArrayList<>();
        ciudades.add("Armenia");
        ciudades.add("Bogota");
        ciudades.add("Cúcuta");
        ciudades.add("Ibague");
        ciudades.add("Neiva");
        ciudades.add("Cali");
        ciudades.add("Medellin");
        ciudades.add("Pereira");
        ciudades.add("Manizales");
        return ResponseEntity.ok().body(new MensajeDTO<>(false,ciudades));
    }

    @GetMapping("/listar-tipos-negocio")
    public ResponseEntity<MensajeDTO<List<String>>> obtenerTiponegocio() throws Exception {
        List<String> tiposNegocio = Arrays.stream(TipoNegocio.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new MensajeDTO<>(false,tiposNegocio));
    }

    @GetMapping("/listar-negocios-cernanos")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegociosCercanos() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegocios()));
    }
}
