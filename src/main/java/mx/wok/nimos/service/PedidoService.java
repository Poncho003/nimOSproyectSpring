// Servicio encargado de la logica de negocio para la entidad Pedido
// Gestiona la creacion, actualizacion y consulta de pedidos en el sistema
// Utiliza los repositorios y servicios para vincular productos y proveedores
package mx.wok.nimos.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.wok.nimos.models.Pedido;
import mx.wok.nimos.models.Producto;
import mx.wok.nimos.repositories.IPedidoRepository;

@Service
public class PedidoService {

    // Inyeccion del repositorio de pedidos para operaciones CRUD
    @Autowired
    private IPedidoRepository pedidoRepository;
    // Inyeccion del servicio de productos para obtener datos de productos asociados
    @Autowired
    private IProductoService productoService;
    // Devuelve la lista completa de pedidos registrados
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }
    // Guarda un pedido nuevo o actualiza uno existente
    // Si no tiene ID asignado, se genera automaticamente con formato 'ped+X'
    public Pedido guardar(Pedido pedido) {
        if (pedido.getId() == null || pedido.getId().isEmpty()) {
            long count = pedidoRepository.count();
            pedido.setId("ped" + (count + 1));
        }
        return pedidoRepository.save(pedido);
    }
    // Busca un pedido especifico por su identificador
    public Pedido buscarPorId(String id) {
        return pedidoRepository.findById(id).orElse(null);
    }
    // Actualiza el estado de un pedido existente (por ejemplo Pendiente o Completado)
    public void actualizarEstado(String id, String estado) {
        Pedido pedido = buscarPorId(id);
        if (pedido != null) {
            pedido.setEstado(estado);
            pedidoRepository.save(pedido);
        }
    }
    // Crea una nueva solicitud de pedido con proveedor y cantidad especificados
    // Valida que el producto exista antes de crear el registro
    public void crearSolicitud(String productoId, String proveedorId, int cantidad) {
        Producto producto = productoService.obtenerPorId(productoId);

        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado: " + productoId);
        }

        Pedido nuevo = new Pedido();
        nuevo.setProductoId(producto.getId());
        nuevo.setNombreProducto(producto.getNombre());
        nuevo.setCantidadSolicitada(cantidad);
        nuevo.setProveedorId(proveedorId);
        nuevo.setEstado("Pendiente");
        nuevo.setFechaSolicitud(LocalDateTime.now());
        guardar(nuevo);
    }

    // Variante del metodo anterior sin proveedor, usada para solicitudes internas
    public void crearSolicitud(Long productoId, int cantidad) {
        Producto producto = productoService.obtenerPorId(String.valueOf(productoId));

        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + productoId);
        }

        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setProductoId(producto.getId());
        nuevoPedido.setNombreProducto(producto.getNombre());
        nuevoPedido.setCantidadSolicitada(cantidad);
        nuevoPedido.setEstado("Pendiente");
        nuevoPedido.setFechaSolicitud(LocalDateTime.now());

        guardar(nuevoPedido);
    }
}
