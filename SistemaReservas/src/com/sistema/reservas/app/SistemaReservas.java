package com.sistema.reservas.app;

import com.sistema.reservas.app.aerolinea.Vuelo;
import com.sistema.reservas.app.exceptions.SobreventaException;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Sistema de reservas de vuelos
 */
public class SistemaReservas {

    public static void main(String[] args) {

        // Lee datos ingresados por el usuario.
        Scanner scanner = new Scanner(System.in);

        // Crea un vuelo con 10 asientos disponibles.
        Vuelo vuelo = new Vuelo("MX123",10);

        // Almacena la opción seleccionada.
        int opcion = 0;

        System.out.println("====== Bienvenido al Sistema de Reservas de Vuelos ======");

        do {
            // Menú principal.
            System.out.println("\nMenú");
            System.out.println("1. Consultar asientos disponibles");
            System.out.println("2. Reservar asientos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                // Lee la opción del usuario.
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1: // Consulta disponibilidad.
                        System.out.println("Asientos disponibles: " + vuelo.consultarDisponibilidad());
                        break;
                    case 2: // Realiza una reserva.
                        System.out.print("Ingrese la cantidad de asientos a reservar: ");
                        int cantidad = scanner.nextInt();
                        vuelo.reservarAsientos(cantidad);
                        break;
                    case 3: // Finaliza el programa.
                        System.out.println("Gracias por usar el sistema de reservas.");
                        break;
                    default: // Valida opciones incorrectas.
                        System.out.println("Opción no válida.");
                }

            } catch(InputMismatchException e) {
                // Captura entradas no numéricas.
                System.out.println("Entrada inválida. Ingrese un número entero.");
                scanner.nextLine();
                opcion = 0;
            } catch (IllegalStateException e) {
                // Captura operaciones fuera de secuencia.
                System.out.println("Aviso de operación: " + e.getMessage());
                opcion = 0;
            } catch(IllegalArgumentException e) {
                // Captura cantidades inválidas.
                System.out.println("Error: " + e.getMessage());
                opcion = 0;
            } catch(SobreventaException e) {
                // Captura intentos de sobreventa.
                System.out.println("Error de reserva: " + e.getMessage());
                opcion = 0;
            } finally {
                // Se ejecuta siempre al finalizar la operación.
                System.out.println("Operación Finalizada.");
            }

        } while(opcion != 3);

        scanner.close();

    }
}
