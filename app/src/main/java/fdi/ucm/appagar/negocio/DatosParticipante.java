package fdi.ucm.appagar.negocio;

/**
 * Created by jalbe on 03/05/2017.
 */

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
