package transporte;

public class Vehiculo {

    protected String marca;
    protected String modelo;
    protected int anio;
    protected float velocidadMaxima;


    public Vehiculo(String marca, String modelo, int anio, float velocidadMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.velocidadMaxima = velocidadMaxima;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(float velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }


    public void mostrarInfo() {
        System.out.println("\n-- Vehiculo ---");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año: " + this.anio);
        System.out.println("Velocidad Máxima: " + this.velocidadMaxima);

    }

    public void encender() {
        System.out.println("Encender... " + this.marca + " " + this.modelo);
    }

    public void apagar() {
        System.out.println("Apagar..." + this.marca + " " + this.modelo);

    }

    public void mover() {
        System.out.println("Mover..."  + this.marca + " " + this.modelo);
    }

    public void frenar() {
        System.out.println("Frenar..." +  this.marca + " " + this.modelo);
    }


}
