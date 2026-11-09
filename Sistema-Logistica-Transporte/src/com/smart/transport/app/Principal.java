package com.smart.transport.app;

import com.smart.transport.app.bonificacion.Bonificable;
import com.smart.transport.app.transporte.Transporte;
import com.smart.transport.app.transporte.TransporteAereo;
import com.smart.transport.app.transporte.TransporteMaritimo;
import com.smart.transport.app.transporte.TransporteTerrestre;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Transporte[] envios = new Transporte[6];

        envios[0] = new TransporteTerrestre("T-001","Queretaro","Tabasco",150.0,50.0);
        envios[1] = new TransporteMaritimo("M-001","Colima","Veracruz",1200,300.0);
        envios[2] = new TransporteAereo("A-001","Chihuahua","Yucatan",2000,200.0);
        envios[3] = new TransporteTerrestre("T-002","Nuevo Leon","CDMX",1150.0,80.0);
        envios[4] = new TransporteMaritimo("M-002","Tamaulipas","Baja California",9100,500.0);
        envios[5] = new TransporteAereo("A-002","Guadalajara","Cancun",90,290.10);

        // Aplicar bonificaciones a los objetos que implementan Bonificable (polimorfismo dinámico)
        for(Transporte transporte : envios) {
            if(transporte instanceof Bonificable) {
                ((Bonificable) transporte).aplicarBonificacion(10.0);
            }
        }

        int opcion = 0;
        do {
            opcion = menuSistema(scanner, envios);
        } while( opcion != 3);
        scanner.close();
    }

    private static int menuSistema(Scanner scanner, Transporte[] envios) {
        int opcion;
        System.out.println("\n=== SISTEMA DE LOGÍSTICA SMARTTRANSPORT ===");
        System.out.println("1. Mostrar reporte detallado de envíos actuales (6 predefinidos)");
        System.out.println("2. Registrar un nuevo envío de forma interactiva");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("\n--- REPORTE DETALLADO DE ENVÍOS ---");
                for (Transporte t : envios) {
                    t.mostrarInformacion();
                }
                break;
            case 2:
                nuevoEnvio(scanner);
                break;
            case 3:
                System.out.println("Saliendo del sistema SmartTransport. ¡Hasta pronto!");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
        return opcion;
    }

    private static void nuevoEnvio(Scanner scanner) {
        System.out.println("\n--- REGISTRO DE NUEVO ENVÍO ---");
        System.out.print("Ingrese ID de envío: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese Origen: ");
        String origen = scanner.nextLine();
        System.out.print("Ingrese Destino: ");
        String destino = scanner.nextLine();

        double peso = 0;
        while (true) {
            System.out.print("Ingrese Peso de la carga (kg): ");
            if (scanner.hasNextDouble()) {
                peso = scanner.nextDouble();
                if (peso > 0) break;
                else System.out.println("Error: El peso debe ser estrictamente mayor a 0.");
            } else {
                System.out.println("Error: Ingrese un valor numérico válido.");
                scanner.next();
            }
        }

        double costoBase = 0;
        while (true) {
            System.out.print("Ingrese Costo Base ($): ");
            if (scanner.hasNextDouble()) {
                costoBase = scanner.nextDouble();
                if (costoBase >= 0) break;
                else System.out.println("Error: El costo base no puede ser negativo.");
            } else {
                System.out.println("Error: Ingrese un valor numérico válido.");
                scanner.next();
            }
        }
        scanner.nextLine();

        Transporte nuevoEnvio = tipoTransporte(scanner, id, origen, destino, peso, costoBase);

        // Aplicar bonificación opcional si aplica
        if (nuevoEnvio instanceof Bonificable) {
            System.out.print("¿Desea aplicar bonificación del 10% a este envío? (s/n): ");
            String resp = scanner.next();
            if (resp.equalsIgnoreCase("s")) {
                ((Bonificable) nuevoEnvio).aplicarBonificacion(10.0);
            }
        }

        System.out.println("\n¡Envío registrado con éxito!");
        nuevoEnvio.mostrarInformacion();
        return;
    }

    private static Transporte tipoTransporte(Scanner scanner, String id, String origen, String destino, double peso, double costoBase) {
        System.out.println("Seleccione tipo de transporte:");
        System.out.println("1. Terrestre");
        System.out.println("2. Marítimo");
        System.out.println("3. Aéreo");
        System.out.print("Opción: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Transporte nuevoEnvio = null;
        switch (tipo) {
            case 1:
                nuevoEnvio = new TransporteTerrestre(id, origen, destino, peso, costoBase);
                break;
            case 2:
                nuevoEnvio = new TransporteMaritimo(id, origen, destino, peso, costoBase);
                break;
            case 3:
                nuevoEnvio = new TransporteAereo(id, origen, destino, peso, costoBase);
                break;
            default:
                System.out.println("Opción inválida. Se asignará Terrestre por defecto.");
                nuevoEnvio = new TransporteTerrestre(id, origen, destino, peso, costoBase);
                break;
        }
        return nuevoEnvio;
    }
}
