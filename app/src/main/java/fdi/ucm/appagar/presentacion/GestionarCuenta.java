package fdi.ucm.appagar.presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fdi.ucm.appagar.ActivityInicio;
import fdi.ucm.appagar.R;
import fdi.ucm.appagar.tablahtml;

public class GestionarCuenta extends AppCompatActivity {

    TextView text;
    Button gasto;
    Button pago;
    Button tabla;
    String cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_cuenta);

        text = (TextView)findViewById(R.id.textNombreCuentaCargar);
        gasto = (Button)findViewById(R.id.buttonNuevoGasto);
        pago = (Button)findViewById(R.id.buttonNuevoPago);
        tabla = (Button)findViewById(R.id.buttonMostrarTabla);

        Bundle b = this.getIntent().getExtras();
        cuenta = b.getString("cuenta");
        text.setText("Cuenta '" + cuenta + "'");

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
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! La aplicacion Appagar es la leche!");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
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
