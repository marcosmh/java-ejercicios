package com.smart.transport.app.transporte;

import com.smart.transport.app.bonificacion.Bonificable;

public class TransporteTerrestre extends Transporte implements Bonificable {

    private double descuentoAplicado = 0.0;

    public TransporteTerrestre(String idEnvio, String origen, String destino, double pesoCarga, double costoBase) {
        super(idEnvio, origen, destino, pesoCarga, costoBase);
    }

    @Override
    public void aplicarBonificacion(double porcentaje) {
        if(porcentaje > 0 && porcentaje <= 100) {
            this.descuentoAplicado = porcentaje / 100.0;
        }
    }

    @Override
    public double calcularCostoEnvio() {
        double costoTotal = costoBase + (pesoCarga * 5);
        return costoTotal - ( costoTotal *  descuentoAplicado);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Terrestre ");
        System.out.println("Descuento: " + (this.descuentoAplicado * 100) + "%" );
        System.out.println("Costo Final: $ " + calcularCostoEnvio());
        System.out.println("\n");

    }
}
