package co.edu.uniquindio.UniLocal.controladores;


import co.edu.uniquindio.UniLocal.dto.LoginDTO;
import co.edu.uniquindio.UniLocal.dto.MensajeDTO;
import co.edu.uniquindio.UniLocal.dto.RegistroUsuarioDTO;
import co.edu.uniquindio.UniLocal.dto.TokenDTO;
import co.edu.uniquindio.UniLocal.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ClienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionControlador {

    private final AutenticacionServicio autenticacionServicio;

    @Autowired
    private ClienteServicio clienteServicio;
    @PostMapping("/login-cliente")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionCliente(@Valid @RequestBody LoginDTO loginDTO) throws Exception
    {
        TokenDTO tokenDTO = autenticacionServicio.iniciarioSesionCliente(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/login-moderador")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionModerador(@Valid @RequestBody LoginDTO loginDTO) throws Exception
    {
        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionModerador(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/crear-usuario")
    public ResponseEntity<MensajeDTO<String>> crearUsuario(@Valid @RequestBody RegistroUsuarioDTO usuario) {
        try {
            clienteServicio.registrarsCliente(usuario);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al registrar el cliente"));
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cliente registrado correctamente"));
    }

}
