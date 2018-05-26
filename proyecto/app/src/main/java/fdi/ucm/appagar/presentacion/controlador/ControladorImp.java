package fdi.ucm.appagar.presentacion.controlador;

import android.content.Context;

import java.util.List;
import java.util.Vector;

import fdi.ucm.appagar.negocio.DatosCuenta;
import fdi.ucm.appagar.negocio.Operaciones;

public class ControladorImp extends Controlador {

    private Operaciones ops;

    public ControladorImp(Context c) {
        ops = new Operaciones(c);
    }

    public boolean exists(String nombre) {
        return ops.exists(nombre);
    }

    public void crearCuenta(String nombre, Vector<String> participantes) {
        ops.crearCuenta(nombre, participantes);
    }

    public void borrarCuenta(String nombre) {
        ops.borrarCuenta(nombre);
    }

    public boolean existsParticipante(String nombreP, String nombreC) {
        return ops.existsParticipante(nombreP, nombreC);
    }

    public void nuevoPago(String cuenta, String deudor, String acreedor, Double importe) {
        ops.nuevoPago(cuenta, deudor, acreedor, importe);
    }

    public void nuevoGasto(String cuenta, String participante, Double importe) {
        ops.nuevoGasto(cuenta, participante, importe);
    }

    public void anadirParticipante(String nombreP, String nombreC) {
        ops.anadirParticipante(nombreP, nombreC);
    }

    public DatosCuenta obtenerDatosCuenta(String cuenta) {
        return ops.obtenerDatosCuenta(cuenta);
    }

    public List<String> obtenerNombresCuentas() {
        return ops.obtenerNombresCuentas();
    }

    public List<String> obtenerNombresParticipantes(String nombreCuenta) {
        return ops.obtenerNombresParticipantes(nombreCuenta);
    }

    public int getParticipantesSize(DatosCuenta d) {
        return ops.getParticipantesSize(d);
    }

    public String getNombreParticipante(DatosCuenta d, int pos) {
        return ops.getNombreParticipante(d, pos);
    }

    public double getDineroParticipante(DatosCuenta d, int pos) {
        return ops.getDineroParticipante(d, pos);
    }

    public double getTotalCuenta(DatosCuenta d) {
        return ops.getTotalCuenta(d);
    }

    public double getDeuda(DatosCuenta d) {
        return ops.getDeuda(d);
    }

    public double getDeudaIndividual(DatosCuenta d, int pos) {
        return ops.getDeudaIndividual(d, pos);
    }

}
