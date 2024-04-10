package co.edu.uniquindio.UniLocal.servicios.interfaces;

import co.edu.uniquindio.UniLocal.dto.LoginDTO;
import co.edu.uniquindio.UniLocal.dto.TokenDTO;

public interface AutenticacionServicio {

    TokenDTO iniciarioSesionCliente(LoginDTO loginDTO)  throws Exception;
    TokenDTO iniciarSesionModerador(LoginDTO loginDTO)  throws Exception;
}
