package co.uniquindio.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Originador.
 * Es el objeto del cual queremos guardar y restaurar el estado.
 */
public class Carrito {
    private List<String> items = new ArrayList<>();

    public void agregarItem(String item) {
        items.add(item);
        System.out.println(" Item agregado al carrito: " + item);
    }

    public CarritoMemento guardar() {
        CarritoMemento mementoNuevo = new CarritoMemento(items);
        return mementoNuevo;
    }

    public void restaurar(CarritoMemento memento) {
        List<String> itemsGuardados = memento.getItems();
        this.items = itemsGuardados;
        System.out.println(" Carrito restaurado a un estado anterior.");
    }
}

