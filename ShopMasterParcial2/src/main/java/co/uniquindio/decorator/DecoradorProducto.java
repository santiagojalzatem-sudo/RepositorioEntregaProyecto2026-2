package co.uniquindio.decorator;

import co.uniquindio.composite.IComponenteProducto;

public abstract class DecoradorProducto implements IComponenteProducto {
    protected IComponenteProducto productoDecorado;

    public DecoradorProducto(IComponenteProducto producto) {
        this.productoDecorado = producto;
    }

    @Override
    public String getNombre() {
        return productoDecorado.getNombre();
    }

    @Override
    public double getPrecio() {
        return productoDecorado.getPrecio();
    }

    @Override
    public void mostrarDetalle(String detalle) {
        productoDecorado.mostrarDetalle(detalle);
    }
}
