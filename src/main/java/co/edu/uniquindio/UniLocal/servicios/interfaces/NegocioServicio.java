package co.edu.uniquindio.UniLocal.servicios.interfaces;

public interface NegocioServicio{

   // void crearNegocio(RegistroNegocioDTO negocioDTO);

     void crearNegocio();
    void actualizarNegocio();

    void eliminarNegocio(String idNegocio);

    void obtenerNegocio();

    void filtrarPorEstado();

    void buscarNegocios();

    void listarNegociosUsuario();

    void listarNegociosPorEstado();

    void cambiarEstado();

    void registrarRevision();





}