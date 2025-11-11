// Servicio encargado de la logica de negocio para la entidad Movimiento
// Gestiona los registros de movimientos entre bodega, proveedores y sucursales
// Utiliza el repositorio IMovimientoRepository para acceder a la base de datos MongoDB
package mx.wok.nimos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.wok.nimos.models.Movimiento;
import mx.wok.nimos.repositories.IMovimientoRepository;

@Service
public class MovimientoService {

    // Inyeccion del repositorio que maneja las operaciones CRUD con MongoDB
    @Autowired
    private IMovimientoRepository movimientoRepository;
    // Devuelve la lista completa de movimientos registrados
    public List<Movimiento> todos() {
        return movimientoRepository.findAll();
    }
    // Guarda un nuevo movimiento o actualiza uno existente
    // Si el movimiento no tiene ID asignado, se genera uno nuevo con formato 'mov+X'
    public Movimiento guardar(Movimiento movimiento) {
        if (movimiento.getId() == null || movimiento.getId().isEmpty()) {
            long count = movimientoRepository.count();
            movimiento.setId("mov" + (count + 1));
        }
        return movimientoRepository.save(movimiento);
    }
    // Busca un movimiento especifico por su identificador
    public Movimiento obtenerPorId(String id) {
        return movimientoRepository.findById(id).orElse(null);
    }
    // Elimina un movimiento segun su identificador
    public void eliminar(String id) {
        movimientoRepository.deleteById(id);
    }
}
