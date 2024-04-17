package co.edu.uniquindio.UniLocal.repositorio;
import co.edu.uniquindio.UniLocal.documentos.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {
    Optional<Cliente> findByNickname(String Nickname);
    Optional<Cliente> findByEmail(String email);
    //Optional<Cliente> findByCodigoSeguridad(String codigoSeguridad);
}