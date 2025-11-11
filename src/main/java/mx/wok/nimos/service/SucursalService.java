// Servicio encargado de la logica de negocio para la entidad Sucursal
// Implementa la interfaz ISucursalService y gestiona la creacion, consulta y eliminacion de sucursales
// Utiliza el repositorio ISucursalRepository para acceder a la base de datos MongoDB
package mx.wok.nimos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.wok.nimos.models.Sucursal;
import mx.wok.nimos.repositories.ISucursalRepository;

@Service
public class SucursalService implements ISucursalService {

    // Inyeccion del repositorio para manejar operaciones CRUD de sucursales
    @Autowired
    private ISucursalRepository sucursalRepository;
    // Devuelve la lista completa de sucursales registradas
    @Override
    public List<Sucursal> todas() {
        return sucursalRepository.findAll();
    }
    // Busca una sucursal especifica por su identificador
    @Override
    public Sucursal obtenerPorId(String id) {
        return sucursalRepository.findById(id).orElse(null);
    }
    // Guarda una nueva sucursal o actualiza una existente
    // Si no tiene ID, se genera automaticamente con el formato 's+X'
    public Sucursal guardar(Sucursal sucursal) {
        if (sucursal.getId() == null || sucursal.getId().isEmpty()) {
            long count = sucursalRepository.count();
            sucursal.setId("s" + (count + 1));
        }
        return sucursalRepository.save(sucursal);
    }
    // Elimina una sucursal de la base de datos segun su identificador
    public void eliminar(String id) {
        sucursalRepository.deleteById(id);
    }
}
