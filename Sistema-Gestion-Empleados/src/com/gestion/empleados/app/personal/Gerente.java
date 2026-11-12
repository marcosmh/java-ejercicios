package com.gestion.empleados.app.personal;

import com.gestion.empleados.app.bonificacion.Bonificable;

public class Gerente extends Empleado implements Bonificable {

    private double porcentajeBono;

    public Gerente(String nombre, String idEmpleado, double salarioBase, double porcentajeBono) {
        super(nombre, idEmpleado, salarioBase);
        this.porcentajeBono = porcentajeBono;
    }


    @Override
    public double calcularBonificacion() {
        return salarioBase * porcentajeBono;
    }

    @Override
    public double calcularSalarioTotal() {
        return salarioBase + calcularBonificacion();
    }
}
