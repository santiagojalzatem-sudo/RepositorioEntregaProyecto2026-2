package co.uniquindio.state;

import co.uniquindio.observer.NotificadorPedido;

public class Pedido {
    private EstadoPedido estado;
    private NotificadorPedido notificador;

    public Pedido(NotificadorPedido notificador) {
        this.estado = new EstadoPendiente();
        this.notificador = notificador;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;

        String nombreClase = estado.getClass().getSimpleName();
        String nombreEstado = nombreClase.replace("Estado", "").toUpperCase();

        notificador.notificar(nombreEstado);
    }

    public void avanzarEstado() {
        estado.procesar(this);
    }
}
