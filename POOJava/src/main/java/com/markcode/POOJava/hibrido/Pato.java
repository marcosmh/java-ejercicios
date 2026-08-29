package com.markcode.POOJava.hibrido;

import com.markcode.POOJava.abstracto.Animal;
import com.markcode.POOJava.comportamiento.Acuatico;
import com.markcode.POOJava.comportamiento.Terrestre;
import com.markcode.POOJava.comportamiento.Volador;

public class Pato extends Animal implements Terrestre, Volador, Acuatico {

    public Pato(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " -> dice cuac!");
    }

    @Override
    public void caminar() {
        System.out.println(getNombre() + " -> camina torpemente en la orilla.");
    }

    @Override
    public void volar() {
        System.out.println(getNombre() + " -> vuela en formacion con su banda.");

    }

    @Override
    public void nadar() {
        System.out.println(getNombre() + " -> flota pacificamente en el lago.");

    }
}
