package com.gestion.empleados.app.personal;

public class Administrativo extends Empleado {

    public Administrativo(String nombre, String idEmpleado, double salarioBase) {
        super(nombre, idEmpleado, salarioBase);
    }

    @Override
    public double calcularSalarioTotal() {
        return salarioBase;
    }
}
