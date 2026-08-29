package com.markcode.POOJava.concretas;

import com.markcode.POOJava.abstracto.Animal;
import com.markcode.POOJava.comportamiento.Volador;

public class Aguila extends Animal implements Volador {

    public Aguila(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() +  " -> emite un chillido agudo");
    }

    @Override
    public void volar() {
        System.out.println(getNombre() + " -> esta planeando en las alturas");

    }
}
