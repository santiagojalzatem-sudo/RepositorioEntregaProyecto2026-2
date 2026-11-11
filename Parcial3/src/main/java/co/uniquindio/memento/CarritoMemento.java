package co.uniquindio.memento;

import java.util.ArrayList;
import java.util.List;

/**
     * Clase Memento. Almacena el estado interno del Carrito de forma inmutable.
     * Resuelve el problema de "Deshacer" sin exponer los detalles de implementación del carrito.
     */
    public class CarritoMemento {
        private final List<String> items;

        public CarritoMemento(List<String> items) {
            // Se crea una copia exacta para evitar modificaciones por referencia
            this.items = new ArrayList<>(items);
        }

        public List<String> getItems() {
            return items;
        }
    }

