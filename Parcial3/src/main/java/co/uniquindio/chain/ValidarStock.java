package co.uniquindio.chain;

import co.uniquindio.memento.Carrito;

public class ValidarStock extends ManejadorValidacion {

    /**
     * Metodo que permite validar si hay stock suficiente.
     * @param carrito el carrito a evaluar
     * @return true si la validacion es exitosa
     */
    @Override
    public boolean validar(Carrito carrito) {
        System.out.println(" Validando Stock en inventario... OK");

        if (siguiente != null) {
            boolean resultadoSiguiente = siguiente.validar(carrito);
            return resultadoSiguiente;
        }

        return true; // Si no hay más validaciones, es válido
    }
}
