package com.gestion.empleados.app.personal;

import com.gestion.empleados.app.bonificacion.Bonificable;

public class Operativo extends Empleado implements Bonificable {

    private double bonoFijo;

    public Operativo(String nombre, String idEmpleado, double salarioBase, double bonoFijo) {
        super(nombre, idEmpleado, salarioBase);
        this.bonoFijo = bonoFijo;
    }

    @Override
    public double calcularBonificacion() {
        return bonoFijo;
    }

    @Override
    public double calcularSalarioTotal() {
        return salarioBase *  calcularBonificacion();
    }
}
