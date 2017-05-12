package fdi.ucm.appagar.presentacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import fdi.ucm.appagar.R;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

public class FinalizarCuenta extends AppCompatActivity {

    Button botonFinalizar;
    Spinner inputNombre;
    Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_cuenta);

        botonFinalizar = (Button)findViewById(R.id.buttonRemoveCuenta);
        inputNombre = (Spinner) findViewById(R.id.spinnerFinalizarNombres);
        controlador = new Controlador(getApplicationContext());

        ArrayAdapter<String> adapterNombres = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, controlador.obtenerNombresCuentas());
        adapterNombres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputNombre.setAdapter(adapterNombres);

        botonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.borrarCuenta(inputNombre.getSelectedItem().toString());
                Toast toastCorrect = Toast.makeText(getApplicationContext(), "Cuenta eliminada con éxito", Toast.LENGTH_SHORT);
                toastCorrect.show();

            }
        });

    }
}
