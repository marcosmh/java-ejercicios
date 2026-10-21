package transporte;

public class VehiculoAereo extends Vehiculo {

    protected int numeroMotores;
    protected float altitudMaxima;
    protected int numeroPasajeros;

    public VehiculoAereo(String marca, String modelo, int anio, float velocidadMaxima, int numeroMotores, float altitudMaxima, int numeroPasajeros) {
        super(marca, modelo, anio, velocidadMaxima);
        this.numeroMotores = numeroMotores;
        this.altitudMaxima = altitudMaxima;
        this.numeroPasajeros = numeroPasajeros;
    }


    public int getNumeroMotores() {
        return numeroMotores;
    }

    public void setNumeroMotores(int numeroMotores) {
        this.numeroMotores = numeroMotores;
    }

    public float getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(float altitudMaxima) {
        this.altitudMaxima = altitudMaxima;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("\n-- Vehiculo Areo ---");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año: " + this.anio);
        System.out.println("Velocidad Máxima: " + this.velocidadMaxima);
        System.out.println("Numero de motores: " + this.numeroMotores);
        System.out.println("Altitud Maxima: " + this.altitudMaxima);
        System.out.println("Numero Pasajeros: " + this.numeroPasajeros);

    }
}
