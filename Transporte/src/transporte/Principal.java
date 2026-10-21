package transporte;

public class Principal {

    public static void main(String[] args) {

        Vehiculo vehiculo = new Vehiculo("Tesla","Model S Plaid", 2024, 322);
        vehiculo.encender();
        vehiculo.mover();
        vehiculo.frenar();
        vehiculo.apagar();
        vehiculo.mostrarInfo();
        System.out.println("\n");

        VehiculoAcuatico vehiculoAcuatico = new VehiculoAcuatico("Yamaha ","SX210", 2026, 400, 400.50f, "A chorro" );
        vehiculoAcuatico.encender();
        vehiculoAcuatico.mover();
        vehiculoAcuatico.frenar();
        vehiculoAcuatico.apagar();
        vehiculoAcuatico.mostrarInfo();
        System.out.println("\n");

        Barco barco = new Barco("Odense Steel","Clase E", 2006, 400, 400.50f, "A chorro" ,170974.140f, 397f);
        barco.encender();
        barco.mover();
        barco.frenar();
        barco.apagar();
        barco.mostrarInfo();
        System.out.println("\n");


        VehiculoTerrestre vehiculoTerrestre = new VehiculoTerrestre("Ducati","Panigale V4",2025,299,2,"Trasera");
        vehiculoTerrestre.encender();
        vehiculoTerrestre.mover();
        vehiculoTerrestre.frenar();
        vehiculoTerrestre.apagar();
        vehiculoTerrestre.mostrarInfo();
        System.out.println("\n");

        Automovil automovil = new Automovil("Toyota","Camry", 2025, 220,4, "Delantera", 5,"Sedan");
        automovil.encender();
        automovil.mover();
        automovil.frenar();
        automovil.apagar();
        automovil.mostrarInfo();
        System.out.println("\n");



        VehiculoAereo vehiculoAereo = new VehiculoAereo("Eurocopter","H125",2023, 287, 1, 7010.1f, 6);
        vehiculoAereo.encender();
        vehiculoAereo.mover();
        vehiculoAereo.frenar();
        vehiculoAereo.apagar();
        vehiculoAereo.mostrarInfo();
        System.out.println("\n");

        Avion avion = new Avion("Boeing","787-9",2024,954, 2, 13100, 200, "Ala en flecha", 200);
        avion.encender();
        avion.mover();
        avion.frenar();
        avion.apagar();
        avion.mostrarInfo();
        System.out.println("\n");


    }


}
