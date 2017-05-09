package fdi.ucm.appagar.presentacion;

import android.content.Context;
import android.webkit.JavascriptInterface;

import fdi.ucm.appagar.negocio.DatosCuenta;

/**
 * Created by Pablo Arranz on 09/05/2017.
 */

public class JavaScriptInterface {

    private Context mContext;
    private DatosCuenta datos;

    JavaScriptInterface(Context c, DatosCuenta d) {
        mContext = c;
        datos = d;
    }

    //http://stackoverflow.com/questions/11752052/passing-data-from-java-class-to-web-view-html
    //Aqui ponemos las funciones qe vamos a usar desde javascript
    @JavascriptInterface
    public int getCantidad() {
        return 1;
    }

    @JavascriptInterface
    public int getParticipantesSize() {
        //esto devuelve el num de participantes y segun eso hacemos las columnas qe sean
        //http://stackoverflow.com/questions/14964253/how-to-dynamically-add-a-new-column-to-an-html-table
        return datos.getNumParticipantes();
    }

}
