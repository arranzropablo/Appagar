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

public class NuevoPago extends AppCompatActivity {
    Button aceptar;
    EditText participante1;
    EditText participante2;
    EditText cantidad;
    Controlador controlador;
    String cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_pago);

        aceptar = (Button)findViewById(R.id.buttonAceptarPago);
        participante1 = (EditText)findViewById(R.id.inputParticipante1Pago);
        participante2 = (EditText)findViewById(R.id.inputParticipante2Pago);
        cantidad = (EditText)findViewById(R.id.inputCantidadPago);
        controlador = new Controlador(getApplicationContext());

        Bundle b = this.getIntent().getExtras();
        cuenta = b.getString("cuenta");

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (participante1.getText().length() > 0 && participante2.getText().length() > 0 && cantidad.getText().length() > 0) {
                    if (controlador.existsParticipante(participante1.getText().toString(), cuenta)) {
                        if (controlador.existsParticipante(participante2.getText().toString(), cuenta)) {
                            try {
                                Double importe = Double.parseDouble(cantidad.getText().toString());
                                controlador.nuevoPago(cuenta, participante1.getText().toString(), participante2.getText().toString(), importe);
                                Toast toastcantidad = Toast.makeText(getApplicationContext(), "Pago realizado con exito", Toast.LENGTH_SHORT);
                                toastcantidad.show();
                                Intent i = new Intent (NuevoPago.this, GestionarCuenta.class);
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
                            Toast toastexists = Toast.makeText(getApplicationContext(), "El segundo participante no existe", Toast.LENGTH_SHORT);
                            toastexists.show();
                        }
                    }
                    else {
                        Toast toastexists = Toast.makeText(getApplicationContext(), "El primer participante no existe", Toast.LENGTH_SHORT);
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
