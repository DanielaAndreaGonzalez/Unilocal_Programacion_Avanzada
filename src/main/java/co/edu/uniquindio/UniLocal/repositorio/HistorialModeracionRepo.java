package co.edu.uniquindio.UniLocal.repositorio;

import co.edu.uniquindio.UniLocal.documentos.HistorialModeracion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialModeracionRepo extends MongoRepository<HistorialModeracion, String> {
    List<HistorialModeracion> findByModeradorIdAndEstadoNegocio(String moderadorId,EstadoNegocio estadoNegocio);


}
