package com.gestion.empleados.app;

import com.gestion.empleados.app.bonificacion.Bonificable;
import com.gestion.empleados.app.personal.Administrativo;
import com.gestion.empleados.app.personal.Empleado;
import com.gestion.empleados.app.personal.Gerente;
import com.gestion.empleados.app.personal.Operativo;

public class Principal {

    public static void main(String[] args) {

        Empleado[] empleados = new  Empleado[3];
        empleados[0] = new Administrativo("Juan Perez","EMP-001",3000.0);
        empleados[1] = new Operativo("Carlos Gomez","EMP-002",1800.0,350.0);
        empleados[2] = new Gerente("Santiago Lopez","EMP-003",5500.0,0.20);

        for(Empleado empleado : empleados) {
            empleado.mostrarInformacion();
            System.out.println("Salario Total $ " + empleado.calcularSalarioTotal());


            if(empleado instanceof Bonificable) {
                Bonificable bonificable = (Bonificable) empleado;
                System.out.println("Bonificacion aplicada $ " + bonificable.calcularBonificacion() );
            } else {
                System.out.println("Este empleado no recibe bonificacion ");
            }
            System.out.println("--------------------------------\n");

        }
    }
}
