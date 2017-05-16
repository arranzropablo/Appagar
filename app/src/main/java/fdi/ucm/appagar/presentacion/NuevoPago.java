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

public class NuevoPago extends AppCompatActivity {
    Button aceptar;
    Spinner participantePaga;
    Spinner participanteRecibe;
    EditText cantidad;
    Controlador controlador;
    String cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_pago);

        aceptar = (Button)findViewById(R.id.buttonAceptarPago);
        participantePaga = (Spinner) findViewById(R.id.spinnerParticipantePaga);
        participanteRecibe = (Spinner) findViewById(R.id.spinnerParticipanteRecibe);
        cantidad = (EditText)findViewById(R.id.inputCantidadPago);
        controlador = Controlador.getInstance(getApplicationContext());

        Bundle b = this.getIntent().getExtras();
        cuenta = b.getString("cuenta");

        ArrayAdapter<String> adapterNombres = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, controlador.obtenerNombresParticipantes(cuenta));
        adapterNombres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        participantePaga.setAdapter(adapterNombres);
        participanteRecibe.setAdapter(adapterNombres);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (participanteRecibe.getSelectedItem().equals(participantePaga.getSelectedItem())) {
                    Toast toastError = Toast.makeText(getApplicationContext(), "No puedes pagarte a ti mismo.", Toast.LENGTH_SHORT);
                    toastError.show();
                }
                else {
                    if (cantidad.getText().length() > 0) {
                        try {
                            Double importe = Double.parseDouble(cantidad.getText().toString().replace(",", "."));
                            controlador.nuevoPago(cuenta, participantePaga.getSelectedItem().toString(), participanteRecibe.getSelectedItem().toString(), importe);
                            Toast toastcantidad = Toast.makeText(getApplicationContext(), "Pago realizado con exito", Toast.LENGTH_SHORT);
                            toastcantidad.show();
                            Intent i = new Intent(NuevoPago.this, GestionarCuenta.class);
                            Bundle b = new Bundle();
                            b.putString("cuenta", cuenta);
                            i.putExtras(b);
                            startActivity(i);
                        } catch (Exception e) {
                            Toast toastcantidad = Toast.makeText(getApplicationContext(), "El campo 'importe' debe ser un número", Toast.LENGTH_SHORT);
                            toastcantidad.show();
                        }
                    } else {
                        Toast toastEmptyName = Toast.makeText(getApplicationContext(), "Introduce todos los datos necesarios", Toast.LENGTH_SHORT);
                        toastEmptyName.show();
                    }
                }
            }
        });
    }
}
