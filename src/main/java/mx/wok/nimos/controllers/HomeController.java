// Controlador principal del sistema web nimOS
// Administra las vistas y operaciones de las áreas Bodega, Proveedor y Administración
// Gestiona inventario, pedidos, movimientos y validación de usuarios según su rol
package mx.wok.nimos.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.wok.nimos.models.Movimiento;
import mx.wok.nimos.models.Producto;
import mx.wok.nimos.models.Proveedor;
import mx.wok.nimos.models.Usuario;
import mx.wok.nimos.service.IProductoService;
import mx.wok.nimos.service.MovimientoService;
import mx.wok.nimos.service.PedidoService;
import mx.wok.nimos.service.ProveedorService;
import mx.wok.nimos.service.SucursalService;
import mx.wok.nimos.service.UsuarioService;

@Controller
public class HomeController {

    // Inyeccion de dependencias de los servicios principales
    @Autowired
    private IProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProveedorService proveedorService;

    // ==================== SECCION BODEGA ====================

    // Muestra el inventario general de productos en bodega
    @GetMapping("/bodega/productos")
    public String listarProductos(Model model) {
        model.addAttribute("pageTitle", "Inventario de Bodega");
        model.addAttribute("productos", productoService.todos());
        return "bodega/productos/indexProd";
    }

    // Muestra el historial de movimientos o envíos realizados
    @GetMapping("/envio/productos")
    public String listarEnvios(Model model) {
        model.addAttribute("pageTitle", "Historial de Movimientos");
        model.addAttribute("envios", movimientoService.todos());
        return "bodega/envios/indexEnv";
    }

    // Formulario para enviar un producto desde bodega hacia una sucursal
    @GetMapping("/bodega/envios/formEnviar/{id}")
    public String formEnviar(@PathVariable String id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("pageTitle", "Enviar producto a sucursal");
        return "bodega/envios/formEnviar";
    }

    // Vista principal del módulo de bodega
    @GetMapping("/bodega/home/")
    public String listarProductosBodega(Model model) {
        model.addAttribute("pageTitle", "Home de Bodega");
        return "bodega/homeProd";
    }

    // ==================== SECCION PROVEEDOR ====================

    // Lista productos con bajo nivel de stock para que proveedor genere pedido
    @GetMapping("/proveedor/articulos")
    public String listarArticulosBajos(Model model) {
        model.addAttribute("pageTitle", "Artículos bajos en inventario");
        model.addAttribute("productosBajos", productoService.obtenerProductosBajos());
        return "proveedor/productos/productosBajos";
    }

    // Vista principal del módulo proveedor
    @GetMapping("/proveedor/home/")
    public String listarProductosProveedor(Model model) {
        model.addAttribute("pageTitle", "Home de Proveedor");
        return "proveedor/homeProv";
    }

    // Crea una nueva solicitud de pedido al proveedor
    @PostMapping("/proveedor/pedidos/crear")
    @ResponseBody
    public String crearPedido(@RequestParam String productoId,
            @RequestParam String proveedorId,
            @RequestParam int cantidad) {
        pedidoService.crearSolicitud(productoId, proveedorId, cantidad);
        return "Pedido registrado correctamente";
    }

    // ==================== SECCION HOME GENERAL ====================

    // Vista por defecto sin datos, muestra mensaje base
    @GetMapping("/")
    public String sinDatos(Model model) {
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("mensaje", "Welcome to my page, be happy");
        return "sinDatos";
    }

    // ==================== SECCION AJUSTES DE BODEGA ====================

    // Formulario para editar o ajustar cantidad de un producto
    @GetMapping("/productos/formEditProd/{id}")
    public String goEditarProductoBodega(@PathVariable String id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("pageTitle", "Ajustar cantidad - Bodega");
        return "bodega/productos/formEditProd";
    }

    // Actualiza cantidad de producto en bodega y registra ajuste
    @PostMapping("/productos/ajustar")
    public String ajustarProducto(@ModelAttribute Producto producto,
            @RequestParam String arreglo,
            RedirectAttributes redirectAttrs) {
        productoService.actualizarCantidad(producto.getId(), producto.getCantidad(), arreglo);
        redirectAttrs.addFlashAttribute("mensaje", "Ajuste registrado correctamente");
        return "redirect:/bodega/productos";
    }

    // ==================== SECCION LOGIN Y AUTENTICACION ====================

    // Muestra la vista de inicio de sesion
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Login");
        return "login";
    }

    // Procesa las credenciales y redirige segun el rol del usuario
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
            @RequestParam String password, Model model) {

        Usuario usuario = usuarioService.validarLogin(correo, password);

        // Redirige al home correspondiente segun el rol del usuario
        if (usuario != null) {
            if ("BODEGA".equals(usuario.getRol())) {
                return "redirect:/bodega/home/";
            } else if ("PEDIDOS".equals(usuario.getRol())) {
                return "redirect:/proveedor/home/";
            } else if ("SUPERADMIN".equals(usuario.getRol())) {
                return "redirect:/admin/home/";
            }
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }

    // ==================== SECCION ENVIOS ====================

    // Formulario para crear un nuevo envío a sucursal
    @GetMapping("/bodega/envios/nuevo")
    public String nuevoEnvio(Model model) {
        model.addAttribute("pageTitle", "Nuevo Envío a Sucursal");
        model.addAttribute("envio", new Movimiento());
        model.addAttribute("sucursales", sucursalService.todas());
        model.addAttribute("productos", productoService.todos());
        return "bodega/envios/nuevEnviar";
    }

    // Registra un envío de producto y actualiza stock
    @PostMapping("/bodega/envios/guardar")
    public String guardarEnvio(@RequestParam String productoId,
            @RequestParam int cantidad,
            @RequestParam String sucursalDestino,
            RedirectAttributes redirectAttrs) {

        Producto producto = productoService.obtenerPorId(productoId);

        if (producto == null) {
            redirectAttrs.addFlashAttribute("error", "Producto no encontrado");
            return "redirect:/bodega/envios/indexEnv";
        }

        boolean restado = productoService.restarCantidad(productoId, cantidad);
        if (!restado) {
            redirectAttrs.addFlashAttribute("error", "Stock insuficiente para el envío");
            return "redirect:/bodega/envios/indexEnv";
        }

        // Crea registro del nuevo movimiento de envío
        Movimiento envio = new Movimiento();
        envio.setUsuarioId("u2");
        envio.setProveedorId("prov1");
        envio.setFecha(LocalDateTime.now());
        envio.setEstado("ENVIADO");
        envio.setProductos(List.of(producto));
        movimientoService.guardar(envio);

        redirectAttrs.addFlashAttribute("mensaje", "Envío registrado y stock actualizado correctamente");
        return "redirect:/envio/productos";
    }

    // ==================== SECCION PROVEEDORES ====================

    // Formulario de edicion de proveedor
    @GetMapping("/proveedor/editar/{id}")
    public String editarProveedor(@PathVariable String id, Model model) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("pageTitle", "Editar Proveedor");
        return "proveedor/productos/formEditarProv";
    }

    // Actualiza los datos del proveedor existente
    @PostMapping("/proveedor/actualizar")
    public String actualizarProveedor(@ModelAttribute Proveedor proveedor, RedirectAttributes redirect) {
        proveedorService.actualizar(proveedor);
        redirect.addFlashAttribute("success", "Datos del proveedor actualizados correctamente");
        return "redirect:/proveedor/lista";
    }

    // Lista todos los proveedores registrados
    @GetMapping("/proveedor/lista")
    public String listarProveedores(Model model) {
        model.addAttribute("pageTitle", "Lista de Proveedores");
        model.addAttribute("proveedores", proveedorService.listar());
        return "proveedor/productos/listaProveedores";
    }

    // ==================== SECCION ADMINISTRADOR ====================

    // Vista principal del administrador
    @GetMapping("/admin/home/")
    public String listarHomeAdmin(Model model) {
        model.addAttribute("pageTitle", "Home de Administrador");
        return "admin/homeAdmin";
    }

    // ---------- GESTION DE PROVEEDORES ----------

    // Lista proveedores desde el panel de administrador
    @GetMapping("/admin/proveedores")
    public String listarProveedoresAdmin(Model model) {
        model.addAttribute("pageTitle", "Gestión de Proveedores");
        model.addAttribute("proveedores", proveedorService.listar());
        return "admin/proveedores/proveedoresView";
    }

    // Formulario para registrar un nuevo proveedor
    @GetMapping("/admin/proveedores/nuevo")
    public String nuevoProveedorAdmin(Model model) {
        model.addAttribute("pageTitle", "Nuevo Proveedor");
        model.addAttribute("proveedor", new Proveedor());
        return "admin/proveedores/formNuevo";
    }

    // Guarda un nuevo proveedor en la base de datos
    @PostMapping("/admin/proveedores/guardar")
    public String guardarProveedorAdmin(@ModelAttribute Proveedor proveedor, RedirectAttributes redirectAttrs) {
        proveedorService.actualizar(proveedor);
        redirectAttrs.addFlashAttribute("success", "Proveedor creado correctamente");
        return "redirect:/admin/proveedores";
    }

    // Formulario para editar datos de un proveedor desde el panel admin
    @GetMapping("/admin/proveedores/editar/{id}")
    public String editarProveedorAdmin(@PathVariable String id, Model model, RedirectAttributes redirectAttrs) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        if (proveedor == null) {
            redirectAttrs.addFlashAttribute("error", "Proveedor no encontrado");
            return "redirect:/admin/proveedores";
        }
        model.addAttribute("pageTitle", "Editar Proveedor");
        model.addAttribute("proveedor", proveedor);
        return "admin/proveedores/formEditar";
    }

    // Actualiza un proveedor existente desde la vista de administrador
    @PostMapping("/admin/proveedores/actualizar")
    public String actualizarProveedorAdmin(@ModelAttribute Proveedor proveedor, RedirectAttributes redirectAttrs) {
        proveedorService.actualizar(proveedor);
        redirectAttrs.addFlashAttribute("success", "Proveedor actualizado correctamente");
        return "redirect:/admin/proveedores";
    }

    // Elimina un proveedor seleccionado desde el panel admin
    @PostMapping("/admin/proveedores/eliminar/{id}")
    public String eliminarProveedorAdmin(@PathVariable String id, RedirectAttributes redirectAttrs) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        if (proveedor == null) {
            redirectAttrs.addFlashAttribute("error", "Proveedor no encontrado");
            return "redirect:/admin/proveedores";
        }

        proveedorService.eliminar(id);
        redirectAttrs.addFlashAttribute("success", "Proveedor eliminado correctamente");
        return "redirect:/admin/proveedores";
    }

    // ---------- GESTION DE PRODUCTOS ----------

    // Lista de productos administrables
    @GetMapping("/admin/productos")
    public String listarProductosAdmin(Model model) {
        model.addAttribute("pageTitle", "Gestión de Productos");
        model.addAttribute("productos", productoService.todos());
        return "admin/productos/productosView";
    }

    // Formulario para crear un nuevo producto
    @GetMapping("/admin/productos/nuevo")
    public String nuevoProductoAdmin(Model model) {
        model.addAttribute("pageTitle", "Nuevo Producto");
        model.addAttribute("producto", new Producto());
        model.addAttribute("proveedores", proveedorService.listar());
        model.addAttribute("sucursales", sucursalService.todas());
        return "admin/productos/formNuevo";
    }

    // Guarda un nuevo producto desde la vista administrador
    @PostMapping("/admin/productos/guardar")
    public String guardarProductoAdmin(@ModelAttribute Producto producto, RedirectAttributes redirectAttrs) {
        productoService.guardar(producto);
        redirectAttrs.addFlashAttribute("success", "Producto registrado correctamente");
        return "redirect:/admin/productos";
    }

    // Formulario de edicion de producto en panel admin
    @GetMapping("/admin/productos/editar/{id}")
    public String editarProductoAdmin(@PathVariable String id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("pageTitle", "Editar Producto");
        model.addAttribute("proveedores", proveedorService.listar());
        model.addAttribute("sucursales", sucursalService.todas());
        return "admin/productos/formEditar";
    }

    // Elimina un producto desde la administracion
    @PostMapping("/admin/productos/eliminar/{id}")
    public String eliminarProductoAdmin(@PathVariable String id, RedirectAttributes redirectAttrs) {
        productoService.eliminar(id);
        redirectAttrs.addFlashAttribute("success", "Producto eliminado correctamente");
        return "redirect:/admin/productos";
    }
}
