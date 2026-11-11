package co.uniquindio.strategy;

public class CalculadoraEnvio {
    private EstrategiaEnvio estrategia;

    public void setEstrategia(EstrategiaEnvio estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(double subtotal) {
        double totalEnvio = estrategia.calcularTarifa(subtotal);
        return totalEnvio;
    }
}

