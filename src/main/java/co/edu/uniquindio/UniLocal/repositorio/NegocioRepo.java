package co.edu.uniquindio.UniLocal.repositorio;
import co.edu.uniquindio.UniLocal.modelo.Negocio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {

}