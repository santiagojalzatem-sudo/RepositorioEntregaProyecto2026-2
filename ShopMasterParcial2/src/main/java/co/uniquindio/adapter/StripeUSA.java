package co.uniquindio.adapter;

// Clase de proveedor externo: no se puede modificar
public class StripeUSA {
    public boolean chargeInDollars(double amountUSD, String customerEmail, String merchantKey) {
        System.out.println("   [StripeUSA] Charge processed successfully in USA.");
        System.out.println("   -> Customer: " + customerEmail);
        System.out.println("   -> Merchant key: " + merchantKey);
        System.out.println("   -> Amount charged: $" + String.format("%.2f", amountUSD) + " USD");
        return true;
    }
}
