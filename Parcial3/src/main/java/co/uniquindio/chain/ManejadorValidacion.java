package co.uniquindio.chain;

import co.uniquindio.memento.Carrito;

public abstract class ManejadorValidacion {
    protected ManejadorValidacion siguiente;

    public void setSiguiente(ManejadorValidacion siguiente) {
        this.siguiente = siguiente;
    }

    public abstract boolean validar(Carrito carrito);
}

