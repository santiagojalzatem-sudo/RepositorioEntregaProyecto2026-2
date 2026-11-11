package co.uniquindio.command;

import co.uniquindio.mediator.Mediador;
import co.uniquindio.memento.Carrito;
import co.uniquindio.strategy.EstrategiaEnvio;

public class ComandoComprar implements Comando {
    private Mediador mediador;
    private Carrito carrito;
    private EstrategiaEnvio envio;

    public ComandoComprar(Mediador mediador, Carrito carrito, EstrategiaEnvio envio) {
        this.mediador = mediador;
        this.carrito = carrito;
        this.envio = envio;
    }

    @Override
    public void ejecutar() {
        System.out.println("\n>>> INICIANDO FLUJO COMANDO COMPRAR <<<");
        mediador.procesarCompra(carrito, envio);
    }
}
