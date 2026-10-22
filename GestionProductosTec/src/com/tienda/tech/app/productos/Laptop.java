package com.tienda.tech.app.productos;

public class Laptop extends Producto {

    private String procesador;
    private int memoriaRAM;
    private String sistemaOperativo;

    public Laptop(String nombre, int stock, double precio, String codigoInterno, String procesador, int memoriaRAM, String sistemaOperativo) {
        super(nombre, stock, precio, codigoInterno);
        this.procesador = procesador;
        this.memoriaRAM = memoriaRAM;
        this.sistemaOperativo = sistemaOperativo;
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("--- LAPTOP ---");
        super.mostrarInformacion();
        System.out.println("Procesador: " + procesador);
        System.out.println("Memoria RAM: " + memoriaRAM + " GB");
        System.out.println("Sistema Operativo: " + sistemaOperativo);
    }

}
