package com.sistema.control.acceso.app;

import com.sistema.control.acceso.app.excepciones.CredencialInvalidaException;
import com.sistema.control.acceso.app.excepciones.DatosIncompletosException;
import com.sistema.control.acceso.app.excepciones.HorarioNoPermitidoException;
import com.sistema.control.acceso.app.excepciones.UsuarioBloqueadoException;
import com.sistema.control.acceso.app.modelo.Usuario;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ControlAcceso controlAcceso = new ControlAcceso();
        cargarDatosIniciales(controlAcceso);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean ejecutando = true;
            while (ejecutando) {
                mostrarMenu();
                String opcion = scanner.nextLine().trim();

                switch (opcion) {
                    case "1":
                        registrarUsuarioConsola(scanner, controlAcceso);
                        break;
                    case "2":
                        simularAccesoConsola(scanner, controlAcceso);
                        break;
                    case "3":
                        bloquearUsuarioConsola(scanner, controlAcceso);
                        break;
                    case "4":
                        mostrarBitacora(controlAcceso, false);
                        break;
                    case "5":
                            mostrarBitacora(controlAcceso, true);
                            break;
                    case "6":
                        listarUsuarios(controlAcceso);
                        break;
                    case "7":
                            ejecutarCasosDePrueba();
                            break;
                    case "0":
                        ejecutando = false;
                        System.out.println("Cerrando sistema de control de acceso...");
                        break;
                    default:
                        System.out.println("[AVISO] Opción no válida. Intente de nuevo.");
                        break;
                }
            }
        }

    }

    private static void mostrarMenu() {
        System.out.println("\n=== CONTROL DE ACCESO - EDIFICIO INTELIGENTE ===");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Validar acceso (simular credencial)");
        System.out.println("3. Bloquear usuario manualmente");
        System.out.println("4. Ver bitácora completa de accesos");
        System.out.println("5. Ver registro de accesos fallidos");
        System.out.println("6. Listar usuarios registrados");
        System.out.println("7. Ejecutar suite de 5 casos de prueba");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void cargarDatosIniciales(ControlAcceso control) {
        try {
            control.registrarUsuario(new Usuario(
                    "USR-001", "Anakin Skywalker", "Piso 3 - Laboratorio",
                    LocalTime.of(8, 0), LocalTime.of(18, 0)));
            control.registrarUsuario(new Usuario(
                    "USR-002", "Mace Windu", "Piso 1 - Recepción",
                    LocalTime.of(7, 0), LocalTime.of(15, 0)));
        } catch (DatosIncompletosException e) {
            System.out.println("[ERROR INICIAL] " + e.getMessage());
        }
    }

    private static void registrarUsuarioConsola(Scanner scanner, ControlAcceso control) {
        try {
            System.out.print("ID del usuario: ");
            String id = scanner.nextLine();
            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();
            System.out.print("Área / permiso: ");
            String area = scanner.nextLine();
            System.out.print("Hora inicio permitida (HH:mm, ej. 08:00): ");
            LocalTime inicio = LocalTime.parse(scanner.nextLine().trim());
            System.out.print("Hora fin permitida (HH:mm, ej. 18:00): ");
            LocalTime fin = LocalTime.parse(scanner.nextLine().trim());

            Usuario nuevo = new Usuario(id, nombre, area, inicio, fin);
            control.registrarUsuario(nuevo);
            System.out.println("[OK] Usuario registrado: " + nuevo.toString());
        } catch (DatosIncompletosException e) {
            System.out.println("[ERROR DE REGISTRO] " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("[ERROR DE ENTRADA] Formato de hora inválido. Use HH:mm (ej. 09:30).");
        }
    }

    private static void simularAccesoConsola(Scanner scanner, ControlAcceso control) {
        try {
            System.out.print("Ingrese ID / credencial: ");
            String id = scanner.nextLine();
            System.out.print("Ingrese área a acceder: ");
            String area = scanner.nextLine();
            System.out.print("Ingrese hora de acceso (HH:mm o ENTER para hora actual): ");
            String horaTexto = scanner.nextLine().trim();

            LocalTime hora = horaTexto.isEmpty() ? LocalTime.now() : LocalTime.parse(horaTexto);
            boolean permitido = control.validarAcceso(id, area, hora);
            if (permitido) {
                System.out.println("[ACCESO PERMITIDO] Puerta desbloqueada.");
            }
        } catch (CredencialInvalidaException
                 | HorarioNoPermitidoException
                 | UsuarioBloqueadoException
                 | DatosIncompletosException e) {
            System.out.println("[ALERTA DE ACCESO] " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("[ERROR DE ENTRADA] Hora inválida. Use formato HH:mm.");
        }
    }

    private static void bloquearUsuarioConsola(Scanner scanner, ControlAcceso control) {
        try {
            System.out.print("ID del usuario a bloquear: ");
            String id = scanner.nextLine();
            control.bloquearUsuario(id);
            System.out.println("[OK] Usuario bloqueado.");
        } catch (CredencialInvalidaException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void mostrarBitacora(ControlAcceso control, boolean soloFallidos) {
        var lista = soloFallidos ? control.getAccesosFallidos() : control.getBitacoraCompleta();
        System.out.println(soloFallidos ? "\n--- ACCESOS FALLIDOS ---" : "\n--- BITÁCORA COMPLETA ---");
        if (lista.isEmpty()) {
            System.out.println("(Sin registros)");
            return;
        }
        lista.forEach(System.out::println);
    }

    private static void listarUsuarios(ControlAcceso control) {
        System.out.println("\n--- USUARIOS REGISTRADOS ---");
        control.getUsuarios().forEach(System.out::println);
    }

    public static void ejecutarCasosDePrueba() {
        System.out.println("\n================ EJECUCIÓN DE CASOS DE PRUEBA ================");
        ControlAcceso sistema = new ControlAcceso();

        try {
            sistema.registrarUsuario(new Usuario(
                    "EMP-100", "Elena Ríos", "Piso 4 - Servidores",
                    LocalTime.of(8, 0), LocalTime.of(17, 0)));
        } catch (DatosIncompletosException e) {
            System.out.println("Fallo al preparar prueba: " + e.getMessage());
        }

        // Caso 1: Acceso exitoso
        System.out.println("\n[Caso 1] Acceso exitoso:");
        try {
            boolean ok = sistema.validarAcceso("EMP-100", "Piso 4 - Servidores", LocalTime.of(10, 30));
            System.out.println("Resultado: " + (ok ? "PERMITIDO" : "DENEGADO"));
        } catch (Exception e) {
            System.out.println("Excepción inesperada: " + e.getMessage());
        }

        // Caso 2: Acceso con credencial inválida
        System.out.println("\n[Caso 2] Acceso con credencial inválida:");
        try {
            sistema.validarAcceso("NO-EXISTE", "Piso 1 - Lobby", LocalTime.of(11, 0));
        } catch (CredencialInvalidaException e) {
            System.out.println("Capturada CredencialInvalidaException -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Otra excepción: " + e.getMessage());
        }

        // Caso 3: Acceso fuera del horario
        System.out.println("\n[Caso 3] Acceso fuera del horario permitido:");
        try {
            sistema.validarAcceso("EMP-100", "Piso 4 - Servidores", LocalTime.of(21, 15));
        } catch (HorarioNoPermitidoException e) {
            System.out.println("Capturada HorarioNoPermitidoException -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Otra excepción: " + e.getMessage());
        }

        // Caso 4: Usuario bloqueado tras 3 intentos fallidos
        System.out.println("\n[Caso 4] Usuario bloqueado tras 3 intentos fallidos:");
        for (int i = 1; i <= 3; i++) {
            try {
                sistema.validarAcceso("EMP-100", "Piso 4 - Servidores", LocalTime.of(6, 0));
            } catch (HorarioNoPermitidoException e) {
                System.out.println("Intento fallido #" + i + ": " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Excepción: " + e.getMessage());
            }
        }
        try {
            // 4to intento con usuario ya bloqueado (incluso en horario válido)
            sistema.validarAcceso("EMP-100", "Piso 4 - Servidores", LocalTime.of(10, 0));
        } catch (UsuarioBloqueadoException e) {
            System.out.println("Capturada UsuarioBloqueadoException -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        // Caso 5: Usuario con datos incompletos
        System.out.println("\n[Caso 5] Usuario con datos incompletos:");
        try {
            Usuario invalido = new Usuario("EMP-101", "   ", "Piso 2",
                    LocalTime.of(8, 0), LocalTime.of(17, 0));
            sistema.registrarUsuario(invalido);
        } catch (DatosIncompletosException e) {
            System.out.println("Capturada DatosIncompletosException -> " + e.getMessage());
        }

        System.out.println("\n--- Bitácora generada durante las pruebas (gracias a finally) ---");
        sistema.getBitacoraCompleta().forEach(System.out::println);
        System.out.println("==============================================================\n");
    }

}
