package com.sistema.control.acceso.app.excepciones;

/**
 * Excepción lanzada cuando un usuario bloqueado intenta ingresar.
 */
public class UsuarioBloqueadoException extends Exception{
    public UsuarioBloqueadoException(String message) {
        super(message);
    }
}
