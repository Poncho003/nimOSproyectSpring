// Repositorio encargado del manejo de datos para la entidad Sucursal
// Extiende MongoRepository para ejecutar operaciones CRUD sobre la coleccion 'sucursal'
// No requiere consultas personalizadas, MongoRepository ya incluye los metodos basicos
package mx.wok.nimos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import mx.wok.nimos.models.Sucursal;

public interface ISucursalRepository extends MongoRepository<Sucursal, String> {
    // Repositorio vacio, las operaciones CRUD ya estan disponibles por defecto
}
