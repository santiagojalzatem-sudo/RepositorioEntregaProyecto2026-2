package co.uniquindio.composite;

public class ProductoSimple implements IComponenteProducto {

    private String nombre;
    private double precio;

    public ProductoSimple(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void mostrarDetalle(String indentacion) {
        System.out.println(indentacion + "• Producto: " + nombre + " - $" + String.format("%.2f", precio) + " COP");
    }
}

