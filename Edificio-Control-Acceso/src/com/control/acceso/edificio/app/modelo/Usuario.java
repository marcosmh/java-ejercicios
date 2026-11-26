package com.control.acceso.edificio.app.modelo;

import com.control.acceso.edificio.app.exceptions.DatosInvalidosException;

import java.time.LocalTime;
import java.util.Set;

public class Usuario {

    private final String id;
    private final String nombre;
    private final LocalTime horaInicio;
    private final LocalTime horaFin;
    private final Set<String> areasPermitidas;

    private boolean activo;
    private int intentosFallidos;

    public Usuario(String id, String nombre, LocalTime horaInicio, LocalTime horaFin, Set<String> areasPermitidas) throws DatosInvalidosException {
        if (id == null || id.isBlank()) {
            throw new DatosInvalidosException("El ID es obligatorio.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new DatosInvalidosException("El nombre es obligatorio.");
        }
        if (horaInicio == null || horaFin == null) {
            throw new DatosInvalidosException("El horario es obligatorio.");
        }
        if (!horaInicio.isBefore(horaFin)) {
            throw new DatosInvalidosException("La hora inicial debe ser anterior a la hora final.");
        }
        if (areasPermitidas == null || areasPermitidas.isEmpty()) {
            throw new DatosInvalidosException("Debe asignarse al menos un área permitida.");
        }

        this.id = id.trim();
        this.nombre = nombre.trim();
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.areasPermitidas = Set.copyOf(areasPermitidas);
        this.activo = true;
        this.intentosFallidos = 0;
    }

    public boolean estaDentroDelHorario(LocalTime hora) {
        // Intervalo: [horaInicio, horaFin). La hora final no está incluida.
        return !hora.isBefore(horaInicio) && hora.isBefore(horaFin);
    }

    public boolean tienePermiso(String area) {
        return area != null && areasPermitidas.contains(area.trim().toUpperCase());
    }

    public void registrarIntentoFallido() {
        intentosFallidos++;
        if (intentosFallidos >= 3) {
            bloquear();
        }
    }

    public void registrarAccesoExitoso() {
        intentosFallidos = 0;
    }

    public void bloquear() {
        activo = false;
    }

    public void desbloquear() {
        activo = true;
        intentosFallidos = 0;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public Set<String> getAreasPermitidas() {
        return areasPermitidas;
    }

    public boolean isActivo() {
        return activo;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }
}
