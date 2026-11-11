package co.uniquindio.observer;

import java.util.ArrayList;
import java.util.List;

public class NotificadorPedido {
    private List<ObservadorPedido> observadores;

    public NotificadorPedido() {
        this.observadores = new ArrayList<>();
    }

    public void suscribir(ObservadorPedido obs) {
        this.observadores.add(obs);
    }

    public void notificar(String estado) {
        for (int i = 0; i < observadores.size(); i++) {
            ObservadorPedido obs = observadores.get(i);
            obs.actualizar(estado);
        }
    }
}
