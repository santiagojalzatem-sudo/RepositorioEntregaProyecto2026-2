package co.uniquindio.chain;

import co.uniquindio.memento.Carrito;

public class ValidarFraude extends ManejadorValidacion {

    /**
     * Metodo que permite validar si la tarjeta es segura.
     * @param carrito el carrito a evaluar
     * @return true si la validacion es exitosa
     */
    @Override
    public boolean validar(Carrito carrito) {
        System.out.println(" Validando Fraude bancario... OK");

        if (siguiente != null) {
            boolean resultadoSiguiente = siguiente.validar(carrito);
            return resultadoSiguiente;
        }

        return true;
    }
}
