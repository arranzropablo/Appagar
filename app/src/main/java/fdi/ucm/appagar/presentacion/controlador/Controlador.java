package fdi.ucm.appagar.presentacion.controlador;

import android.content.Context;

import java.util.List;
import java.util.Vector;

import fdi.ucm.appagar.negocio.DatosCuenta;

abstract public class Controlador {

    private static Controlador controlador;

    public static Controlador getInstance(Context c) {
        if (controlador == null) {
            controlador = new ControladorImp(c);
        }
        return controlador;
    }

    public abstract boolean exists(String nombre);

    public abstract void crearCuenta(String nombre, Vector<String> participantes);

    public abstract void borrarCuenta(String nombre);

    public abstract boolean existsParticipante(String nombreP, String nombreC);

    public abstract void nuevoPago(String cuenta, String deudor, String acreedor, Double importe);

    public abstract void nuevoGasto(String cuenta, String participante, Double importe);

    public abstract void anadirParticipante(String nombreP, String nombreC);

    public abstract DatosCuenta obtenerDatosCuenta(String cuenta);

    public abstract List<String> obtenerNombresCuentas();

    public abstract List<String> obtenerNombresParticipantes(String nombreCuenta);

}
