package co.uniquindio.model;

public class EnvioEstandar implements IEnvio{
    @Override
    public String getDescripcion() {
        return "Envio Estandar";
    }

    @Override
    public double getCosto() {
        return 10000.0;
    }
}
