package com.example.asus.evaluapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EscogerPrueba extends AppCompatActivity {

    ListView lvPruebas;

    Carchivos objFilePruebas;

    static String pruebaEscogida;

    ArrayList<String> pruebas = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_prueba);
        connect();

        objFilePruebas = new Carchivos(this, "Pruebas.txt");

        String leerInicio;
        leerInicio = objFilePruebas.leer();
        String[] arrLeer = leerInicio.split("-#-");

        for (String t : arrLeer) {
            String[] arr = t.split("#&");

            pruebas.add(arr[0]);

        }

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_selectable_list_item, pruebas);
        lvPruebas.setAdapter(adapter);

        lvPruebas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pruebaEscogida = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(getApplicationContext(),SolucionPrueba.class);
                startActivity(intent);
            }
        });
    }

    private void connect() {
        lvPruebas = findViewById(R.id.lvPruebas);
    }
}
