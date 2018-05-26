package fdi.ucm.appagar.presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import fdi.ucm.appagar.R;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

public class NuevoGasto extends AppCompatActivity {
    Button aceptar;
    Spinner participante;
    EditText cantidad;
    Controlador controlador;
    String cuenta;

    /**
     * Método que crea la actividad
     * @param savedInstanceState instancia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_gasto);

        aceptar = (Button)findViewById(R.id.buttonAceptarGasto);
        participante = (Spinner) findViewById(R.id.spinnerParticipanteGasto);
        cantidad = (EditText)findViewById(R.id.inputCantidadGasto);
        controlador = Controlador.getInstance(getApplicationContext());

        Bundle b = this.getIntent().getExtras();
        cuenta = b.getString("cuenta");

        //Adapta la lista de nombres de los participantes para introducirla en un spinner
        ArrayAdapter<String> adapterNombres = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, controlador.obtenerNombresParticipantes(cuenta));
        adapterNombres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        participante.setAdapter(adapterNombres);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cantidad.getText().length() > 0) {
                    try {
                        Double importe = Double.parseDouble(cantidad.getText().toString().replace(",", "."));
                        controlador.nuevoGasto(cuenta, participante.getSelectedItem().toString(), importe);
                        Toast toastcantidad = Toast.makeText(getApplicationContext(), "Gasto registrado con exito", Toast.LENGTH_SHORT);
                        toastcantidad.show();
                        Intent i = new Intent(NuevoGasto.this, GestionarCuenta.class);
                        Bundle b = new Bundle();
                        b.putString("cuenta", cuenta);
                        i.putExtras(b);
                        startActivity(i);
                    } catch (Exception e) {
                        Toast toastcantidad = Toast.makeText(getApplicationContext(), "El campo 'importe' debe ser un número", Toast.LENGTH_SHORT);
                        toastcantidad.show();
                    }
                }
                else {
                    Toast toastEmptyName = Toast.makeText(getApplicationContext(), "Introduce todos los datos necesarios", Toast.LENGTH_SHORT);
                    toastEmptyName.show();
                }
            }
        });
    }
}
