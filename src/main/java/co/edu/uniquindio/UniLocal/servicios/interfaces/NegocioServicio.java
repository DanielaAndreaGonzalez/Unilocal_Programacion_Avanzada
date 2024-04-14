package co.edu.uniquindio.UniLocal.servicios.interfaces;

import co.edu.uniquindio.UniLocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.UniLocal.dto.NegocioDTO;
import co.edu.uniquindio.UniLocal.dto.RegistroNegocioDTO;
import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;

public interface NegocioServicio{
    String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    void obtenerNegocio();

    void filtrarPorEstado();

    void buscarNegocios();

    void listarNegociosUsuario();

    void listarNegociosPorEstado();

    void cambiarEstado();

    void registrarRevision();

    NegocioDTO obtenerNegocioPorNombre(String nombreNegocio) throws ResourceNotFoundException;
    NegocioDTO obtenerNegocioPorTipoNegocio(TipoNegocio tipoNegocio) throws ResourceNotFoundException;
    NegocioDTO obtenerNegocioPorUbicacion(Ubicacion ubicacion) throws ResourceNotFoundException;



}