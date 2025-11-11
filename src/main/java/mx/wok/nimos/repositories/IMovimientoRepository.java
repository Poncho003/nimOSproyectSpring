// Repositorio encargado del manejo de datos para la entidad Movimiento
// Extiende MongoRepository para aprovechar las operaciones CRUD de MongoDB
// No requiere consultas personalizadas, MongoRepository provee los metodos basicos
package mx.wok.nimos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import mx.wok.nimos.models.Movimiento;

@Repository
public interface IMovimientoRepository extends MongoRepository<Movimiento, String> {
    // Repositorio vacio, las operaciones CRUD ya estan disponibles por defecto
}
