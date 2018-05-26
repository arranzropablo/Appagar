package fdi.ucm.appagar.presentacion.controlador;

import android.content.Context;

import java.util.List;
import java.util.Vector;

import fdi.ucm.appagar.negocio.DatosCuenta;

abstract public class Controlador {

    private static Controlador controlador;

    /**
     * Método que aplica el patrón singleton sobre el controlador para que solo sea creado una vez
     */
    public static Controlador getInstance(Context c) {
        if (controlador == null) {
            controlador = new ControladorImp(c);
        }
        return controlador;
    }

    /**
     * Método que confirma si existe la cuenta
     * @param nombre nombre de la cuenta
     */
    public abstract boolean exists(String nombre);

    /**
     * Método que crea una nueva cuenta
     * @param nombre nombre de la cuenta
     * @param participantes vector con los participantes de la cuenta
     */
    public abstract void crearCuenta(String nombre, Vector<String> participantes);

    /**
     * Método que borra una cuenta de la BBDD
     * @param nombre nombre de la cuenta
     */
    public abstract void borrarCuenta(String nombre);

    /**
     * Método que confirma si existe un participante en una cuenta
     * @param nombreP nombre del participante
     * @param nombreC nombre de la cuenta
     */
    public abstract boolean existsParticipante(String nombreP, String nombreC);

    /**
     * Método que permite efectuar un nuevo pago de un participante a otro
     * @param cuenta nombre de la cuenta sobre la que hacemos el pago
     * @param deudor persona que paga
     * @param acreedor persona que cobra
     * @param importe cantidad que se transfiere
     */
    public abstract void nuevoPago(String cuenta, String deudor, String acreedor, Double importe);

    /**
     * Método que permite hacer un nuevo gasto
     * @param cuenta nombre de la cuenta en la que hacemos el gasto
     * @param participante nombre del participante que hace el gasto
     * @param importe cantidad que gasta dicho participante
     */
    public abstract void nuevoGasto(String cuenta, String participante, Double importe);

    /**
     * Método que añade un participante a una cuenta
     * @param nombreP nombre del participante que añadimos
     * @param nombreC nombre de la cuenta en la que añadimos al participante
     */
    public abstract void anadirParticipante(String nombreP, String nombreC);

    /**
     * Método que obtiene los datos de una cuenta en forma de objeto DatosCuenta
     * @param cuenta nombre de la cuenta que consultamos
     */
    public abstract DatosCuenta obtenerDatosCuenta(String cuenta);

    /**
     * Método que obtiene los nombres de las distintas cuentas almacenadas
     */
    public abstract List<String> obtenerNombresCuentas();

    /**
     * Método que devuelve una lista con los nombres de los participantes de una cuenta
     * @param nombreCuenta nombre de la cuenta que consultamos
     */
    public abstract List<String> obtenerNombresParticipantes(String nombreCuenta);

    /**
     * Método que devuelve el tamaño de la lista de participantes de una cuenta
     * @param d cuenta sobre la que consultamos
     */
    public abstract int getParticipantesSize(DatosCuenta d);

    /**
     * Método que devuelve el nombre de un participante según su posición
     * @param d cuenta sobre la que consultamos
     * @param pos posición del participante que queremos consultar
     */
    public abstract String getNombreParticipante(DatosCuenta d, int pos);

    /**
     * Método que devuelve el dinero aportado por un participante en función de su posición
     * @param d cuenta sobre la que consultamos
     * @param pos posición del participante que queremos consultar
     */
    public abstract double getDineroParticipante(DatosCuenta d, int pos);

    /**
     * Método que devuelve el total de la cuenta
     * @param d cuenta sobre la que consultamos
     */
    public abstract double getTotalCuenta(DatosCuenta d);

    /**
     * Método que devuelve la deuda de cada participante sin tener en cuenta su aportación a la cuenta
     * @param d cuenta sobre la que consultamos
     */
    public abstract double getDeuda(DatosCuenta d);

    /**
     * Método que devuelve la deuda individual de un participante (teniendo en cuenta su aportación) en función de la posición
     * @param d cuenta sobre la que consultamos
     * @param pos posición del participante que queremos consultar
     */
    public abstract double getDeudaIndividual(DatosCuenta d, int pos);

}
