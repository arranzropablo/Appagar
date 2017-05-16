package fdi.ucm.appagar.presentacion;

import android.content.Context;
import android.webkit.JavascriptInterface;

import fdi.ucm.appagar.negocio.DatosCuenta;

public class JavaScriptInterface {

    private Context mContext;
    private DatosCuenta datos;

    JavaScriptInterface(Context c, DatosCuenta d) {
        mContext = c;
        datos = d;
    }

    @JavascriptInterface
    public int getParticipantesSize() {
        return datos.getNumParticipantes();
    }

    @JavascriptInterface
    public String getNombreParticipante(int pos) {
        return datos.getParticipante(pos).getNombre();
    }

    @JavascriptInterface
    public double getDineroParticipante(int pos) {
        return datos.getParticipante(pos).getCantidad();
    }

    @JavascriptInterface
    public double getTotalCuenta() {
        return datos.getTotalCuenta();
    }

    @JavascriptInterface
    public double getDeuda() {
        return datos.getDeuda();
    }

    @JavascriptInterface
    public double getDeudaIndividual(int pos) {
        return getDeuda() - datos.getParticipante(pos).getCantidad();
    }

}
