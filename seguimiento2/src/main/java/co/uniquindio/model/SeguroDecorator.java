package co.uniquindio.model;

public class SeguroDecorator extends EnvioDecorator{
    public SeguroDecorator(IEnvio envio) {
        super(envio);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Seguro Contra Robo";
    }

    @Override
    public double getCosto() {
        return super.getCosto() + 12000.0;
    }
}

