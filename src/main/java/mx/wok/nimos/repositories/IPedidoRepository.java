// Repositorio encargado del manejo de datos para la entidad Pedido
// Extiende MongoRepository para utilizar las operaciones CRUD basicas en MongoDB
// No requiere consultas personalizadas, el framework provee los metodos principales
package mx.wok.nimos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import mx.wok.nimos.models.Pedido;

public interface IPedidoRepository extends MongoRepository<Pedido, String> {
    // Repositorio vacio, las operaciones CRUD ya estan disponibles por defecto
}
