package fdi.ucm.appagar.presentacion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fdi.ucm.appagar.R;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

public class CargarCuenta extends AppCompatActivity {

    Button botonCargar;
    EditText inputNombre;
    Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_cuenta);

        botonCargar = (Button)findViewById(R.id.buttonLoadCuenta);
        inputNombre = (EditText)findViewById(R.id.inputNombreCuentaCargar);
        controlador = new Controlador(getApplicationContext());

        botonCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputNombre.getText().length() > 0){
                    if (controlador.exists(inputNombre.getText().toString())) {
                        Bundle b = new Bundle();
                        b.putString("cuenta", inputNombre.getText().toString());
                        Intent i = new Intent(CargarCuenta.this, GestionarCuenta.class);
                        i.putExtras(b);
                        startActivity(i);
                    }
                    else {
                        Toast toastIncorrect = Toast.makeText(getApplicationContext(), "La cuenta introducida no existe", Toast.LENGTH_SHORT);
                        toastIncorrect.show();
                    }
                }
                else{
                    View view = CargarCuenta.this.getCurrentFocus();
                    view.clearFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    Toast toastEmptyName = Toast.makeText(getApplicationContext(), "Introduce un nombre.", Toast.LENGTH_SHORT);
                    toastEmptyName.show();
                }

            }
        });
    }
}
