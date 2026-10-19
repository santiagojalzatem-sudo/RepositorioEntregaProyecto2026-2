package co.uniquindio.decorator;

import co.uniquindio.composite.IComponenteProducto;

public class GarantiaExtendida extends DecoradorProducto {

    public GarantiaExtendida(IComponenteProducto producto) {
        super(producto);
    }

    @Override
    public String getNombre() {
        return super.getNombre() + " Garantía Extendida (10%)";
    }

    @Override
    public double getPrecio() {
        double base = super.getPrecio();
        return base + (base * 0.10); // Suma el 10%
    }

    @Override
    public void mostrarDetalle(String detalle) {
        super.mostrarDetalle(detalle);
        System.out.println(detalle + " Extra: Póliza de Garantía Extendida (10%)");
    }
}

