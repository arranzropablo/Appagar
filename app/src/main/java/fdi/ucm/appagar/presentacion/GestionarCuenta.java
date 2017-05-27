package fdi.ucm.appagar.presentacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import fdi.ucm.appagar.ActivityInicio;
import fdi.ucm.appagar.R;
import fdi.ucm.appagar.negocio.DatosCuenta;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

public class GestionarCuenta extends AppCompatActivity {

    TextView text;
    Button gasto;
    Button pago;
    Button tabla;
    String cuenta;
    Controlador c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_cuenta);

        text = (TextView)findViewById(R.id.textNombreCuentaCargar);
        gasto = (Button)findViewById(R.id.buttonNuevoGasto);
        pago = (Button)findViewById(R.id.buttonNuevoPago);
        tabla = (Button)findViewById(R.id.buttonMostrarTabla);
        c = Controlador.getInstance(getApplicationContext());

        Bundle b = this.getIntent().getExtras();
        cuenta = b.getString("cuenta");
        text.setText("Cuenta '" + cuenta + "'.");

        gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("cuenta", cuenta);
                Intent i = new Intent(GestionarCuenta.this, NuevoGasto.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        pago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("cuenta", cuenta);
                Intent i = new Intent(GestionarCuenta.this, NuevoPago.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        tabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("cuenta", cuenta);
                Intent i = new Intent(GestionarCuenta.this, tablahtml.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAviso:


                final AlertDialog.Builder builderAviso = new AlertDialog.Builder(this);
                builderAviso.setTitle("Selecciona un participante al que avisar");

                final Spinner inputAviso = new Spinner(this);
                ArrayAdapter<String> adapterNombres = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, c.obtenerNombresParticipantes(cuenta));
                adapterNombres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                inputAviso.setAdapter(adapterNombres);

                builderAviso.setView(inputAviso);

                builderAviso.setPositiveButton("Avisar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String participanteSelected = inputAviso.getSelectedItem().toString();
                        DatosCuenta dCuenta = c.obtenerDatosCuenta(cuenta);
                        double cantidadDebesOTeDeben = dCuenta.getDeuda() - dCuenta.getParticipantePorNombre(participanteSelected).getCantidad();
                        String stringDebesOTeDeben = (cantidadDebesOTeDeben < 0) ? "te deben " : "tienes que pagar ";
                        cantidadDebesOTeDeben = (cantidadDebesOTeDeben < 0) ? (-1) * cantidadDebesOTeDeben : cantidadDebesOTeDeben;

                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! " + participanteSelected + ", estaba usando la aplicación Appagar para gestionar una cuenta que tenemos en común y he visto que aún " + stringDebesOTeDeben + cantidadDebesOTeDeben + "€ con motivo de la cuenta " + cuenta + ". No te descuides y un saludo!");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                    }
                });
                builderAviso.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builderAviso.show();


                break;

            case R.id.menuAnadir:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Añadir participante");

                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (input.getText().toString() == "") {
                            Toast toastEmptyName = Toast.makeText(getApplicationContext(), "Introduce un participante", Toast.LENGTH_SHORT);
                            toastEmptyName.show();
                        } else if (c.existsParticipante(input.getText().toString(), cuenta)) {
                            Toast toastExists = Toast.makeText(getApplicationContext(), "Ese participante ya está en la cuenta.", Toast.LENGTH_SHORT);
                            toastExists.show();
                            //dialog.cancel();
                        } else {
                            c.anadirParticipante(input.getText().toString(), cuenta);
                            Toast toastCorrect = Toast.makeText(getApplicationContext(), "Participante añadido con éxito", Toast.LENGTH_SHORT);
                            toastCorrect.show();
                        }
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                break;

                /*
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! La aplicacion Appagar es la leche!");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);



            */
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent i = new Intent(GestionarCuenta.this, ActivityInicio.class);
        startActivity(i);
        return true;
    }
}
