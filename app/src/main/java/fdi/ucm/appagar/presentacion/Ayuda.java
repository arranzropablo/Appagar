package fdi.ucm.appagar.presentacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fdi.ucm.appagar.R;

/**
 * Activity que muestra la ayuda para usar la aplicación
 */
public class Ayuda extends AppCompatActivity {
    /**
     * Método que crea la actividad
     * @param savedInstanceState instancia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }
}
