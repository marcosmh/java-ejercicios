package com.sistema.reservas.app;

import com.sistema.reservas.app.aerolinea.Vuelo;
import com.sistema.reservas.app.exceptions.SobreventaException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaReservas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vuelo vuelo = new Vuelo("MX123",10);
        int opcion = 0;

        System.out.println("====== Bienvenido al Sistema de Reservas de Vuelos ======");

        do {
            System.out.println("\nMenú");
            System.out.println("1. Consultar asientos disponibles");
            System.out.println("2. Reservar asientos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Asientos disponibles: " + vuelo.consultarDisponibilidad());
                        break;
                    case 2:
                        System.out.print("Ingrese la cantidad de asientos a reservar: ");
                        int cantidad = scanner.nextInt();
                        vuelo.reservarAsientos(cantidad);
                        break;
                    case 3:
                        System.out.println("Gracias por usar el sistema de reservas.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } catch(InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                scanner.nextLine();
                opcion = 0;
            } catch (IllegalStateException e) {
                System.out.println("Aviso de operación: " + e.getMessage());
                opcion = 0;
            } catch(IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                opcion = 0;
            } catch(SobreventaException e) {
                System.out.println("Error de reserva: " + e.getMessage());
                opcion = 0;
            } finally {
                System.out.println("Operación Finalizada.");
            }

        } while(opcion != 3);

        scanner.close();

    }
}
