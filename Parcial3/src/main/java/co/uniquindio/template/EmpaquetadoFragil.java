package co.uniquindio.template;

public class EmpaquetadoFragil extends ProcesoEmpaquetado {
    @Override
    protected void protegerProducto() {
        System.out.println("   - Añadiendo plástico de burbujas triple (Producto Frágil).");
    }
}
