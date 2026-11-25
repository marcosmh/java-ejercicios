package com.sistema.control.acceso.app.modelo;

import com.sistema.control.acceso.app.excepciones.DatosIncompletosException;

import java.time.LocalTime;

public class Usuario {

    public static final  int MAX_INTENTOS_FALLIDOS = 3;

    private final String id;
    private String nombre;
    private String permisoArea;
    private EstadoUsuario estado;
    private int intentosFallidos;
    private LocalTime horaInicioPermitida;
    private LocalTime horaFinPermitida;


    public Usuario(String id, String nombre, String permisoArea, LocalTime horaInicioPermitida, LocalTime horaFinPermitida) {
        this.id = id;
        this.nombre = nombre;
        this.permisoArea = permisoArea;
        this.estado = EstadoUsuario.ACTIVO;
        this.intentosFallidos = 0;
        this.horaInicioPermitida = horaInicioPermitida;
        this.horaFinPermitida = horaFinPermitida;
    }

    private void validarDatos(String id, String nombre, String permisoArea,
                              LocalTime inicio, LocalTime fin) throws DatosIncompletosException {

        if (id == null || id.isBlank()
                || nombre == null || nombre.isBlank()
                || permisoArea == null || permisoArea.isBlank()
                || inicio == null || fin == null) {
            throw new DatosIncompletosException(
                    "Todos los campos (ID, nombre, área/permiso y horario) son obligatorios.");
        }

    }


    private String validaDatosX(String id, String nombre, String permisoArea,
                                LocalTime inicio, LocalTime fin) {
        String mensaje = "";
        if(id.isEmpty()) {
            mensaje += "El Id es obligatorio.\n";
        }

        if(nombre.isEmpty()) {
            mensaje += "El nombre es obligatorio.\n";
        }

        if(permisoArea.isEmpty()) {
            mensaje += "El nombre es obligatorio.\n";
        }

        if(inicio == null ) {
            mensaje += "La  hora inicio es obligatorio.\n";
        }

        if(fin == null ) {
            mensaje += "La hora fin es obligatorio.\n";
        }

        return mensaje;
    }

    public boolean estaDentroDeHorario(LocalTime horaActual) {
        return !horaActual.isBefore(horaInicioPermitida) &&
                !horaActual.isAfter(horaFinPermitida);
    }

    public void registrarIntentoFallido() {
        this.intentosFallidos++;
        if(this.intentosFallidos >= MAX_INTENTOS_FALLIDOS) {
            bloquear();
        }
    }

    public void reiniciarIntentosFallidos() {
        this.intentosFallidos = 0;
    }

    public void bloquear() {
        this.estado = EstadoUsuario.BLOQUEADO;
    }

    public void desbloquear() {
        this.estado = EstadoUsuario.ACTIVO;
        this.intentosFallidos = 0;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPermisoArea() {
        return permisoArea;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public LocalTime getHoraInicioPermitida() {
        return horaInicioPermitida;
    }

    public LocalTime getHoraFinPermitida() {
        return horaFinPermitida;
    }


    public void mostrarInfo() {
        String info = String.format("Usuario{id='%s', nombre='%s', area='%s', estado=%s, fallos=%d, horario=%s-%s}",
                id, nombre, permisoArea, estado, intentosFallidos, horaInicioPermitida, horaFinPermitida);
        System.out.println(info);
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", permisoArea='" + permisoArea + '\'' +
                ", estado=" + estado +
                ", intentosFallidos=" + intentosFallidos +
                ", horaInicioPermitida=" + horaInicioPermitida +
                ", horaFinPermitida=" + horaFinPermitida +
                '}';
    }
}


