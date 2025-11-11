
# 🧵 nimOSproyectSpring

Sistema ERP desarrollado en Spring Boot para la gestión integral de una tienda de ropa online, el cual ofrece módulos especializados para diferentes roles de usuario: administradores, proveedores y personal de bodega, optimizando los procesos de inventario, compras y gestión del negocio.

---

## 📁 Estructura del Proyecto

```
NIMOS/
├── data/
│   └── mongoDB.zip              # Colecciones exportadas de MongoDB
├── src/
│   └── main/
│       ├── java/mx/wok/nimos/
│       │   ├── controllers/     # Controladores principales (rutas y vistas)
│       │   ├── models/          # Entidades: Producto, Pedido, Usuario, etc.
│       │   ├── repositories/    # Interfaces CRUD (MongoRepository)
│       │   ├── service/         # Lógica de negocio y servicios
│       │   ├── NimosApplication.java
│       │   └── ServletInitializer.java
│       └── resources/
│           ├── static/          # Archivos estáticos (CSS, JS, imágenes)
│           ├── templates/       # Vistas Thymeleaf
│           └── application.properties
└── README.md
```

---

## 🚀 Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/usuario/nimos.git
cd nimos
```

### 2. Configurar conexión a MongoDB

Editar el archivo `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/nimosdb
spring.thymeleaf.cache=false
```

### 3. Importar las colecciones

Descomprime el archivo `data/mongoDB.zip` y luego ejecuta:

```bash
mongorestore --db nimosdb ./mongoDB/
```

O usa MongoDB Compass para importar manualmente.

### 4. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

### 5. Abrir en navegador

```
http://localhost:8080
```

---

## 🧮 Módulos del Sistema

| Módulo      | Descripción                                       |
|-------------|---------------------------------------------------|
| Usuarios    | Manejo de roles: administrador, proveedor, bodega |
| Productos   | Registro, actualización y control de stock        |
| Pedidos     | Gestión de órdenes y movimientos de inventario    |
| Proveedores | Registro y control de proveedores                 |
| Sucursales  | Administración de ubicaciones y existencias       |
| Movimientos | Control de entradas y salidas de bodega           |

---

## 🧠 Tecnologías Empleadas

- Java 17+
- Spring Boot 3.x
- MongoDB
- Thymeleaf
- Maven
- HTML / CSS / Bootstrap

---

## 🧾 Roles del Sistema

| Rol             | Permisos principales                                   |
|-----------------|--------------------------------------------------------|
| 👑 Administrador | Control total del sistema, usuarios y proveedores     |
| 🧑‍💼 Proveedor     | Registro y actualización de productos                 |
| 🚚 Bodega        | Control de inventario, envíos y movimientos           |

---

## 🌱 Estructura Lógica del Proyecto

```
Controller  →  Service  →  Repository  →  MongoDB
     ↑
 (Vistas Thymeleaf)
```

- **Controller**: Maneja las solicitudes del usuario
- **Service**: Aplica la lógica de negocio
- **Repository**: Se comunica directamente con MongoDB

---

## 📬 Correos Institucionales

| Rol         | Correo                     | Contraseña |
|-------------|----------------------------|------------|
| Administrador | admin@nimos.mx            | 1234       |
| Pedidos      | pedidos@nimos.mx          | 1234       |
| Bodega       | bodega@nimos.mx           | 1234       |

> 🔐 Estos correos están configurados para pruebas internas del sistema.  
> ⚠️ Asegúrate de cambiar las contraseñas en producción.

---

## 🛠️ Próximas Mejoras

- 🔐 Autenticación y roles con **Spring Security**
- 📊 Dashboard de métricas y reportes en tiempo real
- 📱 API REST pública para pedidos y productos
- 📦 Exportación de reportes en PDF/Excel

---

## 👨‍💻 Autor

*Alfonso Medina**  
💼 Ingeniería en Tecnologías de la Información y Comunicación  
📧 Contacto: [alfonsomedinaalvarado@gmail.com](alfonsomedinaalvarado@gmail.com)  
💻 GitHub: [Poncho003](https://github.com/Poncho003)

---

## 📜 Licencia

Este proyecto está bajo la licencia **MIT** — puedes usarlo, modificarlo y compartirlo libremente con atribución.
