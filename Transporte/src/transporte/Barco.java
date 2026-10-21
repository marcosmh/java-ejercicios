package transporte;

public class Barco extends VehiculoAcuatico {

    private float tonelaje;
    private float longitud;

    public Barco(String marca, String modelo, int anio, float velocidadMaxima, float desplazamiento, String tipoPropulsion, float tonelaje, float longitud) {
        super(marca, modelo, anio, velocidadMaxima, desplazamiento, tipoPropulsion);
        this.tonelaje = tonelaje;
        this.longitud = longitud;
    }

    public float getTonelaje() {
        return tonelaje;
    }

    public void setTonelaje(float tonelaje) {
        this.tonelaje = tonelaje;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("\n-- Barco ---");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año: " + this.anio);
        System.out.println("Velocidad Máxima: " + this.velocidadMaxima);
        System.out.println("Desplazamiento: " + this.desplazamiento);
        System.out.println("Tipo Propulsion: " + this.tipoPropulsion);
        System.out.println("Tonelaje: " + this.tonelaje);
        System.out.println("Longitud: " + this.longitud);

    }
}
