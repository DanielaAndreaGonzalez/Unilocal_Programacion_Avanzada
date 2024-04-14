package co.edu.uniquindio.UniLocal.excepciones;

public class AutorizacionException extends Exception{
    public AutorizacionException(String mensaje){
        super(mensaje);
    }

    public AutorizacionException(){
        super("No se pudo autorizar");
    }

    public AutorizacionException(String mensaje, Exception e){
        super(mensaje, e);
    }

    public AutorizacionException(Exception e){
        super(e);
    }
}
