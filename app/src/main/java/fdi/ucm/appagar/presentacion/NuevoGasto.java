package fdi.ucm.appagar.presentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fdi.ucm.appagar.R;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

public class NuevoGasto extends AppCompatActivity {
    Button aceptar;
    EditText participante;
    EditText cantidad;
    Controlador controlador;
    String cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_gasto);

        aceptar = (Button)findViewById(R.id.buttonAceptarGasto);
        participante = (EditText)findViewById(R.id.inputParticipanteGasto);
        cantidad = (EditText)findViewById(R.id.inputCantidadGasto);
        controlador = new Controlador(getApplicationContext());

        Bundle b = this.getIntent().getExtras();
        cuenta = b.getString("cuenta");

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (participante.getText().length() > 0 && cantidad.getText().length() > 0) {
                    if (controlador.existsParticipante(participante.getText().toString(), cuenta)) {
                        try {
                            Double importe = Double.parseDouble(cantidad.getText().toString());
                            controlador.nuevoGasto(cuenta, participante.getText().toString(), importe);
                            Toast toastcantidad = Toast.makeText(getApplicationContext(), "Gasto registrado con exito", Toast.LENGTH_SHORT);
                            toastcantidad.show();
                            Intent i = new Intent (NuevoGasto.this, GestionarCuenta.class);
                            Bundle b = new Bundle();
                            b.putString("cuenta", cuenta);
                            i.putExtras(b);
                            startActivity(i);
                        }
                        catch (Exception e) {
                            Toast toastcantidad = Toast.makeText(getApplicationContext(), "El campo 'importe' debe ser un número", Toast.LENGTH_SHORT);
                            toastcantidad.show();
                        }
                    }
                    else {
                        Toast toastexists = Toast.makeText(getApplicationContext(), "El participante no existe", Toast.LENGTH_SHORT);
                        toastexists.show();
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
