package co.uniquindio.mediator;

import co.uniquindio.memento.Carrito;
import co.uniquindio.strategy.EstrategiaEnvio;

public interface Mediador {
    void procesarCompra(Carrito carrito, EstrategiaEnvio envio);
}
