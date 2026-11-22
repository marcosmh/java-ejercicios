package com.sistema.reservas.app.aerolinea;

import com.sistema.reservas.app.exceptions.SobreventaException;

public class Vuelo {

    private String codigoVuelo;
    private int asientosDisponibles;
    private boolean disponibilidadConsultada;

    public Vuelo(String codigoVuelo, int asientosDisponibles) {
        if(asientosDisponibles < 0) {
            throw new IllegalArgumentException("La capacidad inicial no debe ser negativa.");
        }
        this.codigoVuelo = codigoVuelo;
        this.asientosDisponibles = asientosDisponibles;
        this.disponibilidadConsultada = false;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public int consultarDisponibilidad() {
        this.disponibilidadConsultada = true;
        return asientosDisponibles;
    }

    public void reservarAsientos(int cantidad) throws
            IllegalStateException, IllegalArgumentException, SobreventaException {

        if(!disponibilidadConsultada) {
            throw new IllegalStateException("Debe consultar la disponibilidad antes de realizar una reserva");
        }

        if(cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de asientos debe ser mayor a cero.");
        }

        if(cantidad > asientosDisponibles) {
            throw new SobreventaException("No hay suficiented asientos disponibles quedan.\n Solo quedan: " +
                    asientosDisponibles + ".");
        }

        asientosDisponibles -= cantidad;

        System.out.println("Reserva realizada con exito. Asientos restantes " + asientosDisponibles);

    }

}
