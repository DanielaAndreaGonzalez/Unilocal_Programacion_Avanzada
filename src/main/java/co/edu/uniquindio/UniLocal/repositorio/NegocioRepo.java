package co.edu.uniquindio.UniLocal.repositorio;
import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.entidades.Ubicacion;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.TipoNegocio;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {

    //buscar todos por estado
    List<Negocio> findByEstado(EstadoNegocio estado);

    @Aggregation(pipeline = {
            "{ '$match': { 'tipoNegocio': ?0 } }", // Filtra negocios por el enum TipoNegocio
            "{ '$unwind': '$comentarios' }",
            "{ '$group': { '_id': '$_id', 'nombre': { '$first': '$nombre' }, 'promedioCalificacion': { '$avg': '$comentario.calificacion' } } }",
            "{ '$sort': { 'promedioCalificacion': -1 } }" // Ordena los resultados por calificación promedio en orden descendente
    })
    List<Negocio> findNegociosMejorCalificacion(TipoNegocio tipoNegocio);

    Optional<Negocio> findByNombre(String nombreNegocio);
    Optional<Negocio> findByTipoNegocio(TipoNegocio tipoNegocio);
    Optional<Negocio> findByUbicacion(Ubicacion ubicacion);

}