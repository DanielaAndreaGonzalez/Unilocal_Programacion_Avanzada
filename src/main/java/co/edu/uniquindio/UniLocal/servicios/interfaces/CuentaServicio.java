package co.edu.uniquindio.UniLocal.servicios.interfaces;

import co.edu.uniquindio.UniLocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.UniLocal.dto.InicioSesionDTO;
import co.edu.uniquindio.UniLocal.dto.RecuperacionPasswordDTO;

public interface CuentaServicio {

    void eliminarCuenta(String codigo) throws Exception;

    void enviarLinkRecuperacion(String email) throws Exception;

    void recuperarPassword(RecuperacionPasswordDTO RecuperacionPasswordDTO)throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;

}
