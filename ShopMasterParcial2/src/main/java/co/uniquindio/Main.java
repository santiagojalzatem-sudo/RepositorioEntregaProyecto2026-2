package co.uniquindio;

import co.uniquindio.composite.ComboProducto;
import co.uniquindio.composite.IComponenteProducto;
import co.uniquindio.composite.ProductoSimple;
import co.uniquindio.facade.ServicioExtra;
import co.uniquindio.facade.ShopMasterFacade;

public class Main {
    public static void main(String[] args) {
        ShopMasterFacade shopMaster = new ShopMasterFacade();

        // Caso de Prueba 1: Compra exitosa completa
        IComponenteProducto kitLimpieza = new ComboProducto("Kit de Limpieza");
        kitLimpieza.agregar(new ProductoSimple("Limpiador de Pantalla", 25000));
        kitLimpieza.agregar(new ProductoSimple("Paño de Microfibra", 15000));

        IComponenteProducto comboGamer = new ComboProducto("Combo Setup Gamer");
        comboGamer.agregar(new ProductoSimple("Monitor", 800000));
        comboGamer.agregar(new ProductoSimple("Teclado", 200000));
        comboGamer.agregar(kitLimpieza);

        shopMaster.ejecutarCompraRapida("cliente_vip", comboGamer,
                ServicioExtra.GARANTIA_EXTENDIDA, ServicioExtra.EMPAQUE_REGALO);

        // Caso de Prueba 2: Rechazo por tope de dinero
        IComponenteProducto servidor = new ProductoSimple("Servidor Empresarial", 6000000);
        shopMaster.ejecutarCompraRapida("cliente_normal", servidor, ServicioExtra.MANEJO_FRAGIL);

        // Caso de Prueba 3: Rechazo por usuario reportado
        IComponenteProducto mouse = new ProductoSimple("Mouse", 150000);
        shopMaster.ejecutarCompraRapida("BLOQUEADO", mouse);
    }
}
