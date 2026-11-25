package com.sistema.control.acceso.app.excepciones;

/**
 * Excepción lanzada cuando el acceso ocurre fuera del horario autorizado.
 */
public class HorarioNoPermitidoException extends Exception {
    public HorarioNoPermitidoException(String message) {
        super(message);
    }
}
