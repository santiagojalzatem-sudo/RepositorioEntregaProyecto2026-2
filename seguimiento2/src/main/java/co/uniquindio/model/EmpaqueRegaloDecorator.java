package co.uniquindio.model;

public class EmpaqueRegaloDecorator extends EnvioDecorator{

    public EmpaqueRegaloDecorator(IEnvio envio) {
        super(envio);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Empaque de Regalo";
    }

    @Override
    public double getCosto() {
        return super.getCosto() + 5000.0;
    }
}

