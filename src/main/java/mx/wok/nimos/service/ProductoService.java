// Servicio encargado de la logica de negocio para la entidad Producto
// Implementa la interfaz IProductoService con las operaciones principales de inventario
// Gestiona la creacion, modificacion y control de stock de los productos almacenados
package mx.wok.nimos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.wok.nimos.models.Producto;
import mx.wok.nimos.repositories.IProductoRepository;

@Service
public class ProductoService implements IProductoService {

    // Inyeccion del repositorio para el acceso a la base de datos MongoDB
    @Autowired
    private IProductoRepository productoRepository;
    // Devuelve todos los productos registrados en la base de datos
    @Override
    public List<Producto> todos() {
        return productoRepository.findAll();
    }
    // Busca un producto especifico por su identificador
    @Override
    public Producto obtenerPorId(String id) {
        return productoRepository.findById(id).orElse(null);
    }
    // Registra un nuevo producto en el sistema
    @Override
    public Producto nuevo(Producto producto) {
        return guardar(producto);
    }
    // Actualiza los datos de un producto existente
    @Override
    public Producto actualizar(Producto producto) {
        return guardar(producto);
    }
    // Elimina un producto por su identificador
    @Override
    public void eliminar(String id) {
        productoRepository.deleteById(id);
    }
    // Actualiza la cantidad de stock de un producto y registra el motivo del cambio
    @Override
    public void actualizarCantidad(String id, int cantidad, String motivo) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        p.setCantidad(cantidad);
        productoRepository.save(p);
        System.out.println("Motivo del ajuste: " + motivo);
    }
    // Obtiene los productos cuyo stock es inferior a 10 unidades
    @Override
    public List<Producto> obtenerProductosBajos() {
        List<Producto> todos = productoRepository.findAll();
        return todos.stream()
                .filter(p -> p.getCantidad() < 10)
                .toList();
    }
    // Guarda un producto nuevo o actualiza uno existente
    // Si no tiene ID asignado, genera uno con formato 'pX'
    @Override
    public Producto guardar(Producto producto) {
        if (producto.getId() == null || producto.getId().isEmpty()) {
            long count = productoRepository.count();
            producto.setId("p" + (count + 1));
        }
        return productoRepository.save(producto);
    }
    // Resta unidades de un producto al realizar un envio a sucursal
    // Valida que haya stock suficiente antes de realizar la operacion
    public boolean restarCantidad(String id, int cantidadEnviar) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        int cantidadActual = producto.getCantidad();

        if (cantidadEnviar > cantidadActual) {
            System.out.println("⚠️ Stock insuficiente para el producto: " + producto.getNombre());
            return false;
        }

        producto.setCantidad(cantidadActual - cantidadEnviar);
        productoRepository.save(producto);

        System.out.println("✅ Se enviaron " + cantidadEnviar + " unidades de " + producto.getNombre()
                + ". Nuevo stock: " + producto.getCantidad());

        return true;
    }
}
