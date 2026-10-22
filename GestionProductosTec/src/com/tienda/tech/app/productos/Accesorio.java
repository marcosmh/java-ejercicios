package com.tienda.tech.app.productos;

public class Accesorio extends Producto {

    private String tipoAccesorio;
    private boolean esInalambrico;
    private String marca;

    public Accesorio(String nombre, int stock, double precio, String codigoInterno, String tipoAccesorio, boolean esInalambrico, String marca) {
        super(nombre, stock, precio, codigoInterno);
        this.tipoAccesorio = tipoAccesorio;
        this.esInalambrico = esInalambrico;
        this.marca = marca;
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("--- ACCESORIO ---");
        String inalambrico = "No";
        if(this.esInalambrico) {
            inalambrico = "Sí";
        }
        super.mostrarInformacion();
        System.out.println("Tipo de Accesorio: " + this.tipoAccesorio);
        System.out.println("Inalámbrico: " + inalambrico);
        System.out.println("Marca: " + this.marca);
    }
}
