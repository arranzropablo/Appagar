package fdi.ucm.appagar.presentacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fdi.ucm.appagar.R;

/**
 * Activity asociada con la información sobre los creadores de la aplicación
 */
public class Contacto extends AppCompatActivity {
    /**
     * Método que crea la actividad
     * @param savedInstanceState instancia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

    }
}
