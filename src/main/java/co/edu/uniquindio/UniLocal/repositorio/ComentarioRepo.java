package co.edu.uniquindio.UniLocal.repositorio;

import co.edu.uniquindio.UniLocal.documentos.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {
}
