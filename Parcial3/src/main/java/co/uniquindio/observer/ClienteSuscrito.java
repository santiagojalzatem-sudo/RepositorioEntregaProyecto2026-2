package co.uniquindio.observer;

public class ClienteSuscrito implements ObservadorPedido{
        @Override
        public void actualizar(String estado) {
            System.out.println(" Notificación al Cliente: Tu pedido ahora está " + estado);
        }
}
