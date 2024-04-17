package co.edu.uniquindio.UniLocal.repositorio;

import co.edu.uniquindio.UniLocal.documentos.Comentario;
import co.edu.uniquindio.UniLocal.dto.ItemComentarioDTO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {

    @Aggregation(pipeline = {
            "{ '$match': { 'codigoNegocio': ?0 } }",
            "{ '$lookup': { 'from': 'clientes', 'localField': 'codigoCliente', 'foreignField': '_id', 'as': 'clienteInfo' } }",
            "{ '$unwind': { 'path': '$clienteInfo', 'preserveNullAndEmptyArrays': true } }",
            "{ '$project': { 'codigoComentario': '$_id', 'mensaje': 1, 'respuesta': 1, 'nombrecliente': '$clienteInfo.nombre', 'fotoCliente': '$clienteInfo.fotoPerfil', 'fechaFormato': { '$dateToString': { 'format': '%Y-%m-%d', 'date': '$fecha' } }, 'calificacion': 1 } }"
    })
    /*@Aggregation({
            "{ $match: { codigoNegocio: ?0 } }",
            "{ $lookup: { from: 'clientes', localField:'codigoCliente', foreignField: '_id', as: 'cliente' } }",
            "{ $unwind: '$cliente' }",
            "{ $project: { codigoComentario: '$_id', mensaje: 1, respuesta: 1, nombrecliente: '$cliente.nombre', fotoCliente: '$cliente.fotoPerfil' } }"
    })*/
    List<ItemComentarioDTO> findComentariosByNegocioId(String codigoNegocio);


    //calcular promedio calificación negocio
    @Aggregation(pipeline = {
            "{ '$match': { 'codigoNegocio': ?0 } }",
            "{ '$group': { '_id': null, 'avg': { '$avg': '$calificacion' } } }"
    })
    Double calcularPromedioCalificacionNegocio(String codigoNegocio);
}
