package com.smart.transport.app.transporte;

public abstract class Transporte {

    protected String idEnvio;
    protected String origen;
    protected String destino;
    protected double pesoCarga;
    protected double costoBase;

    public Transporte(String idEnvio, String origen, String destino, double pesoCarga, double costoBase) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.pesoCarga = pesoCarga;

        this.costoBase = costoBase;
    }

    public void setPesoCarga(double pesoCarga) {
        if(pesoCarga <= 0) {
            System.out.println("El peso de la carga debe ser mayor a 0");
        }
        this.pesoCarga = pesoCarga;
    }

    public abstract double calcularCostoEnvio();

    public void mostrarInformacion() {
        System.out.println("===== Transporte ======");
        System.out.println("ID: " + this.idEnvio);
        System.out.println("Origen: " + this.origen);
        System.out.println("Destino: " + this.destino);
        System.out.println("Peso de carga: " + this.pesoCarga + " kg.");
        System.out.println("Costo Base: $ " + this.costoBase);
        System.out.println("\n");
    }
}
