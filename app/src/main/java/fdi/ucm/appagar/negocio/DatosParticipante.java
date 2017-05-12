package fdi.ucm.appagar.negocio;

public class DatosParticipante {
    private String nombre;
    private double cantidad;

    public DatosParticipante(String nombre, double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre () {
        return this.nombre;
    }

    public double getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
