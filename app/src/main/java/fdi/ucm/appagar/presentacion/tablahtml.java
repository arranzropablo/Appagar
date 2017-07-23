package fdi.ucm.appagar.presentacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import fdi.ucm.appagar.R;
import fdi.ucm.appagar.negocio.DatosCuenta;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

public class tablahtml extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablahtml);

        Controlador controlador = Controlador.getInstance(getApplicationContext());

        Bundle b = this.getIntent().getExtras();
        String cuenta = b.getString("cuenta");

        DatosCuenta datos = controlador.obtenerDatosCuenta(cuenta);

        WebView myWebView = (WebView) this.findViewById(R.id.webViewTablaHtml);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new JavaScriptInterface(this, datos), "Android");
        myWebView.loadUrl("file:///android_asset/vistaTablaCuenta/index.html");
    }
}
