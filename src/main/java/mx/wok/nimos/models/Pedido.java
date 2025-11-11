// Entidad que representa los pedidos realizados a proveedores dentro del sistema
// Se usa para mapear la coleccion 'pedidos' en MongoDB
// Almacena informacion sobre productos solicitados, proveedor, estado y fecha de solicitud
package mx.wok.nimos.models;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedidos")
public class Pedido {

    // Identificador unico del pedido en la coleccion
    @Id
    private String id;

    // Identificador del producto solicitado
    private String productoId;

    // Nombre del producto asociado al pedido
    private String nombreProducto;

    // Identificador del proveedor al que se realiza el pedido
    private String proveedorId;

    // Cantidad de unidades solicitadas del producto
    private int cantidadSolicitada;

    // Estado actual del pedido (por ejemplo PENDIENTE o COMPLETADO)
    private String estado;

    // Fecha y hora en que se realizo la solicitud
    private LocalDateTime fechaSolicitud;

    // Constructor vacio requerido por MongoDB
    public Pedido() {
    }

    // Getters y Setters para acceder y modificar los datos del pedido
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }
}
