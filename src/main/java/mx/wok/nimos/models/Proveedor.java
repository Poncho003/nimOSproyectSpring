// Entidad que representa a los proveedores dentro del sistema
// Se usa para mapear la coleccion 'proveedores' en MongoDB
// Contiene informacion de contacto y ubicacion de cada proveedor
package mx.wok.nimos.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedores")
public class Proveedor {

    // Identificador unico del proveedor dentro de la coleccion
    @Id
    private String id;

    // Nombre comercial o razon social del proveedor
    private String nombre;

    // Nombre de la persona de contacto principal
    private String contacto;

    // Numero telefonico del proveedor o contacto
    private String telefono;

    // Direccion fisica del proveedor
    private String direccion;

    // Constructor vacio requerido por MongoDB
    public Proveedor() {
    }

    // Constructor completo con datos principales del proveedor
    public Proveedor(String nombre, String contacto, String telefono, String direccion) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y Setters para acceder y modificar los datos del proveedor
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
