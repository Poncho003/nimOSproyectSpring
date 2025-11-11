// Entidad que representa los movimientos registrados en el sistema
// Se usa para mapear la coleccion 'movimientos' en MongoDB
// Contiene informacion sobre envios, usuarios, proveedores y productos involucrados
package mx.wok.nimos.models;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movimientos")
public class Movimiento {

    // Identificador unico del movimiento dentro de la coleccion
    @Id
    private String id;

    // Identificador del usuario que realiza el movimiento
    private String usuarioId;

    // Identificador del proveedor relacionado con el movimiento
    private String proveedorId;

    // Fecha y hora en que se registra el movimiento
    private LocalDateTime fecha;

    // Lista de productos incluidos en el movimiento
    private List<Producto> productos;

    // Estado actual del movimiento (por ejemplo ENVIADO o RECIBIDO)
    private String estado;

    // Sucursal destino del envio o movimiento
    private String sucursalDestino;

    // Constructor vacio requerido por MongoDB
    public Movimiento() {
    }

    // Constructor completo que inicializa todos los campos
    public Movimiento(String usuarioId, String proveedorId, LocalDateTime fecha, List<Producto> productos,
            String estado, String sucursalDestino) {
        this.usuarioId = usuarioId;
        this.proveedorId = proveedorId;
        this.fecha = fecha;
        this.productos = productos;
        this.estado = estado;
        this.sucursalDestino = sucursalDestino;
    }

    // Getters y Setters para acceder y modificar los datos del movimiento
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(String sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }
}
