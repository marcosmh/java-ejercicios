package com.control.acceso.edificio.app;

import com.control.acceso.edificio.app.exceptions.CredencialInvalidaException;
import com.control.acceso.edificio.app.exceptions.DatosInvalidosException;
import com.control.acceso.edificio.app.exceptions.HorarioNoPermitidoException;
import com.control.acceso.edificio.app.exceptions.UsuarioBloqueadoException;
import com.control.acceso.edificio.app.modelo.Usuario;
import com.control.acceso.edificio.app.service.ControlAcceso;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ControlAcceso control = new ControlAcceso();
            ejecutarMenu(scanner, control);
        }
    }

    private static void ejecutarMenu(Scanner scanner, ControlAcceso control) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();

            try {
                switch (opcion) {
                    case "1": 
                        registrarUsuario(scanner, control);
                        break;
                    case "2": 
                        validarAcceso(scanner, control);
                        break;
                    case "3": 
                        control.listarUsuarios();
                        break;
                    case "4": 
                        control.mostrarHistorial();
                        break;
                    case "5":  
                        desbloquearUsuario(scanner, control);
                        break;
                    case "6":
                        System.out.println("Cerrando el sistema. ¡Hasta pronto!");
                        continuar = false;
                        break;
                    default: 
                        System.out.println("Opción no válida. Seleccione del 1 al 6.");
                        break;
                }
            } catch (DatosInvalidosException | DateTimeParseException e) {
                System.out.println("Error de datos: " + e.getMessage());
            } catch (Exception e) {
                // Última barrera para que un error inesperado no cierre el menú.
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                =========================================
                   CONTROL DE ACCESO - EDIFICIO
                =========================================
                1. Registrar usuario
                2. Validar acceso
                3. Consultar usuarios
                4. Consultar historial de accesos
                5. Desbloquear usuario
                6. Salir
                =========================================
                Seleccione una opción: """);
    }

    private static void registrarUsuario(Scanner scanner, ControlAcceso control) throws DatosInvalidosException {
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Hora inicial (HH:mm): ");
        LocalTime inicio = LocalTime.parse(scanner.nextLine().trim());

        System.out.print("Hora final (HH:mm): ");
        LocalTime fin = LocalTime.parse(scanner.nextLine().trim());

        System.out.print("Áreas permitidas separadas por coma (ej. RECEPCION,PISO1): ");
        Set<String> areas = Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(s -> s.toUpperCase().replace(' ', '_'))
                .collect(Collectors.toSet());

        Usuario usuario = new Usuario(id, nombre, inicio, fin, areas);
        control.registrarUsuario(usuario);
    }

    private static void validarAcceso(Scanner scanner, ControlAcceso control) {
        System.out.print("ID de la credencial: ");
        String id = scanner.nextLine().trim();

        System.out.print("Área a la que desea ingresar: ");
        String area = scanner.nextLine().trim().toUpperCase().replace(' ', '_');

        try {
            control.validarAcceso(id, area);
        } catch (CredencialInvalidaException
                 | HorarioNoPermitidoException
                 | UsuarioBloqueadoException e) {
            System.out.println("ACCESO DENEGADO: " + e.getMessage());
        }
    }

    private static void desbloquearUsuario(Scanner scanner, ControlAcceso control) {
        System.out.print("ID del usuario que desea desbloquear: ");
        String id = scanner.nextLine().trim();

        try {
            control.desbloquearUsuario(id);
        } catch (CredencialInvalidaException e) {
            System.out.println("No fue posible desbloquear: " + e.getMessage());
        }
    }

}
