package co.uniquindio.model;

public abstract class EnvioDecorator implements IEnvio{
    protected IEnvio envioDecorado;

    public EnvioDecorator(IEnvio envio) {
        this.envioDecorado = envio;
    }

    @Override
    public String getDescripcion() {
        return envioDecorado.getDescripcion();
    }

    @Override
    public double getCosto() {
        return envioDecorado.getCosto();
    }
}

