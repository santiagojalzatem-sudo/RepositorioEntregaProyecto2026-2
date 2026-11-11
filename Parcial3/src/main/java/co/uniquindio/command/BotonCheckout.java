package co.uniquindio.command;

public class BotonCheckout {
    private Comando comandoAsignado;

    public void setComando(Comando comando) {
        this.comandoAsignado = comando;
    }

    public void presionarBoton() {
        if (comandoAsignado != null) {
            comandoAsignado.ejecutar();
        }
    }
}
