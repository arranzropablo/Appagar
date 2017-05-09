package fdi.ucm.appagar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fdi.ucm.appagar.presentacion.Ayuda;
import fdi.ucm.appagar.presentacion.CargarCuenta;
import fdi.ucm.appagar.presentacion.Contacto;
import fdi.ucm.appagar.presentacion.FinalizarCuenta;
import fdi.ucm.appagar.presentacion.NuevaCuenta;

public class ActivityInicio extends AppCompatActivity {

    Button nuevo;
    Button cargar;
    Button finalizar;

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
                Intent i = new Intent(ActivityInicio.this, NuevaCuenta.class);
                startActivity(i);
            }
        });

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityInicio.this, CargarCuenta.class);
                startActivity(i);
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityInicio.this, FinalizarCuenta.class);
                startActivity(i);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.menuContacto:
                i = new Intent(ActivityInicio.this, Contacto.class);
                startActivity(i);
                break;
            case R.id.menuAyuda:
                i = new Intent(ActivityInicio.this, Ayuda.class);
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
