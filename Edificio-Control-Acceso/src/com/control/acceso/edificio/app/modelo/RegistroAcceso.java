package com.control.acceso.edificio.app.modelo;

import java.time.LocalDateTime;

public class RegistroAcceso {

    private final LocalDateTime fechaHora;
    private final String id;
    private final String nombre;
    private final String area;
    private final String resultado;
    private final String detalle;

    public RegistroAcceso(LocalDateTime fechaHora,String id,String nombre,String area,
            String resultado,String detalle) {

        this.fechaHora = fechaHora;
        this.id = id;
        this.nombre = nombre;
        this.area = area;
        this.resultado = resultado;
        this.detalle = detalle;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {

        return fechaHora +
                " | ID: " + id +
                " | Nombre: " + nombre +
                " | Área: " + area +
                " | Resultado: " + resultado +
                " | Detalle: " + detalle;
    }
    
}
