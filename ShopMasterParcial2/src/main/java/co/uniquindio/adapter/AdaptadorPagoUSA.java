package co.uniquindio.adapter;

public class AdaptadorPagoUSA implements PasarelaPagoLocal {

    private USAPaymentsAPI apiUSA;
    private static final double TASACAMBIOCOPAUSD = 4000.0; // 1 USD = 4000 COP

    public AdaptadorPagoUSA(USAPaymentsAPI apiUSA) {
        this.apiUSA = apiUSA;
    }

    @Override
    public boolean procesarPago(String usuario, double montoCOP) {
        // Conversión de moneda (COP -> USD)
        double montoUSD = montoCOP / TASACAMBIOCOPAUSD;

        System.out.println("   [ADAPTER] Convirtiendo $" + montoCOP + " COP a $" + String.format("%.2f", montoUSD) + " USD (TRM: 4000)...");

        // Delegación a la API externa
        return apiUSA.sendPaymentInUSD(usuario, montoUSD);
    }
}
