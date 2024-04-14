package co.edu.uniquindio.UniLocal.servicios.implementaciones;

import co.edu.uniquindio.UniLocal.documentos.Cliente;
import co.edu.uniquindio.UniLocal.documentos.Moderador;
import co.edu.uniquindio.UniLocal.dto.LoginDTO;
import co.edu.uniquindio.UniLocal.dto.TokenDTO;
import co.edu.uniquindio.UniLocal.repositorio.ClienteRepo;
import co.edu.uniquindio.UniLocal.repositorio.ModeradorRepo;
import co.edu.uniquindio.UniLocal.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.UniLocal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final ClienteRepo clienteRepo;
    private final ModeradorRepo moderadorRepo;
    private final JWTUtils jwtUtils;
    @Override
    public TokenDTO iniciarioSesionCliente(LoginDTO loginDTO) throws Exception {

        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(loginDTO.correo());

        if(clienteOptional.isEmpty()){
            throw new Exception("El correo no se encuentra registrado");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cliente cliente = clienteOptional.get();

        if(!passwordEncoder.matches(loginDTO.password(),cliente.getPassword())){
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol","CLIENTE");
        map.put("nombre", cliente.getNombre());
        map.put("id", cliente.getCodigo());
        return new TokenDTO(jwtUtils.generarToken(cliente.getEmail(),map));
    }

    @Override
    public TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception {

        Optional<Moderador> moderadorOptional = moderadorRepo.findByEmail(loginDTO.correo());

        if(moderadorOptional.isEmpty()){
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Moderador moderador = moderadorOptional.get();

        if(!passwordEncoder.matches(loginDTO.password(),moderador.getPassword())){
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol","MODERADOR");
        map.put("nombre", moderador.getNombre());
        map.put("id", moderador.getCodigo());

        return new TokenDTO(jwtUtils.generarToken(moderador.getEmail(),map));
    }
}
