package fdi.ucm.appagar.integracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase que representa la base de datos en la aplicacion
 */
public class BaseDeDatos extends SQLiteOpenHelper {
    /**
     * Constructora de la clase
     * @param context contexto de la aplicación
     * @param name nombre de la base de datos
     * @param factory factoria de sqlite
     * @param version version de la base de datos
     */
    public BaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Funcion que crea las tablas iniciales de la base de datos (en esta aplicación, no hay tablas por defecto)
     * @param sqLiteDatabase base de datos
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    /**
     * Funcion que especifica que hacer cuando la base de datos cambia de version
     * @param sqLiteDatabase base de datos
     * @param i version actual
     * @param i1 proxima version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
