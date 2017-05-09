package fdi.ucm.appagar.negocio;

import android.content.Context;

import java.util.ArrayList;
import java.util.Vector;

import fdi.ucm.appagar.integracion.DAOCuentas;

public class Operaciones {
    DAOCuentas dao;

    public Operaciones (Context c) {
        dao = new DAOCuentas(c);
    }

    private String nombreCuenta(String nombre){
        return "cuenta_" + nombre;
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

        double cantidadPaga = dao.obtenerImporte(nombreCuenta(cuenta), paga);             //he cambiado lo de deudor o acreedor porqe si alguien qe no tenga ni idea de qe significa eso lee nuestro codigo
        double cantidadRecibe = dao.obtenerImporte(nombreCuenta(cuenta), recibe);         //lo mas probable es que se haga un lio (de hecho yo no sabia quien era quien, he tenido qe depurar), asi se entiende

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


}
