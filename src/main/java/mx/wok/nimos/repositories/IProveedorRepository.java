// Repositorio encargado del manejo de datos para la entidad Proveedor
// Extiende MongoRepository para realizar operaciones CRUD sobre la coleccion 'proveedores'
// No requiere consultas personalizadas, el framework proporciona los metodos basicos
package mx.wok.nimos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import mx.wok.nimos.models.Proveedor;

public interface IProveedorRepository extends MongoRepository<Proveedor, String> {
    // Repositorio vacio, las operaciones CRUD ya estan disponibles por defecto
}
