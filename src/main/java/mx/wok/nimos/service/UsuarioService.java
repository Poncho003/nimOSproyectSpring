// Servicio encargado de la logica de autenticacion y validacion de usuarios
// Utiliza el repositorio IUsuarioRepository para consultar credenciales en la base de datos
// Comprueba correo y contraseña para permitir el acceso al sistema
package mx.wok.nimos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.wok.nimos.models.Usuario;
import mx.wok.nimos.repositories.IUsuarioRepository;

@Service
public class UsuarioService {

    // Inyeccion del repositorio encargado de acceder a los datos de usuario
    @Autowired
    private IUsuarioRepository usuarioRepository;
    // Valida las credenciales de inicio de sesion
    // Busca un usuario por correo y compara la contraseña proporcionada
    // Retorna el usuario si las credenciales son correctas, de lo contrario null
    public Usuario validarLogin(String correo, String password) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }
}
