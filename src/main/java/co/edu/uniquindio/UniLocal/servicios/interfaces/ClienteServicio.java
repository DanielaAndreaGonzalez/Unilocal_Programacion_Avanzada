package co.edu.uniquindio.UniLocal.servicios.interfaces;


import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;

import java.util.List;

public interface ClienteServicio extends CuentaServicio{

    String registrarsCliente(RegistroUsuarioDTO registroUsuarioDTO) throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws ResourceNotFoundException;

    void eliminarCliente(String idCuenta) throws ResourceNotFoundException;

    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;

    List<ItemClienteDTO> listarClientes();

    public List<NegocioDTO> obtenerNegociosMejorCalificados(TipoNegocio tipoNegocio) throws Exception;

    public boolean agregarAFavoritos(String clienteId, String negocioId) throws Exception;
     public boolean quitarDeFavoritos(String clienteId, String negocioId) throws Exception;
}