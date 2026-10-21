package transporte;

public class VehiculoAcuatico extends Vehiculo {

    protected float desplazamiento;
    protected String tipoPropulsion;


    public VehiculoAcuatico(String marca, String modelo, int anio, float velocidadMaxima, float desplazamiento, String tipoPropulsion) {
        super(marca, modelo, anio, velocidadMaxima);
        this.desplazamiento = desplazamiento;
        this.tipoPropulsion = tipoPropulsion;
    }

    public float getDesplazamiento() {
        return desplazamiento;
    }

    public void setDesplazamiento(float desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    public String getTipoPropulsion() {
        return tipoPropulsion;
    }

    public void setTipoPropulsion(String tipoPropulsion) {
        this.tipoPropulsion = tipoPropulsion;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("\n-- Vehiculo Acuatico ---");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año: " + this.anio);
        System.out.println("Velocidad Máxima: " + this.velocidadMaxima);
        System.out.println("Desplazamiento: " + this.desplazamiento);
        System.out.println("Tipo Propulsion: " + this.tipoPropulsion);

    }

}
