package com.markcode.POOJava.concretas;

import com.markcode.POOJava.abstracto.Animal;
import com.markcode.POOJava.comportamiento.Acuatico;

public class Tiburon extends Animal implements Acuatico {

    public Tiburon(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() +  " -> no emite sonidos.");
    }


    @Override
    public void nadar() {
        System.out.println(getNombre() +" -> esta cazando en el oceano");

    }
}
