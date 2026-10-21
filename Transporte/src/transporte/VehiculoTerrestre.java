package transporte;

public class VehiculoTerrestre extends  Vehiculo {

    protected int numeroRuedas;
    protected String traccion;

    public VehiculoTerrestre(String marca, String modelo, int anio, float velocidadMaxima, int numeroRuedas, String traccion) {
        super(marca, modelo, anio, velocidadMaxima);
        this.numeroRuedas = numeroRuedas;
        this.traccion = traccion;
    }

    public int getNumeroRuedas() {
        return numeroRuedas;
    }

    public void setNumeroRuedas(int numeroRuedas) {
        this.numeroRuedas = numeroRuedas;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("\n-- Vehiculo Terrestre ---");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año: " + this.anio);
        System.out.println("Velocidad Máxima: " + this.velocidadMaxima);
        System.out.println("Numero de Ruedas: " + this.numeroRuedas);
        System.out.println("Traccion: " + this.traccion);

    }
}
