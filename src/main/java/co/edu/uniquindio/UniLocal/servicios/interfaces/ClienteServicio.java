package co.edu.uniquindio.UniLocal.servicios.interfaces;


import co.edu.uniquindio.UniLocal.documentos.Cliente;
import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.ExcepcionesGlobales;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;

import java.util.List;

public interface ClienteServicio extends CuentaServicio{

    String registrarsCliente(RegistroUsuarioDTO registroUsuarioDTO) throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws ResourceNotFoundException;

    void eliminarCliente(String idCuenta) throws ResourceNotFoundException;

    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;

    List<ItemClienteDTO> listarClientes();

    List<NegocioDTO> obtenerNegociosMejorCalificados(TipoNegocio tipoNegocio) throws Exception;

    boolean agregarAFavoritos(NegocioFavoritoDTO negocioFavoritoDTO) throws Exception;
    boolean quitarDeFavoritos(NegocioFavoritoDTO negocioFavoritoDTO) throws Exception;
    List<NegocioDTO> listarNegociosFavoritos(String clienteId) throws Exception;

    Cliente obtenerClienteporId(String idCliente);
}