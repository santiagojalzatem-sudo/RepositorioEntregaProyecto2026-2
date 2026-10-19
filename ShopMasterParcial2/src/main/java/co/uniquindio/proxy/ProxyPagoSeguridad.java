package co.uniquindio.proxy;

import co.uniquindio.adapter.AdaptadorStripeUSA;
import co.uniquindio.adapter.ProcesadorPago;
import co.uniquindio.adapter.StripeUSA;

public class ProxyPagoSeguridad implements ProcesadorPago {

    private static final double MONTO_MAXIMO_COP = 5_000_000.0;
    private static final String USUARIO_BLOQUEADO = "BLOQUEADO";

    // El procesador real solo se crea cuando una transacción pasa los controles
    private ProcesadorPago procesadorReal;

    @Override
    public boolean cobrarEnPesos(String usuario, double montoCOP) {
        System.out.println("   [PROXY] Validando transacción de " + usuario + "...");

        if (USUARIO_BLOQUEADO.equals(usuario)) {
            System.out.println("   [PROXY] ALERTA: Transacción rechazada. El usuario '" + usuario + "' está reportado como BLOQUEADO.");
            return false;
        }

        if (montoCOP > MONTO_MAXIMO_COP) {
            System.out.println("   [PROXY] ALERTA: Transacción rechazada. El monto $" + String.format("%.2f", montoCOP)
                    + " COP supera el límite de $" + String.format("%.2f", MONTO_MAXIMO_COP) + " COP.");
            return false;
        }

        if (procesadorReal == null) {
            procesadorReal = new AdaptadorStripeUSA(new StripeUSA());
        }

        boolean aprobado = procesadorReal.cobrarEnPesos(usuario, montoCOP);
        if (aprobado) {
            System.out.println("   [AUDITORÍA: Transacción aprobada para " + usuario + " por $"
                    + String.format("%.2f", montoCOP) + " COP]");
        }
        return aprobado;
    }
}
