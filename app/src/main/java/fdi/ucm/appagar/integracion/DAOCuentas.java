package fdi.ucm.appagar.integracion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fdi.ucm.appagar.negocio.DatosParticipante;

public class DAOCuentas {
    private BaseDeDatos base;
    private SQLiteDatabase bd;

    public DAOCuentas (Context c) {
        base = new BaseDeDatos(c, "Base de datos", null, 1);
        //bd = base.getWritableDatabase();
    }

    public void abrirConexion() {
        bd = base.getWritableDatabase();
    }

    public void cerrarConexion() {
        bd.close();
    }

    public boolean exists(String nombre) {

        try {
            String check = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+nombre+"'";
            bd.execSQL(check);
            return false;
        }
        catch (Exception e) {
            return true;
        }

    }

    public void nuevaCuenta (String nombre) {
        String sqlcreate = "CREATE TABLE " + nombre + "(PARTICIPANTE TEXT PRIMARY KEY, CANTIDAD DOUBLE)";
        bd.execSQL(sqlcreate);
    }

    public void nuevoParticipante(String nombrePersona, String nombreCuenta) {
        String sqlinsert = "INSERT INTO " + nombreCuenta + " VALUES ('" + nombrePersona + "', 0)";
        bd.execSQL(sqlinsert);
    }

    public void borrarCuenta (String nombre) {
        String sqldelete = "DROP TABLE " + nombre;
        bd.execSQL(sqldelete);
    }

    public boolean existsParticipante(String nombreP, String nombreC) {
        String [] s = new String [] {nombreP} ;
        Cursor c = bd.rawQuery("SELECT * FROM " + nombreC + " WHERE PARTICIPANTE=?", s);
        boolean b = c.moveToFirst();
        c.close();
        return b;
    }

    public double obtenerImporte(String cuenta, String deudor) {
        String [] s = new String [] {deudor} ;
        Cursor c = bd.rawQuery("SELECT CANTIDAD FROM " + cuenta + " WHERE PARTICIPANTE=?", s);
        c.moveToFirst();
        int i = c.getInt(0);
        c.close();
        return i;
    }

    public void updateCantidad(String cuenta, String participante, double cantidad) {
        bd.execSQL("UPDATE " + cuenta + " SET CANTIDAD=" + cantidad + " WHERE PARTICIPANTE='" + participante + "'");
    }

    public double obtenerTotalCuenta(String cuenta) {
        String [] s = new String [] {} ;
        Cursor c = bd.rawQuery("SELECT SUM(CANTIDAD) FROM " + cuenta, s);
        c.moveToFirst();
        int i = c.getInt(0);
        c.close();
        return i;
    }

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

    public List<String> obtenerNombresCuentas() {
        Cursor c = bd.rawQuery("SELECT name AS _id FROM sqlite_master WHERE type='table' and name like 'cuenta%'", null);       // consulta sobre sqlite-master
        // qe devuelva todas las tablas qe empiecen por "cuenta_"
        // no consigo escapar el _ (se supone qe con \_) asiqe cojo
        // tod0 lo qe empiece
        //por cuenta y luego ya lo filtro en codigo
        //aunqe en teoria no habra ninguna qe no tenga la _ si la base de datos es desde 0...
        //no se si deberiamos asegurarnos  o dejarlo porqe no habra ninguna
        List<String> names = new Vector<>();
        if (c.moveToFirst()) {
            do {
                if (c.getString(0).contains("cuenta_")) {
                    names.add(c.getString(0).replace("cuenta_", ""));
                }
            }
            while (c.moveToNext());
        }
        c.close();
        return names;
    }

    public List<String> obtenerNombresParticipantes(String nombreCuenta) {
        Cursor c = bd.rawQuery("SELECT PARTICIPANTE AS _id FROM " + nombreCuenta + ";", null);       // consulta sobre sqlite-master
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


    /*
    public boolean existeParticipante(String nombreP, String nombreC) {
        String [] s = new String [] {nombreP} ;
        Cursor c = bd.rawQuery("SELECT * FROM " + nombreC + " WHERE PARTICIPANTE=?", s);
        return c.moveToFirst();
    }
    */


    /*
       Esta es otra manera de comprobar si existe pero no se porqe los returns van al reves de lo que sería obvio (nota: en sqlite_master estan todas las tablas)
        try {
           String check = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+nombre+"'";
           bd.execSQL(check);
           return false;
       }
       catch (Exception e) {
           return true;
       }
       */
}
