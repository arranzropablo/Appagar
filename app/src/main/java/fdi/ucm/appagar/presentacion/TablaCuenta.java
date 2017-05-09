package fdi.ucm.appagar.presentacion;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import fdi.ucm.appagar.R;
import fdi.ucm.appagar.negocio.DatosCuenta;
import fdi.ucm.appagar.presentacion.controlador.Controlador;

public class TablaCuenta extends AppCompatActivity {
    Tabla tabla;
    Controlador controlador;
    String cuenta;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_cuenta);

        controlador = new Controlador(getApplicationContext());
        Bundle b = this.getIntent().getExtras();
        cuenta = b.getString("cuenta");

        text = (TextView)findViewById(R.id.textTablaCuenta);
        tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.Tabla_Participantes);

        DatosCuenta lista = controlador.obtenerDatosCuenta(cuenta);
        text.setText("Total de la cuenta: " + lista.getTotalCuenta() + "€\n\n");
        text.setTextColor(Color.BLACK);

        for(int i = 0; i < lista.getNumParticipantes(); i++)
        {
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add(lista.getParticipante(i).getNombre());
            elementos.add(Double.toString(lista.getParticipante(i).getCantidad()) + "€");
            elementos.add(Double.toString(lista.getDeuda()) + "€");
            DecimalFormat df = new DecimalFormat("#.00");
            double cantidad = lista.getParticipante(i).getCantidad() - lista.getDeuda();
            String cant = df.format(cantidad);
            cant = cant.replace(',', '.');
            cantidad = Double.parseDouble(cant);
            elementos.add(Double.toString(cantidad));
            tabla.agregarFilaTabla(elementos);
        }
    }
}
