package com.example.asus.evaluapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class IngresoDocente extends AppCompatActivity {

    private Button btnComp,btnElim,btnCont,btnEdit, btnResult;
    private EditText txtPrueba;
    private TextView tvOpc;

    private Carchivos objFilePruebas;

    static String nombrePrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_docente);
        connect();
        hacerInvisible();

        objFilePruebas = new Carchivos(this,"Pruebas.txt");



        btnComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtPrueba.getText().toString().isEmpty()) {
                    String leerInicio;
                    leerInicio = objFilePruebas.leer();
                    String[] arrLeer = leerInicio.split("-#-");
                    tvOpc.setVisibility(View.VISIBLE);

                    for (String t : arrLeer) {
                        String[] arr = t.split("#&");
                        if (arr[0].equals(txtPrueba.getText().toString())) {
                            nombrePrueba = txtPrueba.getText().toString();
                            btnEdit.setVisibility(View.VISIBLE);
                            btnElim.setVisibility(View.VISIBLE);
                            btnResult.setVisibility(View.VISIBLE);
                            btnCont.setVisibility(View.INVISIBLE);
                            break;

                        } else {
                            nombrePrueba = txtPrueba.getText().toString();
                            btnEdit.setVisibility(View.INVISIBLE);
                            btnElim.setVisibility(View.INVISIBLE);
                            btnCont.setVisibility(View.VISIBLE);
                            btnResult.setVisibility(View.INVISIBLE);
                        }

                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Ingresa un nombre de prueba",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombrePrueba = txtPrueba.getText().toString();
                Intent in = new Intent(IngresoDocente.this,NuevaPrueba.class);
                startActivity(in);
                hacerInvisible();
            }
        });

        btnElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombrePrueba = txtPrueba.getText().toString();
                String leerInicio;
                StringBuilder strFinal = new StringBuilder();
                leerInicio = objFilePruebas.leer();
                String[] arrLeer = leerInicio.split("-#-");

                for (String t : arrLeer) {
                    String[] arr = t.split("#&");

                        if (!arr[0].equals(nombrePrueba)) {
                            strFinal.append(t+"-#-");
                        }
                }
                Toast.makeText(getApplicationContext(),"La prueba: "+nombrePrueba+" fué eliminada con éxito",Toast.LENGTH_LONG).show();
                try {
                    objFilePruebas.sobreEscribir(strFinal.toString().substring(0,strFinal.toString().length()-2));

                } catch (IOException ex) {
                    Log.e("", ex.getMessage());
                }
                hacerInvisible();
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombrePrueba = txtPrueba.getText().toString();
                Intent i = new Intent(getApplicationContext(),ResultadosPrueba.class);
                startActivity(i);
                hacerInvisible();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombrePrueba = txtPrueba.getText().toString();
                Intent i = new Intent(getApplicationContext(),EditPrueba.class);
                startActivity(i);
                hacerInvisible();
            }
        });


    }

    private void connect () {
        btnComp = findViewById(R.id.btnComp);
        btnCont = findViewById(R.id.btnCont);
        btnElim = findViewById(R.id.btnElim);
        btnEdit = findViewById(R.id.btnEdit);
        txtPrueba = findViewById(R.id.txtPrueba);
        tvOpc = findViewById(R.id.tvOpc);
        btnResult = findViewById(R.id.btnResult);
    }
    private void hacerInvisible () {
        tvOpc.setVisibility(View.INVISIBLE);
        btnCont.setVisibility(View.INVISIBLE);
        btnElim.setVisibility(View.INVISIBLE);
        btnEdit.setVisibility(View.INVISIBLE);
        btnResult.setVisibility(View.INVISIBLE);
        txtPrueba.setText("");

    }
}