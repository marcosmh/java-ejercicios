package com.gestion.empleados.app.empleados;

public class Desarrollador extends Empleado {

    private String lenguaje;

    public Desarrollador(String nombre, double salario, String lenguaje) {
        super(nombre, salario);
        this.lenguaje = lenguaje;
        System.out.println("Constructor de Desarrollador llamado");
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Lenguaje principal: " + this.lenguaje);
    }


}
