package co.uniquindio.adapter;

public class USAPaymentsAPI {
    public boolean sendPaymentInUSD(String accountName, double amountUSD) {
        System.out.println("   [USAPaymentsAPI] Cobro procesado exitosamente en EE.UU.");
        System.out.println("   -> Titular: " + accountName);
        System.out.println("   -> Monto final cobrado: $" + String.format("%.2f", amountUSD) + " USD");
        return true;
    }
}
