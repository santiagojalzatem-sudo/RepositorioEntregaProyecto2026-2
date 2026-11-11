package co.uniquindio.template;

public abstract class ProcesoEmpaquetado {

    public final void prepararPaquete() {
        buscarCaja();
        protegerProducto();
        etiquetar();
        System.out.println("✔️ Paquete listo para envío físico.");
    }

    private void buscarCaja() {
        System.out.println("   - Buscando caja de cartón estándar...");
    }

    private void etiquetar() {
        System.out.println("   - Pegando etiqueta de transporte...");
    }

    protected abstract void protegerProducto();
}
