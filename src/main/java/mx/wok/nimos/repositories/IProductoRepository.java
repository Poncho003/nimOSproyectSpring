// Repositorio encargado del manejo de datos para la entidad Producto
// Extiende MongoRepository para aprovechar las operaciones CRUD sobre la coleccion 'productos'
// No requiere consultas personalizadas, MongoRepository ofrece los metodos necesarios
package mx.wok.nimos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import mx.wok.nimos.models.Producto;

@Repository
public interface IProductoRepository extends MongoRepository<Producto, String> {
    // Repositorio vacio, las operaciones CRUD ya estan disponibles por defecto
}
