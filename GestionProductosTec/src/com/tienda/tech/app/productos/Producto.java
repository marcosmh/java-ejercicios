package com.tienda.tech.app.productos;

public class Producto {

    protected String nombre;
    protected double precio;
    protected  int stock;
    private String codigoInterno;

    public Producto(String nombre, int stock, double precio, String codigoInterno) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.codigoInterno = codigoInterno;
    }


    public String getCodigoInterno() {
        return codigoInterno;
    }

    public double calcularPrecioConIVA() {
        return this.precio * 1.16;
    }

    public void mostrarInformacion() {
        System.out.println("Código Interno: " + this.getCodigoInterno());
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio Base: $" + this.precio + " Pesos MXN");
        System.out.println("Stock: " + this.stock + " unidades");
    }


}
