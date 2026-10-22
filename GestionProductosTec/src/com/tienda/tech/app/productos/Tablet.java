package com.tienda.tech.app.productos;

public class Tablet extends Producto {

    private int almacenamientoGB;
    private boolean incluyeLapiz;
    private String tipoPantalla;


    public Tablet(String nombre, int stock, double precio, String codigoInterno, int almacenamientoGB, boolean incluyeLapiz, String tipoPantalla) {
        super(nombre, stock, precio, codigoInterno);
        this.almacenamientoGB = almacenamientoGB;
        this.incluyeLapiz = incluyeLapiz;
        this.tipoPantalla = tipoPantalla;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("--- TABLET ---");
        String conLapiz = "No";
        if(this.incluyeLapiz) {
            conLapiz = "Sí";
        }
        super.mostrarInformacion();
        System.out.println("Almacenamiento: " + this.almacenamientoGB + " GB");
        System.out.println("Incluye Lápiz Digital: " + conLapiz);
        System.out.println("Tipo de Pantalla: " + this.tipoPantalla);
    }
}
