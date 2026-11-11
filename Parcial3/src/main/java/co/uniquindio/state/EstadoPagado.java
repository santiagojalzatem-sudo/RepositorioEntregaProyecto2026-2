package co.uniquindio.state;

import co.uniquindio.template.EmpaquetadoFragil;
import co.uniquindio.template.ProcesoEmpaquetado;

public class EstadoPagado implements EstadoPedido {
    @Override
    public void procesar(Pedido pedido) {
        System.out.println("🔄 Estado: PAGADO -> Transición a ENVIADO. Activando logística...");

        ProcesoEmpaquetado logistica = new EmpaquetadoFragil();
        logistica.prepararPaquete();

        EstadoEnviado nuevoEstado = new EstadoEnviado();
        pedido.setEstado(nuevoEstado);
    }
}