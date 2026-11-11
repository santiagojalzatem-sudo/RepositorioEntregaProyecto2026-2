package co.uniquindio;

import co.uniquindio.chain.ManejadorValidacion;
import co.uniquindio.chain.ValidarFraude;
import co.uniquindio.chain.ValidarStock;
import co.uniquindio.command.BotonCheckout;
import co.uniquindio.command.Comando;
import co.uniquindio.command.ComandoComprar;
import co.uniquindio.mediator.Mediador;
import co.uniquindio.mediator.MediadorEcommerce;
import co.uniquindio.memento.Carrito;
import co.uniquindio.memento.CarritoMemento;
import co.uniquindio.strategy.EnvioExpress;
import co.uniquindio.strategy.EstrategiaEnvio;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("--- SIMULADOR E-COMMERCE CON PATRONES DE DISEÑO ---\n");

        Carrito carrito = new Carrito();
        carrito.agregarItem("Monitor 144Hz");

        CarritoMemento respaldo = carrito.guardar();
        carrito.agregarItem("Teclado Mecánico (Error)");
        carrito.restaurar(respaldo);

        ManejadorValidacion validadorStock = new ValidarStock();
        ManejadorValidacion validadorFraude = new ValidarFraude();
        validadorStock.setSiguiente(validadorFraude);

        Mediador mediador = new MediadorEcommerce(validadorStock);

        EstrategiaEnvio estrategia = new EnvioExpress();
        Comando comandoDeCompra = new ComandoComprar(mediador, carrito, estrategia);

        BotonCheckout boton = new BotonCheckout();
        boton.setComando(comandoDeCompra);

        boton.presionarBoton();
    }
}