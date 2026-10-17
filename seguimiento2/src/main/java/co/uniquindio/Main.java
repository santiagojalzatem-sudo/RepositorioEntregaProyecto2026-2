package co.uniquindio;

import co.uniquindio.model.EmpaqueRegaloDecorator;
import co.uniquindio.model.EnvioEstandar;
import co.uniquindio.model.IEnvio;
import co.uniquindio.model.SeguroDecorator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA PATRON DECORATOR ===");

        // 1. Envío base estándar
        IEnvio envioBase = new EnvioEstandar();
        System.out.println("1. " + envioBase.getDescripcion() + " -> Total: $" + envioBase.getCosto());

        // 2. Envío decorado con empaque de regalo
        IEnvio envioConRegalo = new EmpaqueRegaloDecorator(envioBase);
        System.out.println("2. " + envioConRegalo.getDescripcion() + " -> Total: $" + envioConRegalo.getCosto());

        // 3. Envío decorado dinámicamente con regalo Y seguro
        IEnvio envioCompleto = new SeguroDecorator(envioConRegalo);
        System.out.println("3. " + envioCompleto.getDescripcion() + " -> Total: $" + envioCompleto.getCosto());
    }
}