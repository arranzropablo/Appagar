package fdi.ucm.appagar.negocio;

/**
 * Clase que almacena datos de un participante concreto
 */
public class DatosParticipante {
    /**
     * nombre del participante
     */
    private String nombre;
    /**
     * cantidad que ha pagado
     */
    private double cantidad;

    /**
     * Constructora de la clase
     * @param nombre nombre del participante
     * @param cantidad cantidad que ha pagado
     */
    public DatosParticipante(String nombre, double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    /**
     * Función que devuelve el nombre del participante
     * @return nombre del participante
     */
    public String getNombre () {
        return this.nombre;
    }

    /**
     * Función que devuelve la cantidad pagada por el participante
     * @return cantidad pagada por el participante
     */
    public double getCantidad() {
        return this.cantidad;
    }

    /**
     * Pone una cantidad dada a la cantidad pagada del participante
     * @param cantidad nueva cantidad pagada
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
