package fdi.ucm.appagar.presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import fdi.ucm.appagar.R;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

public class NuevaCuenta extends AppCompatActivity {

    private Button nuevo;
    private Button anadir;
    private Button continuar;
    private TextView texto;
    private EditText nuevaCuenta;
    private EditText aniadirParticipante;
    private Controlador controlador;
    private Vector<String> participantes = new Vector<>();
    private String nombreCuenta;
    private TextView currentParticipantes;

    /**
     * Método que crea la actividad
     * @param savedInstanceState instancia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_cuenta);

        nuevo = (Button)findViewById(R.id.buttonCrearCuenta);
        anadir = (Button)findViewById(R.id.buttonAnadirParticipante);
        continuar = (Button)findViewById(R.id.buttonContinuar);
        texto = (TextView)findViewById(R.id.textInfo);
        nuevaCuenta = (EditText)findViewById(R.id.inputNombreCuenta);
        aniadirParticipante = (EditText)findViewById(R.id.inputAnadirParticipante);
        currentParticipantes = (TextView) findViewById(R.id.currentParticipantes);

        anadir.setEnabled(false);
        continuar.setEnabled(false);
        aniadirParticipante.setEnabled(false);

        controlador = Controlador.getInstance(getApplicationContext());

        //Permite introducir el nombre de la cuenta a crear y el nombre de los distintos participantes
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nuevaCuenta.getText().length() <= 0) {
                    Toast toastEmptyName = Toast.makeText(getApplicationContext(), "Introduce un nombre.", Toast.LENGTH_SHORT);
                    toastEmptyName.show();
                }
                else if (controlador.exists(nuevaCuenta.getText().toString())){
                    Toast toastExists = Toast.makeText(getApplicationContext(), "La cuenta ya existe.", Toast.LENGTH_SHORT);
                    toastExists.show();
                }

                else {
                    anadir.setEnabled(true);
                    aniadirParticipante.setEnabled(true);
                    nuevaCuenta.setEnabled(false);
                    nuevo.setEnabled(false);
                    nombreCuenta = nuevaCuenta.getText().toString();
                    texto.setText("Cuando acabes de añadir participantes, pulsa continuar.");
                }
            }
        });

        //Se activa cuando introducimos un nuevo nombre de cuenta.
        //Por cada participante añadido lo añade a una lista escrita debajo del botón para poder observar los nombres añadidos.
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aniadirParticipante.getText().length() > 0){
                    if(participantes.isEmpty() || !participantes.contains(aniadirParticipante.getText().toString())) {
                        participantes.add(aniadirParticipante.getText().toString());
                        continuar.setEnabled(true);
                        currentParticipantes.append(aniadirParticipante.getText().toString() + " añadido." + System.getProperty("line.separator"));
                        aniadirParticipante.setText("");

                        Toast toastCorrect = Toast.makeText(getApplicationContext(), "Participante añadido.", Toast.LENGTH_SHORT);
                        toastCorrect.show();
                    }
                    else {
                        Toast toastExists = Toast.makeText(getApplicationContext(), "El participante introducido ya está en la cuenta. Prueba con otro.", Toast.LENGTH_SHORT);
                        toastExists.show();
                    }
                }
                else{
                    Toast toastEmptyName = Toast.makeText(getApplicationContext(), "Introduce un nombre.", Toast.LENGTH_SHORT);
                    toastEmptyName.show();
                }
            }
        });

        //Se activa cuando introducimos el primer participante.
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlador.crearCuenta(nombreCuenta, participantes);
                Bundle b = new Bundle();
                b.putString("cuenta", nuevaCuenta.getText().toString());
                Intent i = new Intent(NuevaCuenta.this, GestionarCuenta.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}
