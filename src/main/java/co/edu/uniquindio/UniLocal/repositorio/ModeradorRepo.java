package co.edu.uniquindio.UniLocal.repositorio;

import co.edu.uniquindio.UniLocal.documentos.Cliente;
import co.edu.uniquindio.UniLocal.documentos.Moderador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeradorRepo extends MongoRepository<Moderador,String> {

    Optional<Moderador> findByEmail(String email);
}
