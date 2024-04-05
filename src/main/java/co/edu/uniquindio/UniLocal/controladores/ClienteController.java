package co.edu.uniquindio.UniLocal.controladores;

import co.edu.uniquindio.UniLocal.dto.RegistroUsuarioDTO;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;
    @PostMapping("/crear-usuario")
    public ResponseEntity<String> crearUsuario(@RequestBody RegistroUsuarioDTO usuario) {
        try {
            clienteServicio.registrarsCliente(usuario);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creando el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }
}
