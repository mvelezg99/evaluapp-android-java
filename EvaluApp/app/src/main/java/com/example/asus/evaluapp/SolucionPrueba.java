package com.example.asus.evaluapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.ArrayList;

public class SolucionPrueba extends AppCompatActivity {

    TextView tvPrueba, tvPregF, tvPregC, tvPregSM;
    Button btnRespF, btnRespC, btnRespSM;
    ScrollView scrollFoV, scrollSM, scrollCompl;
    ToggleButton btnFoV;
    EditText txtResp;
    RadioButton rbtnA, rbtnB, rbtnC, rbtnD;

    Carchivos objFilePruebas;
    Carchivos objFilePruebasEstudiantes;

    private String nombrePrueba;
    ArrayList<Preguntas> preguntas = new ArrayList<>();
    private double puntaje = 0;
    int preguntasResp = 0;
    double puntajeTot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solucion_prueba);
        connect();
        nombrePrueba = EscogerPrueba.pruebaEscogida;
        tvPrueba.setText("Solución de prueba:\n" + nombrePrueba);
        objFilePruebas = new Carchivos(this, "Pruebas.txt");
        objFilePruebasEstudiantes = new Carchivos(this,"PruebasEstudiantes.txt");

        final String DNIEst = IngresoEstudiantes.DNI;


        String leerInicio;
        leerInicio = objFilePruebas.leer();
        String[] arrLeer = leerInicio.split("-#-");

        for (String t : arrLeer) {
            String[] arr = t.split("#&");
            if (arr[0].equals(nombrePrueba)) {
                for (int i = 4; i < arr.length; i = i + 4) {
                    Preguntas pregunta = new Preguntas(arr[i], arr[i + 1], arr[i - 2], Double.parseDouble(arr[i - 1]));
                    preguntas.add(pregunta);
                    puntajeTot += pregunta.getPuntaje();
                }
                break;
            }
        }




        mostrarPregunta(0);

        btnRespF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String respUs = "";
                String respuestaF = preguntas.get(preguntasResp).getRespuesta();

                if (btnFoV.isChecked()) {
                    respUs = "Verdaderp";
                } else {
                    respUs = "Falso";
                }
                if (respUs.equals(respuestaF)) {
                    puntaje += preguntas.get(preguntasResp).getPuntaje();
                }
                preguntasResp++;

                Toast.makeText(getApplicationContext(), "Has respondido correctamente, llevas " + preguntasResp + " preguntas respondidas de " + preguntas.size(), Toast.LENGTH_LONG).show();
                if (preguntasResp < preguntas.size()) {
                    mostrarPregunta(preguntasResp);
                } else {
                    double calificacion = ((puntaje * 100) / puntajeTot);
                    Toast.makeText(getApplicationContext(), "Has terminado la prueba, tu calificación es de: " + calificacion + "%", Toast.LENGTH_LONG).show();
                    guardarPrueba(DNIEst,nombrePrueba,calificacion);
                    finish();
                }
            }
        });

        btnRespC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String respUs = "";
                String respuestaC = preguntas.get(preguntasResp).getRespuesta();


                respUs = txtResp.getText().toString();
                if (respUs.equals(respuestaC)) {
                    puntaje += preguntas.get(preguntasResp).getPuntaje();
                }

                preguntasResp++;

                Toast.makeText(getApplicationContext(), "Has respondido correctamente, llevas " + preguntasResp + " preguntas respondidas de " + preguntas.size(), Toast.LENGTH_LONG).show();
                if (preguntasResp < preguntas.size()) {
                    mostrarPregunta(preguntasResp);

                } else {
                    double calificacion = ((puntaje * 100) / puntajeTot);
                    Toast.makeText(getApplicationContext(), "Has terminado la prueba, tu calificación es de: " + calificacion + "%", Toast.LENGTH_LONG).show();
                    guardarPrueba(DNIEst,nombrePrueba,calificacion);
                    finish();
                }
            }
        });

        btnRespSM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String respUs = "";
                String respuestaSM = preguntas.get(preguntasResp).getRespuesta();


                if (rbtnA.isChecked()) {
                    respUs = rbtnA.getText().toString();
                } else if (rbtnB.isChecked()) {
                    respUs = rbtnB.getText().toString();
                } else if (rbtnC.isChecked()) {
                    respUs = rbtnC.getText().toString();
                } else if (rbtnD.isChecked()) {
                    respUs = rbtnD.getText().toString();
                }

                if (respUs.equals(respuestaSM)) {
                    puntaje += preguntas.get(preguntasResp).getPuntaje();
                }

                preguntasResp++;

                Toast.makeText(getApplicationContext(), "Has respondido correctamente, llevas " + preguntasResp + " preguntas respondidas de " + preguntas.size(), Toast.LENGTH_LONG).show();
                if (preguntasResp < preguntas.size()) {
                    mostrarPregunta(preguntasResp);
                } else {
                    double calificacion = ((puntaje * 100) / puntajeTot);
                    Toast.makeText(getApplicationContext(), "Has terminado la prueba, tu calificación es de: " + calificacion + "%", Toast.LENGTH_LONG).show();
                    guardarPrueba(DNIEst,nombrePrueba,calificacion);
                    finish();
                }


            }
        });


    }

    private void connect() {
        tvPrueba = findViewById(R.id.tvPrueba);
        scrollCompl = findViewById(R.id.scrollCompl);
        scrollFoV = findViewById(R.id.scrollFoV);
        scrollSM = findViewById(R.id.scrollSM);
        btnRespC = findViewById(R.id.btnRespC);
        btnRespF = findViewById(R.id.btnRespF);
        btnRespSM = findViewById(R.id.btnRespSM);
        tvPregF = findViewById(R.id.tvPregF);
        tvPregC = findViewById(R.id.tvPregC);
        tvPregSM = findViewById(R.id.tvPregSM);
        btnFoV = findViewById(R.id.btnFoV);
        txtResp = findViewById(R.id.txtIng);
        rbtnA = findViewById(R.id.rbtnA);
        rbtnB = findViewById(R.id.rbtnB);
        rbtnC = findViewById(R.id.rbtnC);
        rbtnD = findViewById(R.id.rbtnD);

    }

    private void mostrarPregunta(int index) {
        Preguntas p1 = preguntas.get(index);
        if (p1.getTipoPregunta().equals("Falso o Verdadero")) {
            scrollFoV.setVisibility(View.VISIBLE);
            scrollCompl.setVisibility(View.GONE);
            scrollSM.setVisibility(View.GONE);
            String preguntaF = preguntas.get(preguntasResp).getPregunta();
            tvPregF.setText(preguntaF);

        } else if (p1.getTipoPregunta().equals("Completar")) {
            scrollFoV.setVisibility(View.GONE);
            scrollCompl.setVisibility(View.VISIBLE);
            scrollSM.setVisibility(View.GONE);
            String preguntaC = preguntas.get(preguntasResp).getPregunta();
            tvPregC.setText(preguntaC);

        } else if (p1.getTipoPregunta().equals("Seleccion Multiple")) {
            scrollFoV.setVisibility(View.GONE);
            scrollCompl.setVisibility(View.GONE);
            scrollSM.setVisibility(View.VISIBLE);
            String preguntaSM = preguntas.get(preguntasResp).getPregunta();
            tvPregSM.setText(preguntaSM);
        }
    }

    private void guardarPrueba(String DNI, String nombrePrueba, double calificacion){

        String text = DNI+"#&"+nombrePrueba+"#&"+calificacion+"#&";
        try {
            objFilePruebasEstudiantes.escribir(text);

        } catch (IOException ex) {
            Log.e("", ex.getMessage());
        }
    }

}
