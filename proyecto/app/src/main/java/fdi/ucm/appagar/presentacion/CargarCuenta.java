package fdi.ucm.appagar.presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import fdi.ucm.appagar.R;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

/**
 * Activity que muestra la ventana asociada a la carga de las cuentas
 */
public class CargarCuenta extends AppCompatActivity {
    /**
     * Boton de aceptar la carga
     */
    private Button botonCargar;
    /**
     * Controlador de la aplicacion
     */
    private Controlador controlador;
    /**
     * Spinner que muestra todos los nombres de las cuentas existentes
     */
    private Spinner inputNombre;

    /**
     * Método que crea la actividad
     * @param savedInstanceState instancia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_cuenta);

        //Obtiene los elementos del layout
        botonCargar = (Button)findViewById(R.id.buttonLoadCuenta);
        inputNombre = (Spinner) findViewById(R.id.spinnerCargarNombres);
        controlador = Controlador.getInstance(getApplicationContext());

        //Crea un adapter con los nombres de las cuentas para mostrarlas mediante un spinner
        ArrayAdapter<String> adapterNombres = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, controlador.obtenerNombresCuentas());
        adapterNombres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputNombre.setAdapter(adapterNombres);


        botonCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputNombre.getSelectedItem() == null) {
                    Toast toastVacio = Toast.makeText(getApplicationContext(), "No hay cuenta seleccionada.", Toast.LENGTH_SHORT);
                    toastVacio.show();
                } else {
                    Bundle b = new Bundle();
                    b.putString("cuenta", inputNombre.getSelectedItem().toString());
                    Intent i = new Intent(CargarCuenta.this, GestionarCuenta.class);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });

    }
}
