package fdi.ucm.appagar.presentacion;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fdi.ucm.appagar.R;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

public class FinalizarCuenta extends AppCompatActivity {

    Button botonFinalizar;
    EditText inputNombre;
    Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_cuenta);

        botonFinalizar = (Button)findViewById(R.id.buttonRemoveCuenta);
        inputNombre = (EditText)findViewById(R.id.inputNombreCuentaEliminar);
        controlador = new Controlador(getApplicationContext());
        botonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputNombre.getText().length() > 0){ //tambien comprobar qe exista, sino qe salte el toast y eso
                    if (controlador.exists(inputNombre.getText().toString())) {
                        controlador.borrarCuenta(inputNombre.getText().toString());
                        Toast toastCorrect = Toast.makeText(getApplicationContext(), "Cuenta eliminada con éxito", Toast.LENGTH_SHORT);
                        toastCorrect.show();
                    }
                    else {
                        Toast toastIncorrect = Toast.makeText(getApplicationContext(), "La cuenta introducida no existe", Toast.LENGTH_SHORT);
                        toastIncorrect.show();
                    }
                }
                else{
                    View view = FinalizarCuenta.this.getCurrentFocus();
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
