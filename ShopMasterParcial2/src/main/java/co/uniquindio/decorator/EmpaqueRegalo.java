package co.uniquindio.decorator;

import co.uniquindio.composite.IComponenteProducto;

public class EmpaqueRegalo extends DecoradorProducto {

    public EmpaqueRegalo(IComponenteProducto producto) {
        super(producto);
    }

    @Override
    public String getNombre() {
        return super.getNombre() + " Empaque de Regalo";
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() + 15000; // Suma $15.000 fijos
    }

    @Override
    public void mostrarDetalle(String detalle) {
        super.mostrarDetalle(detalle);
        System.out.println(detalle + " Extra: Empaque de Regalo Premium ($15.000 COP)");
    }
}
