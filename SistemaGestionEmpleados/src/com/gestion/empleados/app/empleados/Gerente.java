package com.gestion.empleados.app.empleados;

public class Gerente extends  Empleado{

    private String departamento;

    public Gerente(String nombre, double salario, String departamento) {
        super(nombre, salario);
        this.departamento = departamento;
        System.out.println("Constructor de Gerente llamado");
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Departamento: " + this.departamento);
    }

    @Override
    public void calcularBono() {
        System.out.println("Bono de gerente: " + (this.getSalario() * 0.10));
    }
}
