package com.sistema.reservas.app.aerolinea;

import com.sistema.reservas.app.exceptions.SobreventaException;

// Representa un vuelo y gestiona la reserva de asientos.
public class Vuelo {

    // Código identificador del vuelo.
    private String codigoVuelo;

    // Asientos disponibles para reservar.
    private int asientosDisponibles;

    // Indica si se consultó la disponibilidad.
    private boolean disponibilidadConsultada;

    // Constructor de la Clase Vuelo
    // Inicializa el vuelo y sus asientos.
    public Vuelo(String codigoVuelo, int asientosDisponibles) {
        if(asientosDisponibles < 0) {
            throw new IllegalArgumentException("La capacidad inicial no debe ser negativa.");
        }
        this.codigoVuelo = codigoVuelo;
        this.asientosDisponibles = asientosDisponibles;
        this.disponibilidadConsultada = false;
    }

    // Obtiene el código del vuelo.
    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    // Consulta la disponibilidad de asientos.
    public int consultarDisponibilidad() {
        this.disponibilidadConsultada = true;
        return asientosDisponibles;
    }

    // Reserva una cantidad de asientos.
    // IllegalStateException se utiliza cuando un método se llama en un estado inválido del objeto.
    // IllegalArgumentException se utiliza cuando un método recibe un argumento (parámetro) inválido.
    // SobreventaException es una excepción personalizada creada para representar un error específico
    public void reservarAsientos(int cantidad) throws
            IllegalStateException, IllegalArgumentException, SobreventaException {

        // Verifica que se haya consultado la disponibilidad.
        if(!disponibilidadConsultada) {
            throw new IllegalStateException("Debe consultar la disponibilidad antes de realizar una reserva");
        }

        // Valida que la cantidad sea mayor a cero.
        if(cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de asientos debe ser mayor a cero.");
        }

        // Valida que existan suficientes asientos disponibles.
        if(cantidad > asientosDisponibles) {
            throw new SobreventaException("No hay suficiented asientos disponibles quedan.\n Solo quedan: " +
                    asientosDisponibles + ".");
        }

        // Descuenta los asientos reservados.
        asientosDisponibles -= cantidad;

        System.out.println("Reserva realizada con exito. Asientos restantes " + asientosDisponibles);

    }

}
