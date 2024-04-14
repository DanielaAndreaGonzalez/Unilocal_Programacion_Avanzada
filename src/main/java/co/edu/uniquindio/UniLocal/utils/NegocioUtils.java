package co.edu.uniquindio.UniLocal.utils;

import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.NegocioDTO;

import java.util.List;
import java.util.stream.Collectors;

public class NegocioUtils {

    public static List<NegocioDTO> convertirListaNegocioAListaNegocioDto(List<Negocio> negociosPendientesAutorizar) {
        return negociosPendientesAutorizar.stream()
                .map(NegocioUtils::convertirANegocioDTO)
                .collect(Collectors.toList());
    }

    public static NegocioDTO convertirANegocioDTO(Negocio negocio) {
        return new NegocioDTO(
                negocio.getCodigo(),
                negocio.getNombre(),
                negocio.getDescripcion(),
                negocio.getImagenes(),
                negocio.getHorarios(),
                negocio.getTelefonos(),
                negocio.getComentarios(),
                negocio.getEstado(),
                negocio.getUbicacion(),
                negocio.getTipoNegocio()
        );
    }
}
