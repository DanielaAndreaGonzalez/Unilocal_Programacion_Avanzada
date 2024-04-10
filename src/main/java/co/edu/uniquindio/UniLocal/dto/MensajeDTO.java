package co.edu.uniquindio.UniLocal.dto;

public record MensajeDTO<T>(

        boolean error,
        T respuesta
)
{
}
