package com.gestion.empleados.app.empleados;

public class Interno extends Empleado {

    public Interno(String nombre, double salario) {
        super(nombre, salario);
        System.out.println("Constructor de Interno llamado");
    }

    @Override
    public void calcularBono() {
        System.out.println("Sin bono disponible");
    }

}
