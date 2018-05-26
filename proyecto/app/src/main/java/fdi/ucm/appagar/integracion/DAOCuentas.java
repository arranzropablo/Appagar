package fdi.ucm.appagar.integracion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fdi.ucm.appagar.negocio.DatosParticipante;

/**
 * Clase que se comunica con la base de datos de la aplicación
 */
public class DAOCuentas {
    /**
     * Clase que representa la base de datos
     */
    private BaseDeDatos base;
    /**
     * Base de datos de SQLite
     */
    private SQLiteDatabase bd;

    /**
     * Constructora de la clase
     * @param c contexto de la aplicación
     */
    public DAOCuentas (Context c) {
        base = new BaseDeDatos(c, "Base de datos", null, 1);
        //bd = base.getWritableDatabase();
    }

    /**
     * Abre la conexión a la base de datos para poder interactuar con la misma
     */
    public void abrirConexion() {
        bd = base.getWritableDatabase();
    }

    /**
     * Cierra la conexion con la base de datos
     */
    public void cerrarConexion() {
        bd.close();
    }

    /**
     * Comprueba que existe una cuenta en la base de datos
     * @param nombre nombre de la cuenta
     * @return booleano que indica si existe o no la cuenta
     */
    public boolean exists(String nombre) {

        try {
            String check = "SELECT name FROM sqlite_master WHERE type='table' AND UPPER(name)=UPPER('" + nombre + "')";
            bd.execSQL(check);
            return false;
        }
        catch (Exception e) {
            return true;
        }

    }

    /**
     * Crea una nueva cuenta (una nueva tabla) en la base de datos
     * @param nombre nombre de la cuenta
     */
    public void nuevaCuenta (String nombre) {
        String sqlcreate = "CREATE TABLE " + nombre + "(PARTICIPANTE TEXT PRIMARY KEY, CANTIDAD DOUBLE)";
        bd.execSQL(sqlcreate);
    }

    /**
     * Añade un nuevo participante en una cuenta de la base de datos
     * @param nombrePersona nombre del participante
     * @param nombreCuenta nombre de la cuenta
     */
    public void nuevoParticipante(String nombrePersona, String nombreCuenta) {
        String sqlinsert = "INSERT INTO " + nombreCuenta + " VALUES ('" + nombrePersona + "', 0)";
        bd.execSQL(sqlinsert);
    }

    /**
     * Elimina una cuenta de la base de datos
     * @param nombre nombre de la cuenta
     */
    public void borrarCuenta (String nombre) {
        String sqldelete = "DROP TABLE " + nombre;
        bd.execSQL(sqldelete);
    }

    /**
     * Comprueba si existe un participante en una cuenta
     * @param nombreP nombre del participante
     * @param nombreC nombre de la cuenta
     * @return booleano que indica si existe el participante o no
     */
    public boolean existsParticipante(String nombreP, String nombreC) {
        String [] s = new String [] {nombreP} ;
        Cursor c = bd.rawQuery("SELECT * FROM " + nombreC + " WHERE UPPER(PARTICIPANTE)=UPPER(?)", s);
        boolean b = c.moveToFirst();
        c.close();
        return b;
    }

    /**
     * Obtiene el importe pagado por un participante en una cuenta
     * @param cuenta nombre de la cuenta
     * @param deudor nombre del participante
     * @return importe total pagado por ese participante
     */
    public double obtenerImporte(String cuenta, String deudor) {
        String [] s = new String [] {deudor} ;
        Cursor c = bd.rawQuery("SELECT CANTIDAD FROM " + cuenta + " WHERE PARTICIPANTE=?", s);
        c.moveToFirst();
        int i = c.getInt(0);
        c.close();
        return i;
    }

    /**
     * Actualiza la cantidad pagada por un participante en una cuenta
     * @param cuenta nombre de la cuenta
     * @param participante nombre del participante
     * @param cantidad cantidad actual que ha pagado el participante
     */
    public void updateCantidad(String cuenta, String participante, double cantidad) {
        bd.execSQL("UPDATE " + cuenta + " SET CANTIDAD=" + cantidad + " WHERE PARTICIPANTE='" + participante + "'");
    }

    /**
     * Obtiene el importe total de una cuenta
     * @param cuenta nombre de la cuenta
     * @return importe total de una cuenta
     */
    public double obtenerTotalCuenta(String cuenta) {
        String [] s = new String [] {} ;
        Cursor c = bd.rawQuery("SELECT SUM(CANTIDAD) FROM " + cuenta, s);
        c.moveToFirst();
        int i = c.getInt(0);
        c.close();
        return i;
    }

    /**
     * Obtiene todos los datos de los participantes de una cuenta
     * @param cuenta nombre de la cuenta
     * @return array con los datos de todos los participantes
     */
    public ArrayList<DatosParticipante> obtenerParticipantes(String cuenta) {
        ArrayList<DatosParticipante> participantes = new ArrayList<>();
        String [] s = new String [] {} ;
        Cursor c = bd.rawQuery("SELECT * FROM " + cuenta, s);
        if (c.moveToFirst()) {
            do {
                participantes.add(new DatosParticipante(c.getString(0), c.getDouble(1)));
            }
            while (c.moveToNext());
        }
        c.close();
        return participantes;
    }

    /**
     * Obtiene todas las cuentas de la base de datos
     * @return array con los nombres de todas las cuentas (tal y como lo metio el usuario)
     */
    public List<String> obtenerNombresCuentas() {
        Cursor c = bd.rawQuery("SELECT name AS _id FROM sqlite_master WHERE type='table' and name like 'cuenta%'", null);
        List<String> names = new Vector<>();
        if (c.moveToFirst()) {
            do {
                if (c.getString(0).contains("cuenta_")) {
                    names.add(c.getString(0).replace("cuenta_", "").replace("_", " "));
                }
            }
            while (c.moveToNext());
        }
        c.close();
        return names;
    }

    /**
     * Obtiene todos los nombres de los participantes de una cuenta
     * @param nombreCuenta nombre de la cuenta
     * @return array con los nombres de los participantes de una cuenta
     */
    public List<String> obtenerNombresParticipantes(String nombreCuenta) {
        Cursor c = bd.rawQuery("SELECT PARTICIPANTE AS _id FROM " + nombreCuenta + ";", null);
        List<String> names = new Vector<>();
        if (c.moveToFirst()) {
            do {
                names.add(c.getString(0));
            }
            while (c.moveToNext());
        }
        c.close();
        return names;
    }

}
