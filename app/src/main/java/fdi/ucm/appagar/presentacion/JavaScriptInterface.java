package fdi.ucm.appagar.presentacion;

import android.content.Context;
import android.webkit.JavascriptInterface;

import fdi.ucm.appagar.negocio.DatosCuenta;
import fdi.ucm.appagar.presentacion.controlador.Controlador;


/**
 * Clase que interactúa con la parte web (Javascript)
 */
public class JavaScriptInterface {

    private Context mContext;
    private DatosCuenta datos;
    private Controlador controlador;

    JavaScriptInterface(Context c, DatosCuenta d) {
        mContext = c;
        datos = d;
        controlador = Controlador.getInstance(mContext);
    }

    /**
     * Devuelve el numero de participantes de la cuenta actual
     */
    @JavascriptInterface
    public int getParticipantesSize() {
        return controlador.getParticipantesSize(this.datos);
    }

    /**
     * Devuelve el nombre de un participante según su posición en la lista
     */
    @JavascriptInterface
    public String getNombreParticipante(int pos) {
        return controlador.getNombreParticipante(this.datos, pos);
    }

    /**
     * Devuelve el dinero aportado por un participante según su posición en la lista
     */
    @JavascriptInterface
    public double getDineroParticipante(int pos) {
        return controlador.getDineroParticipante(this.datos, pos);
    }

    /**
     * Devuelve el total de dinero aportado entre todos los participantes a la cuenta
     */
    @JavascriptInterface
    public double getTotalCuenta() {
        return controlador.getTotalCuenta(this.datos);
    }

    /**
     * Devuelve la cantidad que debe pagar cada participante (sin tener en cuenta la cantidad ya pagada)
     */
    @JavascriptInterface
    public double getDeuda() {
        return controlador.getDeuda(this.datos);
    }

    /**
     * Devuelve la cantidad que debe pagar un participante teniendo en cuenta la cantidad que ya ha aportado
     */
    @JavascriptInterface
    public double getDeudaIndividual(int pos) {
        return controlador.getDeudaIndividual(this.datos, pos);
    }

}
