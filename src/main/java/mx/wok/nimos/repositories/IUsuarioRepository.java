// Repositorio encargado del manejo de datos para la entidad Usuario
// Extiende MongoRepository para realizar operaciones CRUD sobre la coleccion 'usuarios'
// Incluye un metodo personalizado para buscar usuarios por correo electronico
package mx.wok.nimos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import mx.wok.nimos.models.Usuario;

@Repository
public interface IUsuarioRepository extends MongoRepository<Usuario, String> {
    // Busca un usuario en la base de datos segun su correo electronico
    Usuario findByCorreo(String correo);
}
