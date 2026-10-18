package co.uniquindio.composite;

import java.util.ArrayList;
import java.util.List;

public class ComboProducto implements IComponenteProducto {


        private String nombre;
        private List<IComponenteProducto> items;

        public ComboProducto(String nombre) {
            this.nombre = nombre;
            this.items = new ArrayList<>();
        }

        @Override
        public void agregar(IComponenteProducto componente) {
            items.add(componente);
        }

        @Override
        public String getNombre() {
            return nombre;
        }

        @Override
        public double getPrecio() {
            double total = 0;
            for (IComponenteProducto item : items) {
                total += item.getPrecio(); // Calculo recursivo
            }
            return total;
        }

        @Override
        public void mostrarDetalle(String indentacion) {
            System.out.println(indentacion + "📦 [COMBO] " + nombre + " (Total Combo: $" + String.format("%.2f", getPrecio()) + " COP)");
            for (IComponenteProducto item : items) {
                item.mostrarDetalle(indentacion + "   "); // Impresión recursiva con sangría
            }
        }
}
