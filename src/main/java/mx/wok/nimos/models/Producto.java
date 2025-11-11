// Entidad que representa los productos dentro del sistema de inventario
// Se usa para mapear la coleccion 'productos' en MongoDB
// Contiene informacion sobre precios, cantidad, tipo, clasificacion y proveedor
package mx.wok.nimos.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
public class Producto {

    // Identificador unico del producto dentro de la coleccion
    @Id
    private String id;

    // Nombre del producto registrado
    private String nombre;

    // Tipo o categoria general del producto
    private String tipo;

    // Clasificacion especifica del producto (por ejemplo deportiva, alimenticia, etc)
    private String clasificacion;

    // Precio de compra registrado para control de costos
    private double precioCompra;

    // Precio de venta asignado al producto
    private double precioVenta;

    // Cantidad disponible actualmente en inventario
    private int cantidad;

    // Identificador del proveedor asociado al producto
    private String proveedorId;

    // Nombre o identificador de la sucursal donde se encuentra el producto
    private String sucursal;

    // Constructor vacio requerido por MongoDB
    public Producto() {
    }

    // Constructor con datos basicos del producto
    public Producto(String id, String nombre, double precioCompra, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
    }

    // Constructor completo con todos los atributos
    public Producto(String id, String nombre, String tipo, String clasificacion,
            double precioCompra, double precioVenta, int cantidad, String proveedorId, String sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.clasificacion = clasificacion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.proveedorId = proveedorId;
        this.sucursal = sucursal;
    }

    // Getters y Setters para acceder y modificar los datos del producto
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
}
