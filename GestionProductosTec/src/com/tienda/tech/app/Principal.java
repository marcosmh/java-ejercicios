package com.tienda.tech.app;

import com.tienda.tech.app.productos.*;

public class Principal {

    public static void main(String[] args) {

        Producto[] productos = new Producto[6];

        productos[0] = new Laptop("MacBook Pro", 5, 28000.10, "LAP-001", "Apple M3", 16, "macOS");
        productos[1] = new Smartphone("Galaxy S24", 10, 19500.00, "CEL-002", 6.2, "Android", true);
        productos[2] = new Tablet("iPad Air", 8, 14000.00, "TAB-003", 256, true, "Liquid Retina");
        productos[3] = new Laptop("ThinkPad E14", 4, 15500.00, "LAP-004", "Intel Core i5", 16, "Windows 11");
        productos[4] = new Accesorio("Mouse Logitech MX Master 3S", 15, 1800.00, "ACC-005", "Mouse", true, "Logitech");
        productos[5] = new Accesorio("Teclado Mecánico Keychron", 7, 2200.00, "ACC-006", "Teclado", false, "Keychron");

        System.out.println("=========================================");
        System.out.println("     INVENTARIO DE TIENDA TECH           ");
        System.out.println("=========================================\n");

        for (Producto producto : productos) {
            if (producto != null) {
                producto.mostrarInformacion();
                System.out.printf("Precio con IVA: $%.2f%n", producto.calcularPrecioConIVA());
                System.out.println("-----------------------------------------");
            }
        }

    }


}
