package co.edu.uniquindio.UniLocal.servicios.interfaces;


import co.edu.uniquindio.UniLocal.dto.EmailDTO;

public interface EmailServicio {

    void enviarCorreo(EmailDTO emailDTO) throws Exception;


}
