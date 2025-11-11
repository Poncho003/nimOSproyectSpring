// Clase que permite desplegar la aplicacion en un contenedor externo como Tomcat o Jetty
// Extiende SpringBootServletInitializer para configurar el inicio del proyecto fuera del entorno embebido
// Define el punto de entrada alternativo al ejecutar el archivo WAR
package mx.wok.nimos;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	// Configura la aplicacion al ser desplegada en un servidor externo
	// Especifica la clase principal que contiene el metodo main
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NimosApplication.class);
	}

}
