package fdi.ucm.appagar.negocio;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DatosCuenta {
    private double totalCuenta;
    private double deuda;
    private ArrayList<DatosParticipante> participantes;

    public DatosCuenta(double totalCuenta, ArrayList<DatosParticipante> participantes) {
        this.totalCuenta = totalCuenta;
        this.participantes = participantes;
        this.deuda = totalCuenta / participantes.size();
        ajustarDecimales();
    }

    public double getTotalCuenta () {
        return this.totalCuenta;
    }

    public int getNumParticipantes () {
        return participantes.size();
    }

    public double getDeuda () {
        return this.deuda;
    }

    public DatosParticipante getParticipante(int posicion) {
        return participantes.get(posicion);
    }

    public DatosParticipante getParticipantePorNombre(String nombre) {
        boolean enc = false;
        int i = 0;
        while (i < participantes.size() && !enc) {
            if (participantes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                enc = true;
            }
            ++i;
        }
        return participantes.get(i - 1);
    }


    private void ajustarDecimales() {
        DecimalFormat df = new DecimalFormat("#.00");

        String totalCuenta = df.format(this.totalCuenta);
        totalCuenta = totalCuenta.replace(',', '.');
        this.totalCuenta = Double.parseDouble(totalCuenta);
        String deuda = df.format(this.deuda);
        deuda = deuda.replace(',', '.');
        this.deuda = Double.parseDouble(deuda);
        for (int i = 0; i < participantes.size(); ++i) {
            String cantidad = df.format(this.participantes.get(i).getCantidad());
            cantidad = cantidad.replace(',', '.');
            this.participantes.get(i).setCantidad(Double.parseDouble(cantidad));
        }
    }
}
