package co.uniquindio.composite;

public interface IComponenteProducto {

        String getNombre();

        double getPrecio();

        void mostrarDetalle(String detalle);

     default void agregar(IComponenteProducto componente) {
            throw new UnsupportedOperationException("No se pueden agregar productos a un artículo individual.");
        }
}
