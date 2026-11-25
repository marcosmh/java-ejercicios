package com.sistema.control.acceso.app.excepciones;

/**
 * Excepción lanzada cuando faltan datos obligatorios o tienen formato inválido.
 */
public class DatosIncompletosException extends Exception {
    public DatosIncompletosException(String message) {
        super(message);
    }
}
