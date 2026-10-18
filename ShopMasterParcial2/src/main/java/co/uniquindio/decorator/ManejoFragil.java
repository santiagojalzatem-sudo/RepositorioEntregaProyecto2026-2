package co.uniquindio.decorator;

import co.uniquindio.composite.IComponenteProducto;

public class ManejoFragil extends DecoradorProducto {

    public ManejoFragil(IComponenteProducto producto) {
        super(producto);
    }

    @Override
    public String getNombre() {
        return super.getNombre() + " + [Manejo Prioritario Frágil]";
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() + 25000.0; // Suma $25.000 fijos
    }

    @Override
    public void mostrarDetalle(String detalle) {
        super.mostrarDetalle(detalle);
        System.out.println(detalle + " Extra: Manejo Prioritario Frágil (+$25.000 COP)");
    }
}