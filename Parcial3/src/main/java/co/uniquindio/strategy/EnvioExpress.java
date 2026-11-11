package co.uniquindio.strategy;

public class EnvioExpress implements EstrategiaEnvio {
        @Override
        public double calcularTarifa ( double subtotal){
        double recargo = subtotal * 0.15;
        return recargo;
    }
}

