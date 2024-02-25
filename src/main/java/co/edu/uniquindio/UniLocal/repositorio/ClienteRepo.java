package co.edu.uniquindio.UniLocal.repositorio;
import co.edu.uniquindio.UniLocal.modelo.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {

}