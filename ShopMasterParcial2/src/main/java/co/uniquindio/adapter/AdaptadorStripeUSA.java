package co.uniquindio.adapter;

public class AdaptadorStripeUSA implements ProcesadorPago {

    private static final double TASA_CAMBIO_COP_USD = 4000.0; // 1 USD = 4000 COP
    private static final String CLAVE_COMERCIO = "SHOPMASTER-MERCHANT-KEY-2026";

    private StripeUSA stripe;

    public AdaptadorStripeUSA(StripeUSA stripe) {
        this.stripe = stripe;
    }

    @Override
    public boolean cobrarEnPesos(String usuario, double montoCOP) {
        double montoUSD = montoCOP / TASA_CAMBIO_COP_USD;

        System.out.println("   [ADAPTER] Convirtiendo $" + String.format("%.2f", montoCOP) + " COP a $"
                + String.format("%.2f", montoUSD) + " USD (TRM: 4000)...");

        return stripe.chargeInDollars(montoUSD, usuario, CLAVE_COMERCIO);
    }
}
