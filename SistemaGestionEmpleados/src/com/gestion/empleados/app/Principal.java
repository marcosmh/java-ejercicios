package com.gestion.empleados.app;

import com.gestion.empleados.app.empleados.Desarrollador;
import com.gestion.empleados.app.empleados.Empleado;
import com.gestion.empleados.app.empleados.Gerente;
import com.gestion.empleados.app.empleados.Interno;

public class Principal {

    public static void main(String[] args) {

        Empleado emp = new Empleado("Laura",30000);
        Gerente ger = new Gerente("Carlos",50000,"Ventas");
        Desarrollador dev = new Desarrollador("Sofia",40000,"Java");

        Interno inter = new Interno("Jose",41000);

        System.out.println("\n=== Informacion de Empleado ===");
        emp.mostrarInformacion();
        emp.calcularBono();

        System.out.println("\n=== Informacion de Gerente ===");
        ger.mostrarInformacion();
        ger.calcularBono();

        System.out.println("\n=== Informacion de Gerente ===");
        dev.mostrarInformacion();
        dev.calcularBono();

        System.out.println("\n=== Informacion de Interno ===");
        inter.mostrarInformacion();
        inter.calcularBono();

        System.out.println("\n========================================");
        System.out.println("=== EMPLEADOS ===");
        System.out.println("========================================");

        Empleado[] empleados = { emp, ger, dev, inter };
        for(int i = 0; i < empleados.length; i++) {
            System.out.println("\n--- Empleado " + (i + 1) + " ---");
            empleados[i].mostrarInformacion();
            empleados[i].calcularBono();
        }


    }
}
