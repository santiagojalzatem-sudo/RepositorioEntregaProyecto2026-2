package co.uniquindio.state;

public class EstadoEnviado implements EstadoPedido {
    @Override
    public void procesar(Pedido pedido) {
        System.out.println("🔄 Estado: ENVIADO -> El paquete ya está en tránsito.");
    }
}
