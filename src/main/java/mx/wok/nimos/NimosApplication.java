// Clase principal del proyecto Spring Boot nimOS
// Es el punto de entrada de la aplicacion y se encarga de inicializar el contexto de Spring
// La anotacion SpringBootApplication habilita la configuracion automatica y el escaneo de componentes
package mx.wok.nimos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NimosApplication {

	// Metodo principal que ejecuta la aplicacion
	// Inicia el servidor embebido y carga todos los componentes del proyecto
	public static void main(String[] args) {
		SpringApplication.run(NimosApplication.class, args);
	}

}
