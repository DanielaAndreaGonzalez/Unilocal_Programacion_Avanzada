package co.edu.uniquindio.UniLocal.servicios.interfaces;

import co.edu.uniquindio.UniLocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.UniLocal.dto.InicioSesionDTO;
import co.edu.uniquindio.UniLocal.dto.RecuperacionPasswordDTO;

public interface CuentaServicio {

    void eliminarCuenta(String codigo) throws Exception;

    void iniciariSesion(InicioSesionDTO InicioSesionDTO);

    void enviarLinkRecuperacion(String email);

    void recuperarPassword(RecuperacionPasswordDTO RecuperacionPasswordDTO);

    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;

}
