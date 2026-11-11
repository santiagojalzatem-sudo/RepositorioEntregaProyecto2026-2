package co.uniquindio.observer;

public class BodegaSuscrita implements ObservadorPedido {
    @Override
    public void actualizar(String estado) {
        boolean esPagado = estado.equals("PAGADO");
        if (esPagado) {
            System.out.println("📦 [Alerta Bodega]: Pago confirmado. Iniciar preparación logística.");
        }
    }
}
