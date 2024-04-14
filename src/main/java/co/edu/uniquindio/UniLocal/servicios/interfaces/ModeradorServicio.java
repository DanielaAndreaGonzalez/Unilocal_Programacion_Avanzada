package co.edu.uniquindio.UniLocal.servicios.interfaces;

import co.edu.uniquindio.UniLocal.documentos.HistorialModeracion;
import co.edu.uniquindio.UniLocal.dto.AutorizarNegocioDTO;
import co.edu.uniquindio.UniLocal.dto.HistorialModeracionDTO;
import co.edu.uniquindio.UniLocal.dto.NegocioDTO;
import co.edu.uniquindio.UniLocal.excepciones.AutorizacionException;

import java.util.List;

public interface ModeradorServicio extends CuentaServicio{

    public void autorizarNegocio(AutorizarNegocioDTO autorizarNegocioDTO) throws AutorizacionException;

    public void rechazarNegocio(AutorizarNegocioDTO autorizarNegocioDTO) throws AutorizacionException;

    public List<NegocioDTO> obtenerLugaresPendientesAutorizar() throws AutorizacionException;

    public List<HistorialModeracionDTO> obtenerHistoricoLugaresAutorizados(String moderadorId) throws AutorizacionException;

    public List<HistorialModeracionDTO> obtenerHistoricoLugaresRechazados(String moderadorId) throws AutorizacionException;

    public void obtenerEstadisticaNegocio(NegocioDTO negocioDTO);


}