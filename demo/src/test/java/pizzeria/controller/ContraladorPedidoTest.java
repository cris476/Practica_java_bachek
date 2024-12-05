// package pizzeria.controller;

// import static org.junit.jupiter.api.Assertions.*;

// import java.sql.SQLException;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import pizzeria.Controller.ContraladorPedido;
// import pizzeria.Modelo.EstadoPedido;
// import pizzeria.Modelo.Pedido;
// import pizzeria.Modelo.Producto;

// public class ContraladorPedidoTest {

// private ContraladorPedido controladorPedido;

// @BeforeEach
// void setUp() throws ClassNotFoundException, SQLException {
// controladorPedido = new ContraladorPedido();
// }

// @Test
// void testAddCarrito() throws ClassNotFoundException, SQLException {
// // Crear un pedido
// Pedido pedido = controladorPedido.savePedido(1); // Cliente con ID 1
// Producto producto = new Producto("Producto Test", 10.0);

// // Agregar producto al carrito
// controladorPedido.addCarrito(pedido, producto, 3); // Cantidad 3

// // Verificar que el producto está en el carrito
// Pedido pedidoConProductos =
// controladorPedido.obtenerPedidoPorId(pedido.getId());
// assertNotNull(pedidoConProductos);
// assertEquals(1, pedidoConProductos.getListaLineaPedidos().size());
// assertEquals("Producto Test",
// pedidoConProductos.getListaLineaPedidos().get(0).getProducto().getNombre());
// assertEquals(3,
// pedidoConProductos.getListaLineaPedidos().get(0).getCantidad());
// }

// @Test
// void testCancelarPedido() throws ClassNotFoundException, SQLException {
// // Crear y guardar un pedido
// Pedido pedido = controladorPedido.crearPedido(1);
// controladorPedido.savePedido(pedido);

// // Cancelar el pedido
// controladorPedido.cancelarPedido(pedido.getId());

// // Verificar que el estado del pedido sea "CANCELADO"
// Pedido pedidoCancelado =
// controladorPedido.obtenerPedidoPorId(pedido.getId());
// assertEquals(EstadoPedido.CANCELADO, pedidoCancelado.getEstado());
// }

// @Test
// void testEliminarPedido() throws ClassNotFoundException, SQLException {
// // Crear y guardar un pedido
// Pedido pedido = controladorPedido.crearPedido(1);
// controladorPedido.savePedido(pedido);

// // Eliminar el pedido
// controladorPedido.eliminarPedido(pedido.getId());

// // Verificar que el pedido ya no existe
// Pedido pedidoEliminado =
// controladorPedido.obtenerPedidoPorId(pedido.getId());
// assertNull(pedidoEliminado);
// }

// @Test
// void testEntregarPedido() throws ClassNotFoundException, SQLException {
// // Crear y guardar un pedido
// Pedido pedido = controladorPedido.crearPedido(1);
// controladorPedido.savePedido(pedido);

// // Cambiar estado a "ENTREGADO"
// controladorPedido.entregarPedido(pedido.getId());

// // Verificar que el estado del pedido sea "ENTREGADO"
// Pedido pedidoEntregado =
// controladorPedido.obtenerPedidoPorId(pedido.getId());
// assertEquals(EstadoPedido.ENTREGADO, pedidoEntregado.getEstado());
// }

// @Test
// void testFinalizarPedido() throws ClassNotFoundException, SQLException {
// // Crear y guardar un pedido
// Pedido pedido = controladorPedido.crearPedido(1);
// controladorPedido.savePedido(pedido);

// // Cambiar estado a "FINALIZADO"
// controladorPedido.finalizarPedido(pedido.getId());

// // Verificar que el estado del pedido sea "FINALIZADO"
// Pedido pedidoFinalizado =
// controladorPedido.obtenerPedidoPorId(pedido.getId());
// assertEquals(EstadoPedido.FINALIZADO, pedidoFinalizado.getEstado());
// }

// @Test
// void testSavePedido() throws ClassNotFoundException, SQLException {
// // Crear un pedido
// Pedido pedido = controladorPedido.crearPedido(1);

// // Guardar el pedido
// controladorPedido.savePedido(pedido);

// // Verificar que el pedido se guardó correctamente
// Pedido pedidoGuardado = controladorPedido.obtenerPedidoPorId(pedido.getId());
// assertNotNull(pedidoGuardado);
// assertEquals(pedido.getId(), pedidoGuardado.getId());
// }
// }
