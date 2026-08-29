package com.markcode.POOJava.concretas;

import com.markcode.POOJava.abstracto.Animal;
import com.markcode.POOJava.comportamiento.Terrestre;

public class Perro extends Animal implements Terrestre {

    public Perro(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() +  " -> dice Guau!!");
    }

    @Override
    public void caminar() {
        System.out.println(getNombre() +  " -> esta corriendo por el parque");

    }

}
