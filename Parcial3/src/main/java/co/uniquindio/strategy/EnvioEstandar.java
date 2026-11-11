package co.uniquindio.strategy;

public class EnvioEstandar implements EstrategiaEnvio {
    @Override
    public double calcularTarifa(double subtotal) {
        double tarifaFija = 5.0;
        return tarifaFija;
    }
}
