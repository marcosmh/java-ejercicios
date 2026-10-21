package com.gestion.empleados.app.empleados;

public class Empleado {

    private String nombre;
    private double salario;

    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
        System.out.println("Constructor de Empleado llamado");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Salario: " + this.salario);
    }

    public void calcularBono() {
        System.out.println("Bono general: " + (this.salario * 0.5));
    }

    public static class Interno  {





    }
}
