package co.uniquindio.facade;

import co.uniquindio.adapter.AdaptadorPagoUSA;
import co.uniquindio.adapter.PasarelaPagoLocal;
import co.uniquindio.adapter.USAPaymentsAPI;
import co.uniquindio.composite.IComponenteProducto;
import co.uniquindio.proxy.ProxyPagoSeguridad;

public class ShopMasterFacade {


        // Subsistemas internos coordinados por la Fachada[cite: 17, 18]
        private USAPaymentsAPI apiUSA;
        private PasarelaPagoLocal adaptadorPago;
        private PasarelaPagoLocal proxySeguridad;

        // Constructor: Inicializa la cadena de subsistemas internamente[cite: 17, 18]
        public ShopMasterFacade() {
            this.apiUSA = new USAPaymentsAPI();
            this.adaptadorPago = new AdaptadorPagoUSA(apiUSA);
            this.proxySeguridad = new ProxyPagoSeguridad(adaptadorPago);
        }


        public void ejecutarCompraRapida(String usuario, IComponenteProducto pedido) {
            System.out.println("\n==================================================================");
            System.out.println(" PROCESANDO CHECKOUT RÁPIDO PARA: " + usuario);
            System.out.println("==================================================================");

            // 1. Desglose del pedido (Composite / Decorator)
            System.out.println(" DESGLOSE DEL PEDIDO:");
            pedido.mostrarDetalle("   ");

            double totalCOP = pedido.getPrecio();
            System.out.println("\n MONTO TOTAL A PAGAR: $" + String.format("%.2f", totalCOP) + " COP");

            // 2. Procesamiento coordinado a través de la cadena de seguridad y pago
            System.out.println("\n ENVIANDO A PROCESAMIENTO DE PAGO...");
            boolean transaccionExitosa = proxySeguridad.procesarPago(usuario, totalCOP);

            // 3. Confirmación final
            if (transaccionExitosa) {
                System.out.println("\n ESTADO COMPRA: Transacción procesada y completada con éxito.");
            } else {
                System.out.println("\n ESTADO COMPRA: Transacción rechazada por el sistema.");
            }
            System.out.println("==================================================================\n");
        }
    }

