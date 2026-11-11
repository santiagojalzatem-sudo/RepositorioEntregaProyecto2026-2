package co.uniquindio.mediator;

import co.uniquindio.chain.ManejadorValidacion;
import co.uniquindio.memento.Carrito;
import co.uniquindio.observer.BodegaSuscrita;
import co.uniquindio.observer.ClienteSuscrito;
import co.uniquindio.observer.NotificadorPedido;
import co.uniquindio.state.Pedido;
import co.uniquindio.strategy.CalculadoraEnvio;
import co.uniquindio.strategy.EstrategiaEnvio;

public class MediadorEcommerce implements Mediador {
    private ManejadorValidacion validador;

    public MediadorEcommerce(ManejadorValidacion validador) {
        this.validador = validador;
    }

    @Override
    public void procesarCompra(Carrito carrito, EstrategiaEnvio envio) {

        boolean esValido = validador.validar(carrito);

        if (esValido) {
            CalculadoraEnvio calculador = new CalculadoraEnvio();
            calculador.setEstrategia(envio);

            double subtotal = 100.0;
            double tarifaCobrada = calculador.calcular(subtotal);
            double total = subtotal + tarifaCobrada;

            System.out.println("💰 Pago aprobado por el banco. Total cobrado: $" + total);

            NotificadorPedido notificador = new NotificadorPedido();
            notificador.suscribir(new ClienteSuscrito());
            notificador.suscribir(new BodegaSuscrita());

            Pedido pedido = new Pedido(notificador);

            pedido.avanzarEstado();
            pedido.avanzarEstado();
        }
    }
}
