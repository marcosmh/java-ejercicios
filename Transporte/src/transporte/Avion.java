package transporte;

public class Avion extends VehiculoAereo{

    private String tipoAla;
    private int numeroAsientos;

    public Avion(String marca, String modelo, int anio, float velocidadMaxima, int numeroMotores, float altitudMaxima, int numeroPasajeros, String tipoAla, int numeroAsientos) {
        super(marca, modelo, anio, velocidadMaxima, numeroMotores, altitudMaxima, numeroPasajeros);
        this.tipoAla = tipoAla;
        this.numeroAsientos = numeroAsientos;
    }

    public String getTipoAla() {
        return tipoAla;
    }

    public void setTipoAla(String tipoAla) {
        this.tipoAla = tipoAla;
    }

    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(int numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("\n-- Avion ---");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año: " + this.anio);
        System.out.println("Velocidad Máxima: " + this.velocidadMaxima);
        System.out.println("Altitud Maxima: " + this.altitudMaxima);
        System.out.println("Numero de motores: " + this.numeroMotores);
        System.out.println("Numero Pasajeros: " + this.numeroPasajeros);
        System.out.println("Tipo de Ala: " + this.tipoAla);
        System.out.println("Numero Asientos: " + this.numeroAsientos);

    }
}
