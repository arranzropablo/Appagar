package fdi.ucm.appagar.presentacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import fdi.ucm.appagar.R;

public class tablahtml extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablahtml);
        WebView myWebView = (WebView) this.findViewById(R.id.webViewTablaHtml);
        myWebView.loadUrl("file:///android_asset/vistaTablaCuenta/index.html");
    }
}
