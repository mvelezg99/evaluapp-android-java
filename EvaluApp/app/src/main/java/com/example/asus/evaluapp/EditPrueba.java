package com.example.asus.evaluapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class EditPrueba extends AppCompatActivity {

    TextView tvPrueba, tvPreg;
    ListView lvPreg;
    Button btnElim, btnEdit;
    EditText txtEdit;

    private String nombrePrueba;
    String pregSelec = "";
    Carchivos objFilePruebas;

    ArrayList<String> preguntas = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prueba);
        connect();
        lvPreg.setVisibility(View.VISIBLE);
        tvPreg.setVisibility(View.GONE);
        btnElim.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        IngresoDocente ingresoDocente = new IngresoDocente();
        nombrePrueba = ingresoDocente.nombrePrueba;
        tvPrueba.setText(nombrePrueba);
        objFilePruebas = new Carchivos(this, "Pruebas.txt");

        actualizarLista();

        lvPreg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lvPreg.setVisibility(View.GONE);
                tvPreg.setVisibility(View.VISIBLE);
                tvPreg.setText(adapterView.getItemAtPosition(i).toString());
                pregSelec = tvPreg.getText().toString();
                btnElim.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.VISIBLE);
            }
        });

        btnElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String leerInicio;
                StringBuilder strFinal = new StringBuilder();
                leerInicio = objFilePruebas.leer();
                String[] arrLeer = leerInicio.split("-#-");
                for (String t : arrLeer) {
                    String[] arr = t.split("#&");
                    int nPreg = 3;
                    strFinal.append(arr[0] + "#&" + (nPreg - 1) + "#&");
                    for (int i = 4; i < arr.length; i = i + 4) {
                        if (!arr[i].equals(pregSelec)) {
                            strFinal.append(arr[i - 2] + "#&" + arr[i - 1] + "#&" + arr[i] + "#&" + arr[i + 1] + "#&");
                        }
                    }
                    strFinal.append("-#-");

                }
                Toast.makeText(getApplicationContext(), "La pregunta fué eliminada con éxito", Toast.LENGTH_LONG).show();

                try {
                    objFilePruebas.sobreEscribir(strFinal.toString().substring(0, strFinal.toString().length() - 7));

                } catch (IOException ex) {
                    Log.e("", ex.getMessage());
                }

                actualizarLista();
                lvPreg.setVisibility(View.VISIBLE);
                tvPreg.setVisibility(View.GONE);
                btnElim.setVisibility(View.GONE);

            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String leerInicio;
                StringBuilder strFinal = new StringBuilder();
                String pregNueva = txtEdit.getText().toString();
                leerInicio = objFilePruebas.leer();
                String[] arrLeer = leerInicio.split("-#-");
                for (String t : arrLeer) {
                    String[] arr = t.split("#&");
                    int nPreg = 3;
                    strFinal.append(arr[0] + "#&" + (nPreg - 1) + "#&");
                    for (int i = 4; i < arr.length; i = i + 4) {
                        if (!arr[i].equals(pregSelec)) {
                            strFinal.append(arr[i - 2] + "#&" + arr[i - 1] + "#&" + arr[i] + "#&" + arr[i + 1] + "#&");
                        } else {
                            strFinal.append(arr[i - 2] + "#&" + arr[i - 1] + "#&" + pregNueva + "#&" + arr[i + 1] + "#&");
                        }
                    }
                    strFinal.append("-#-");

                }
                Toast.makeText(getApplicationContext(), "La pregunta fué editada con éxito", Toast.LENGTH_LONG).show();
                try {
                    objFilePruebas.sobreEscribir(strFinal.toString().substring(0, strFinal.toString().length() - 7));

                } catch (IOException ex) {
                    Log.e("", ex.getMessage());
                }


                actualizarLista();
                lvPreg.setVisibility(View.VISIBLE);
                tvPreg.setVisibility(View.GONE);
                btnElim.setVisibility(View.GONE);
                btnEdit.setVisibility(View.GONE);


            }
        });


    }


    private void connect() {
        tvPrueba = findViewById(R.id.tvPrueba);
        lvPreg = findViewById(R.id.lvPreg);
        tvPreg = findViewById(R.id.tvPregF);
        btnElim = findViewById(R.id.btnElim);
        btnEdit = findViewById(R.id.btnEdit);
        txtEdit = findViewById(R.id.txtEdit);
    }

    private void actualizarLista() {
        String leerInicio;
        leerInicio = objFilePruebas.leer();
        String[] arrLeer = leerInicio.split("-#-");

        for (String t : arrLeer) {
            String[] arr = t.split("#&");
            if (arr[0].equals(nombrePrueba)) {
                for (int i = 4; i < arr.length; i = i + 4) {
                    preguntas.add(arr[i]);
                }
                break;
            }
        }

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_selectable_list_item, preguntas);
        lvPreg.setAdapter(adapter);
    }
}
