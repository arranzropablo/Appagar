package fdi.ucm.appagar.negocio;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fdi.ucm.appagar.integracion.DAOCuentas;

public class Operaciones {
    private DAOCuentas dao;

    public Operaciones (Context c) {
        dao = new DAOCuentas(c);
    }

    private String nombreCuenta(String nombre){
        return ("cuenta_" + nombre).replace(" ", "_");
    }

    public boolean exists(String nombre) {
        dao.abrirConexion();

        boolean exists = dao.exists(nombreCuenta(nombre));

        dao.cerrarConexion();
        return exists;
    }

    public void crearCuenta (String nombre, Vector<String> participantes) {
        dao.abrirConexion();

        dao.nuevaCuenta(nombreCuenta(nombre));
        for(String p : participantes){
            dao.nuevoParticipante(p, nombreCuenta(nombre));
        }

        dao.cerrarConexion();
    }

    public void borrarCuenta(String nombre) {
        dao.abrirConexion();

        dao.borrarCuenta(nombreCuenta(nombre));

        dao.cerrarConexion();
    }

    public boolean existsParticipante(String nombreP, String nombreC) {
        dao.abrirConexion();

        boolean correcto = dao.existsParticipante(nombreP, nombreCuenta(nombreC));

        dao.cerrarConexion();
        return correcto;
    }

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

    public void nuevoGasto(String cuenta, String participante, Double importe) {
        dao.abrirConexion();

        double cantidad = dao.obtenerImporte(nombreCuenta(cuenta), participante);
        cantidad += importe;
        dao.updateCantidad(nombreCuenta(cuenta), participante, cantidad);

        dao.cerrarConexion();
    }

    public DatosCuenta obtenerDatosCuenta(String cuenta) {
        dao.abrirConexion();

        ArrayList<DatosParticipante> participantes = dao.obtenerParticipantes(nombreCuenta(cuenta));
        double totalCuenta = dao.obtenerTotalCuenta(nombreCuenta(cuenta));
        DatosCuenta datos = new DatosCuenta(totalCuenta, participantes);

        dao.cerrarConexion();
        return datos;
    }

    public List<String> obtenerNombresCuentas() {
        dao.abrirConexion();
        List<String> nombresCuentas = dao.obtenerNombresCuentas();
        dao.cerrarConexion();
        return nombresCuentas;
    }

    public List<String> obtenerNombresParticipantes(String nC) {
        dao.abrirConexion();
        List<String> nombresParticipantes = dao.obtenerNombresParticipantes(nombreCuenta(nC));
        dao.cerrarConexion();
        return nombresParticipantes;
    }
}
