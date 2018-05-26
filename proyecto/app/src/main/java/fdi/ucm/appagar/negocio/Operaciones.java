package fdi.ucm.appagar.negocio;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fdi.ucm.appagar.integracion.DAOCuentas;

/**
 * Clase que realiza todas las operaciones de negocio
 */
public class Operaciones {
    /**
     * DAO para acceder a la base de datos
     */
    private DAOCuentas dao;

    /**
     * Constructora de la clase
     * @param c Contexto de la aplicación
     */
    public Operaciones (Context c) {
        dao = new DAOCuentas(c);
    }

    /**
     * Devuelve el nombre de una cuenta (ya que se usa un nombre especifico para guardar en la base de datos)
     * @param nombre nombre de la cuenta que mete el usuario
     * @return nombre apto para ser tabla de la base de datos
     */
    private String nombreCuenta(String nombre){
        return ("cuenta_" + nombre).replace(" ", "_");
    }

    /**
     * Función que comprueba si existe una cuenta
     * @param nombre nombre de la cuenta
     * @return booleano que indica si existe o no la cuenta
     */
    public boolean exists(String nombre) {
        dao.abrirConexion();

        boolean exists = dao.exists(nombreCuenta(nombre));

        dao.cerrarConexion();
        return exists;
    }

    /**
     * Funcion para crear una nueva cuenta
     * @param nombre nombre de la cuenta
     * @param participantes array de los participantes que pertenecen a esa cuenta
     */
    public void crearCuenta (String nombre, Vector<String> participantes) {
        dao.abrirConexion();

        dao.nuevaCuenta(nombreCuenta(nombre));
        for(String p : participantes){
            dao.nuevoParticipante(p, nombreCuenta(nombre));
        }

        dao.cerrarConexion();
    }

    /**
     * Funcion que elimina una cuenta
     * @param nombre nombre de la cuenta
     */
    public void borrarCuenta(String nombre) {
        dao.abrirConexion();

        dao.borrarCuenta(nombreCuenta(nombre));

        dao.cerrarConexion();
    }

    /**
     * Funcion que comprueba si existe un participante en una cuenta
     * @param nombreP nombre del participante
     * @param nombreC nombre de la cuenta
     * @return booleano que indica si existe o no ese participante en esa cuenta
     */
    public boolean existsParticipante(String nombreP, String nombreC) {
        dao.abrirConexion();

        boolean correcto = dao.existsParticipante(nombreP, nombreCuenta(nombreC));

        dao.cerrarConexion();
        return correcto;
    }

    /**
     * Funcion para realizar un nuevo pago
     * @param cuenta nombre de la cuenta
     * @param paga nombre del participante que realiza el pago
     * @param recibe nombre del participante que recibe el pago
     * @param importe cantidad del pago
     */
    public void nuevoPago(String cuenta, String paga, String recibe, Double importe) {
        dao.abrirConexion();

        double cantidadPaga = dao.obtenerImporte(nombreCuenta(cuenta), paga);
        double cantidadRecibe = dao.obtenerImporte(nombreCuenta(cuenta), recibe);

        cantidadPaga += importe;
        cantidadRecibe -= importe;

        dao.updateCantidad(nombreCuenta(cuenta), paga, cantidadPaga);
        dao.updateCantidad(nombreCuenta(cuenta), recibe, cantidadRecibe);

        dao.cerrarConexion();
    }

    /**
     * Función que registra un nuevo gasto en una cuenta
     * @param cuenta nombre de la cuenta
     * @param participante nombre del participante que ha tenido el gasto
     * @param importe cantidad del gasto
     */
    public void nuevoGasto(String cuenta, String participante, Double importe) {
        dao.abrirConexion();

        double cantidad = dao.obtenerImporte(nombreCuenta(cuenta), participante);
        cantidad += importe;
        dao.updateCantidad(nombreCuenta(cuenta), participante, cantidad);

        dao.cerrarConexion();
    }

    /**
     * Funcion que sirve para obtener los datos de una cuenta
     * @param cuenta nombre de la cuenta
     * @return datos de la cuenta
     */
    public DatosCuenta obtenerDatosCuenta(String cuenta) {
        dao.abrirConexion();

        ArrayList<DatosParticipante> participantes = dao.obtenerParticipantes(nombreCuenta(cuenta));
        double totalCuenta = dao.obtenerTotalCuenta(nombreCuenta(cuenta));
        DatosCuenta datos = new DatosCuenta(totalCuenta, participantes);

        dao.cerrarConexion();
        return datos;
    }

    /**
     * Funcion que sirve para obtener todas las cuentas guardadas en la aplicación
     * @return array con los nombres de las cuentas
     */
    public List<String> obtenerNombresCuentas() {
        dao.abrirConexion();
        List<String> nombresCuentas = dao.obtenerNombresCuentas();
        dao.cerrarConexion();
        return nombresCuentas;
    }

    /**
     * Funcion que sirve para obtener todos los participantes de una cuenta
     * @param nC nombre de la cuenta
     * @return array con los nombres de los participantes de dicha cuenta
     */
    public List<String> obtenerNombresParticipantes(String nC) {
        dao.abrirConexion();
        List<String> nombresParticipantes = dao.obtenerNombresParticipantes(nombreCuenta(nC));
        dao.cerrarConexion();
        return nombresParticipantes;
    }

    /**
     * Funcion que añade un nuevo participante a una cuenta
     * @param nombreP nombre del participante
     * @param nombreC nombre de la cuenta
     */
    public void anadirParticipante(String nombreP, String nombreC) {
        dao.abrirConexion();
        dao.nuevoParticipante(nombreP, nombreCuenta(nombreC));
        dao.cerrarConexion();
    }

    /**
     * Obtiene el numero de participantes de una cuenta
     * @param d datos de la cuenta
     * @return numero de participamtes de la cuenta
     */
    public int getParticipantesSize(DatosCuenta d) {
        return d.getNumParticipantes();
    }

    /**
     * Funcion que obtiene el nombre de un participante de una posición dada
     * @param d datos de una cuenta
     * @param pos posicion del participante
     * @return nombre del participante
     */
    public String getNombreParticipante(DatosCuenta d, int pos) {
        return d.getParticipante(pos).getNombre();
    }

    /**
     * Funcion que obtiene el dinero que ha pagado un participante
     * @param d datos de la cuenta
     * @param pos posiciondel participante
     * @return dinero total que ha pagado ese participante
     */
    public double getDineroParticipante(DatosCuenta d, int pos) {
        return d.getParticipante(pos).getCantidad();
    }

    /**
     * Función que obtiene el total del dinero gastado en una cuenta
     * @param d datos de la cuenta
     * @return importe total de la cuenta
     */
    public double getTotalCuenta(DatosCuenta d) {
        return d.getTotalCuenta();
    }

    /**
     * Función que obtiene cuanto debe cada participante de una cuenta
     * @param d datos de la cuenta
     * @return importe que debe cada participante en la cuenta
     */
    public double getDeuda(DatosCuenta d) {
        return d.getDeuda();
    }

    /**
     * Funcion que devuelve cuanto tiene que pagar o le tienen que pagar a un participante
     * @param d datos de la cuenta
     * @param pos posicion del partcipante
     * @return dinero que debe pagar ese participante
     */
    public double getDeudaIndividual(DatosCuenta d, int pos) {
        return getDeuda(d) - d.getParticipante(pos).getCantidad();
    }


}
