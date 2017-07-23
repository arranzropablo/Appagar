package fdi.ucm.appagar.negocio;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Clase que almacena los datos necesarios de una cuenta para su posterior muestra
 */
public class DatosCuenta {
    /**
     * Total de la cuenta
     */
    private double totalCuenta;
    /**
     * Importe por persona en esta cuenta
     */
    private double deuda;
    /**
     * Datos de los participantes de la cuenta
     */
    private ArrayList<DatosParticipante> participantes;

    /**
     * Constructora de la clase
     * @param totalCuenta importe total de la cuenta.
     * @param participantes Array con los participantes de la cuenta.
     */
    public DatosCuenta(double totalCuenta, ArrayList<DatosParticipante> participantes) {
        this.totalCuenta = totalCuenta;
        this.participantes = participantes;
        this.deuda = totalCuenta / participantes.size();
        ajustarDecimales();
    }

    /**
     * Devuelve el importe total de una cuenta
     * @return importe total
     */
    public double getTotalCuenta () {
        return this.totalCuenta;
    }

    /**
     * Devuelve el numero de participantes de la cuenta
     * @return numero de participantes de la cuenta
     */
    public int getNumParticipantes () {
        return participantes.size();
    }

    /**
     * Devuelve el importe a paar por persona
     * @return importe por persona
     */
    public double getDeuda () {
        return this.deuda;
    }

    /**
     * Devuelve el participante del array que ocupe la posicion indicada
     * @param posicion posicion indicada del array
     * @return datos dek participante
     */
    public DatosParticipante getParticipante(int posicion) {
        return participantes.get(posicion);
    }

    /**
     * Busca en el array un nombre de participante
     * @param nombre nombre del participante
     * @return datos del participante con ese nombre
     */
    public DatosParticipante getParticipantePorNombre(String nombre) {
        boolean enc = false;
        int i = 0;
        while (i < participantes.size() && !enc) {
            if (participantes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                enc = true;
            }
            ++i;
        }
        return participantes.get(i - 1);
    }

    /**
     * Ajusta los decimales de los doubles para que solo haya 2 (para poder mostrarlos adecuadamente)
     */
    private void ajustarDecimales() {
        DecimalFormat df = new DecimalFormat("#.00");

        String totalCuenta = df.format(this.totalCuenta);
        totalCuenta = totalCuenta.replace(',', '.');
        this.totalCuenta = Double.parseDouble(totalCuenta);
        String deuda = df.format(this.deuda);
        deuda = deuda.replace(',', '.');
        this.deuda = Double.parseDouble(deuda);
        for (int i = 0; i < participantes.size(); ++i) {
            String cantidad = df.format(this.participantes.get(i).getCantidad());
            cantidad = cantidad.replace(',', '.');
            this.participantes.get(i).setCantidad(Double.parseDouble(cantidad));
        }
    }
}
