// Interfaz que define los metodos del servicio para la entidad Sucursal
// Establece las operaciones principales que implementara la clase SucursalService
// Facilita la gestion de sucursales y la obtencion de su informacion
package mx.wok.nimos.service;

import java.util.List;
import mx.wok.nimos.models.Sucursal;

public interface ISucursalService {
    
    // Devuelve la lista completa de sucursales registradas
    List<Sucursal> todas();
    // Busca una sucursal por su identificador
    Sucursal obtenerPorId(String id);
}
