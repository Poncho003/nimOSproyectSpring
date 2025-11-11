// Servicio encargado de la logica de negocio para la entidad Proveedor
// Gestiona las operaciones CRUD sobre los proveedores del sistema
// Utiliza el repositorio IProveedorRepository para acceder a la base de datos MongoDB
package mx.wok.nimos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.wok.nimos.models.Proveedor;
import mx.wok.nimos.repositories.IProveedorRepository;

@Service
public class ProveedorService {

    // Inyeccion del repositorio encargado del acceso a datos de proveedores
    @Autowired
    private IProveedorRepository proveedorRepository;
    // Devuelve la lista completa de proveedores registrados
    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }
    // Busca un proveedor especifico por su identificador
    public Proveedor obtenerPorId(String id) {
        return proveedorRepository.findById(id).orElse(null);
    }
    // Guarda un nuevo proveedor o actualiza uno existente
    // Si no tiene ID asignado, genera uno con formato 'prov+X'
    public Proveedor guardar(Proveedor proveedor) {
        if (proveedor.getId() == null || proveedor.getId().isEmpty()) {
            long count = proveedorRepository.count();
            proveedor.setId("prov" + (count + 1));
        }
        return proveedorRepository.save(proveedor);
    }
    // Actualiza la informacion de un proveedor reutilizando el metodo guardar
    public Proveedor actualizar(Proveedor proveedor) {
        return guardar(proveedor);
    }
    // Elimina un proveedor de la base de datos segun su identificador
    public void eliminar(String id) {
        proveedorRepository.deleteById(id);
    }
}
