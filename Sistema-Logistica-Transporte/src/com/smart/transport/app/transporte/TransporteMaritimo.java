package com.smart.transport.app.transporte;

public class TransporteMaritimo extends Transporte {

    public TransporteMaritimo(String idEnvio, String origen, String destino, double pesoCarga, double costoBase) {
        super(idEnvio, origen, destino, pesoCarga, costoBase);
    }

    @Override
    public double calcularCostoEnvio() {
        return costoBase * ( pesoCarga *  3);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Maritimo ");
        System.out.println("Costo Final: $ " + calcularCostoEnvio());
        System.out.println("\n");
    }
}
