package co.uniquindio.facade;

import co.uniquindio.adapter.ProcesadorPago;
import co.uniquindio.composite.IComponenteProducto;
import co.uniquindio.decorator.EmpaqueRegalo;
import co.uniquindio.decorator.GarantiaExtendida;
import co.uniquindio.decorator.ManejoFragil;
import co.uniquindio.proxy.ProxyPagoSeguridad;

public class ShopMasterFacade {

    private ProcesadorPago procesadorPago;

    public ShopMasterFacade() {
        this.procesadorPago = new ProxyPagoSeguridad();
    }

    public boolean ejecutarCompraRapida(String usuario, IComponenteProducto pedidoBase, ServicioExtra... extras) {
        System.out.println("\n==================================================================");
        System.out.println(" PROCESANDO CHECKOUT RÁPIDO PARA: " + usuario);
        System.out.println("==================================================================");

        System.out.println(" ÁRBOL DE PRODUCTOS:");
        pedidoBase.mostrarDetalle("   ");
        System.out.println(" Subtotal: $" + String.format("%.2f", pedidoBase.getPrecio()) + " COP");

        IComponenteProducto pedido = aplicarExtras(pedidoBase, extras);

        System.out.println("\n DESGLOSE CON SERVICIOS ADICIONALES:");
        pedido.mostrarDetalle("   ");

        double totalCOP = pedido.getPrecio();
        System.out.println("\n MONTO TOTAL A PAGAR: $" + String.format("%.2f", totalCOP) + " COP");

        System.out.println("\n ENVIANDO A PROCESAMIENTO DE PAGO...");
        boolean transaccionExitosa = procesadorPago.cobrarEnPesos(usuario, totalCOP);

        if (transaccionExitosa) {
            System.out.println("\n ESTADO COMPRA: Transacción procesada y completada con éxito.");
        } else {
            System.out.println("\n ESTADO COMPRA: Transacción rechazada por el sistema.");
        }
        System.out.println("==================================================================\n");
        return transaccionExitosa;
    }

    private IComponenteProducto aplicarExtras(IComponenteProducto pedido, ServicioExtra... extras) {
        for (ServicioExtra extra : extras) {
            switch (extra) {
                case GARANTIA_EXTENDIDA -> pedido = new GarantiaExtendida(pedido);
                case EMPAQUE_REGALO -> pedido = new EmpaqueRegalo(pedido);
                case MANEJO_FRAGIL -> pedido = new ManejoFragil(pedido);
            }
        }
        return pedido;
    }
}
