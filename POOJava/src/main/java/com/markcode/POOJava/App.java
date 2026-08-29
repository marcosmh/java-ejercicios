package com.markcode.POOJava;


import com.markcode.POOJava.abstracto.Animal;
import com.markcode.POOJava.comportamiento.Acuatico;
import com.markcode.POOJava.comportamiento.Terrestre;
import com.markcode.POOJava.comportamiento.Volador;
import com.markcode.POOJava.concretas.Aguila;
import com.markcode.POOJava.concretas.Perro;
import com.markcode.POOJava.concretas.Tiburon;
import com.markcode.POOJava.hibrido.Pato;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        List<Animal> zoologico = new ArrayList<>();
        zoologico.add(new Perro("Firulais"));
        zoologico.add(new Aguila("Majestad"));
        zoologico.add((new Tiburon("Mandibulin")));
        zoologico.add(new Pato("Lucas"));

        for(Animal animal : zoologico) {
            animal.hacerSonido();

            if(animal instanceof Terrestre) {
                ((Terrestre) animal).caminar();
            }

            if(animal instanceof Volador) {
                ((Volador) animal).volar();
            }

            if(animal instanceof Acuatico) {
                ((Acuatico) animal).nadar();
            }


        }


    }


}
