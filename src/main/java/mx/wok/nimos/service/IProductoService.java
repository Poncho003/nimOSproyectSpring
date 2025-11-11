// Interfaz que define los metodos del servicio para la entidad Producto
// Establece las operaciones principales que implementara la clase ProductoService
// Permite gestionar los productos, sus cantidades y el control de inventario
package mx.wok.nimos.service;

import java.util.List;
import mx.wok.nimos.models.Producto;

public interface IProductoService {

    // Devuelve la lista completa de productos registrados
    List<Producto> todos();
    // Busca un producto por su identificador
    Producto obtenerPorId(String id);
    // Registra un nuevo producto en la base de datos
    Producto nuevo(Producto producto);
    // Actualiza los datos de un producto existente
    Producto actualizar(Producto producto);
    // Actualiza la cantidad disponible de un producto segun el motivo del cambio
    void actualizarCantidad(String id, int cantidad, String motivo);
    // Elimina un producto del sistema por su identificador
    void eliminar(String id);
    // Resta una cantidad del stock disponible y devuelve si la operacion fue exitosa
    boolean restarCantidad(String id, int cantidadEnviar);
    // Devuelve los productos con niveles bajos de inventario
    List<Producto> obtenerProductosBajos();
    // Guarda un producto nuevo o actualiza uno existente
    Producto guardar(Producto producto);
}
