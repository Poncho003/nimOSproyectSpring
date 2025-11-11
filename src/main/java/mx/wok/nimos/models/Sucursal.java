// Entidad que representa las sucursales registradas en el sistema
// Se usa para mapear la coleccion 'sucursal' en MongoDB
// Contiene informacion basica como nombre, direccion y encargado responsable
package mx.wok.nimos.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sucursal")
public class Sucursal {

    // Identificador unico de la sucursal dentro de la coleccion
    @Id
    private String id;

    // Nombre asignado a la sucursal
    private String nombre;

    // Direccion fisica donde se ubica la sucursal
    private String direccion;

    // Nombre del encargado o responsable de la sucursal
    private String encargado;

    // Getters y Setters para acceder y modificar los datos de la sucursal
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
}
