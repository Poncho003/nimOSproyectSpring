// Entidad que representa a los usuarios dentro del sistema
// Se usa para mapear la coleccion 'usuarios' en MongoDB
// Contiene informacion de acceso y rol asignado dentro de la aplicacion
package mx.wok.nimos.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {

    // Identificador unico del usuario dentro de la coleccion
    @Id
    private String id;

    // Nombre completo o identificador visible del usuario
    private String nombre;

    // Correo electronico usado como credencial de acceso
    private String correo;

    // Contraseña asociada a la cuenta del usuario
    private String password;

    // Rol o tipo de usuario (por ejemplo BODEGA, PEDIDOS, SUPERADMIN)
    private String rol;

    // Constructor vacio requerido por MongoDB
    public Usuario() {}

    // Constructor completo con los datos principales del usuario
    public Usuario(String nombre, String correo, String password, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters para acceder y modificar los datos del usuario
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
