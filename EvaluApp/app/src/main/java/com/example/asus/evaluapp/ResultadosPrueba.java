package com.example.asus.evaluapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadosPrueba extends AppCompatActivity {

    Carchivos objFilePruebasEstudiantes;

    TextView tvPrueba, tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_prueba);
        connect();

        objFilePruebasEstudiantes = new Carchivos(this, "PruebasEstudiantes.txt");

        String nombrePrueba = IngresoDocente.nombrePrueba;
        String DNI = "";
        double puntaje = 0;

        tvPrueba.setText("Resultados de la prueba: "+nombrePrueba);

        String leerInicio;
        leerInicio = objFilePruebasEstudiantes.leer();
        String[] arrLeer = leerInicio.split("-#-");

        for (int t = 0; t < arrLeer.length; t++) {
            String[] arr = arrLeer[t].split("#&");
            Toast.makeText(getApplicationContext(),arr[1],Toast.LENGTH_LONG).show();
            if (arr[1].equals(nombrePrueba)) {
                DNI = arr[0];
                puntaje = Double.parseDouble(arr[2]);
                tvResult.setText("DNI: "+DNI+"\nCalificación obtenida: "+puntaje+"%\n----------------------------------\n");
                break;
            }

        }
    }

    private void connect(){
        tvPrueba = findViewById(R.id.tvPrueba);
        tvResult = findViewById(R.id.tvResult);
    }
}
