package com.sistema.control.acceso.app.excepciones;

/**
 * Excepción lanzada cuando la credencial o ID no existe en el sistema.
 */
public class CredencialInvalidaException extends Exception {
    public CredencialInvalidaException(String message) {
        super(message);
    }
}
