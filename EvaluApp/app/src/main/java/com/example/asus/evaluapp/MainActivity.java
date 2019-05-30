package com.example.asus.evaluapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnEstudiante;
    private Button btnDocente;
    private EditText txtPassDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect();

        btnDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtPassDoc.getText().toString().equals("evadoc")) {
                    Intent i = new Intent(getApplicationContext(), IngresoDocente.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),"Contraseña Incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),IngresoEstudiantes.class);
                startActivity(i);
            }
        });
    }

    private void connect(){
        btnEstudiante = findViewById(R.id.btnEstudiantes);
        btnDocente = findViewById(R.id.btnDocentes);
        txtPassDoc = findViewById(R.id.txtPassDoc);
    }
}
