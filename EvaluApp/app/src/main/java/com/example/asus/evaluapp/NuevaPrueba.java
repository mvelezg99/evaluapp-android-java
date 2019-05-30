package com.example.asus.evaluapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class NuevaPrueba extends AppCompatActivity {

    Button btnPreg,btnFin;
    TextView tvNomPr,tvPreg;
    EditText txtPreg,txtResp;
    Spinner spinTipo;
    RatingBar rbarPunt;
    private String nombrePrueba;
    int nPreg = 1;
    ArrayList<Preguntas> arrPreguntas = new ArrayList<>();
    String strPreg = "";
    Carchivos objFilePruebas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_prueba);
        connect();
        nombrePrueba = IngresoDocente.nombrePrueba;
        tvNomPr.setText(nombrePrueba);

        objFilePruebas = new Carchivos(this,"Pruebas.txt");

        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipoPregunta, android.R.layout.simple_spinner_dropdown_item);
        spinTipo.setAdapter(adapter1);

        btnPreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinTipo.getSelectedItemPosition() != 0 && !txtPreg.getText().toString().isEmpty() && !txtResp.getText().toString().isEmpty()) {
                    String tipoPregunta = spinTipo.getSelectedItem().toString();
                    String pregunta = txtPreg.getText().toString();
                    String respuesta = txtResp.getText().toString();
                    double puntaje = rbarPunt.getRating();
                    Preguntas preguntaGen = new Preguntas(pregunta, respuesta, tipoPregunta, puntaje);
                    arrPreguntas.add(preguntaGen);
                    strPreg += (tipoPregunta+"#&"+puntaje+"#&"+pregunta+"#&"+respuesta+"#&");
                    Toast.makeText(getApplicationContext(), "Pregunta agregada con éxito", Toast.LENGTH_SHORT).show();
                    nPreg++;
                    tvPreg.setText("Pregunta #"+nPreg);
                    spinTipo.setSelection(0);
                    txtPreg.setText("");
                    txtResp.setText("");
                    rbarPunt.setRating(1);
                } else {
                    Toast.makeText(getApplicationContext(),"No puede dejar campos vacíos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nPreg>1) {

                    String strFinal = nombrePrueba + "#&" + arrPreguntas.size() + "#&" + strPreg + "-#-";
                    try {
                        objFilePruebas.escribir(strFinal);

                    } catch (IOException ex) {
                        Log.e("", ex.getMessage());
                    }
                    Toast.makeText(getApplicationContext(), "La prueba fué agregada con éxito", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Debes agregar por lo menos 1 pregunta", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void connect(){
        btnPreg = findViewById(R.id.btnPreg);
        btnFin = findViewById(R.id.btnFin);
        tvNomPr = findViewById(R.id.tvNomPr);
        tvPreg = findViewById(R.id.tvPregF);
        txtPreg = findViewById(R.id.txtPreg);
        txtResp = findViewById(R.id.txtIng);
        spinTipo = findViewById(R.id.spinTipo);
        rbarPunt = findViewById(R.id.rbarPunt);
    }

}
