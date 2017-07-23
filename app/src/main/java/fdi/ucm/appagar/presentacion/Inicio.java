package fdi.ucm.appagar.presentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fdi.ucm.appagar.R;

public class Inicio extends AppCompatActivity {

    //boton para crear una nueva cuenta
    Button nuevo;
    //boton para cargar una cuenta existente
    Button cargar;
    //boton para finalizar una cuenta existente
    Button finalizar;

    /**
     * Método que crea la actividad
     * @param savedInstanceState instancia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_inicio);

        nuevo = (Button)findViewById(R.id.buttonNuevaCuenta);
        cargar = (Button)findViewById(R.id.buttonCargarCuenta);
        finalizar = (Button)findViewById(R.id.buttonFinalizarCuenta);

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, NuevaCuenta.class);
                startActivity(i);
            }
        });

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, CargarCuenta.class);
                startActivity(i);
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, FinalizarCuenta.class);
                startActivity(i);
            }
        });
    }

    /**
     * Método que crea las opciones del menú según el xml menu1
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    /**
     * Método que redirige la aplicación a la vista necesaria según la opción elegida del menú
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.menuContacto:
                i = new Intent(Inicio.this, Contacto.class);
                startActivity(i);
                break;
            case R.id.menuAyuda:
                i = new Intent(Inicio.this, Ayuda.class);
                startActivity(i);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finishAffinity();
        System.exit(0);
        return true;
    }
}
