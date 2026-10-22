package com.tienda.tech.app.productos;

public class Smartphone extends Producto {

    private double tamanioPantalla;
    private String sistemaOperativo;
    private boolean es5G;


    public Smartphone(String nombre, int stock, double precio, String codigoInterno, double tamanioPantalla, String sistemaOperativo, boolean es5G) {
        super(nombre, stock, precio, codigoInterno);
        this.tamanioPantalla = tamanioPantalla;
        this.sistemaOperativo = sistemaOperativo;
        this.es5G = es5G;
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("--- SMARTPHONE ---");
        String compatible = "No";
        if(this.es5G) {
            compatible = "Sí";
        }
        super.mostrarInformacion();
        System.out.println("Tamaño de Pantalla: " + this.tamanioPantalla + "\"");
        System.out.println("Sistema Operativo: " + this.sistemaOperativo);
        System.out.println("Compatible con 5G: " + compatible);
    }
}
