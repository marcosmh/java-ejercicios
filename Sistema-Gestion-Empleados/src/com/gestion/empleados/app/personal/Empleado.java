package com.gestion.empleados.app.personal;

public abstract class Empleado {

    protected String nombre;
    protected String idEmpleado;
    protected double salarioBase;

    public Empleado(String nombre, String idEmpleado, double salarioBase) {
        this.nombre = nombre;
        this.idEmpleado = idEmpleado;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalarioTotal();

    public void mostrarInformacion() {
        System.out.println("\n=== Empleado ===");
        System.out.println("ID: " + this.idEmpleado);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Salario Base $ " + this.salarioBase);
        System.out.println("\n");

    }


}
