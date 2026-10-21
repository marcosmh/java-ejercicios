package transporte;

public class Automovil extends VehiculoTerrestre {

    private int capacidadPasajeros;
    private String tipoCarroseria;

    public Automovil(String marca, String modelo, int anio, float velocidadMaxima, int numeroRuedas, String traccion, int capacidadPasajeros, String tipoCarroseria) {
        super(marca, modelo, anio, velocidadMaxima, numeroRuedas, traccion);
        this.capacidadPasajeros = capacidadPasajeros;
        this.tipoCarroseria = tipoCarroseria;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public String getTipoCarroseria() {
        return tipoCarroseria;
    }

    public void setTipoCarroseria(String tipoCarroseria) {
        this.tipoCarroseria = tipoCarroseria;
    }


    @Override
    public void mostrarInfo() {
        System.out.println("\n-- Automovil ---");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año: " + this.anio);
        System.out.println("Velocidad Máxima: " + this.velocidadMaxima);
        System.out.println("Numero de Ruedas: " + this.numeroRuedas);
        System.out.println("Traccion: " + this.traccion);
        System.out.println("Capacidad de pasajeros: " + capacidadPasajeros);
        System.out.println("Tipo Carroseria: " + this.tipoCarroseria);

    }
}
