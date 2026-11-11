package co.uniquindio.state;

public class EstadoPendiente implements EstadoPedido {
    @Override
    public void procesar(Pedido pedido) {
        System.out.println("🔄 Estado: PENDIENTE -> Transición a PAGADO.");
        EstadoPagado nuevoEstado = new EstadoPagado();
        pedido.setEstado(nuevoEstado);
    }
}
