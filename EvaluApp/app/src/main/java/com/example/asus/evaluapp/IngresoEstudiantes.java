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

public class IngresoEstudiantes extends AppCompatActivity {

    Button btnIngr, btnRegistro;
    EditText txtIng, txtNom, txtDNI, txtClave;
    TextView tvRegistro;

    static public String DNI;

    Carchivos objFilePruebas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_estudiantes);
        connect();

        objFilePruebas = new Carchivos(this, "Estudiantes.txt");


        btnIngr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtIng.getText().toString().isEmpty()) {
                    Boolean encontroDni = false;

                    String dniIng = txtIng.getText().toString();

                    String leerInicio;
                    leerInicio = objFilePruebas.leer();
                    String[] arrLeer = leerInicio.split("-#-");

                    for (String t : arrLeer) {
                        String[] arr = t.split("#&");
                        if (arr[0].equals(dniIng)) {
                            DNI = dniIng;
                            encontroDni = true;
                            break;
                        }

                    }
                    if (encontroDni == true) {
                        Intent i = new Intent(getApplicationContext(), EscogerPrueba.class);
                        startActivity(i);

                    } else {
                        Toast.makeText(getApplicationContext(),"No hay ningún estudiante con éste DNI, puede registrarse",Toast.LENGTH_LONG).show();
                        tvRegistro.setVisibility(View.VISIBLE);
                        txtNom.setVisibility(View.VISIBLE);
                        txtDNI.setVisibility(View.VISIBLE);
                        txtClave.setVisibility(View.VISIBLE);
                        btnRegistro.setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Debes ingresar algún DNI",Toast.LENGTH_LONG).show();
                }
            }

        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNom.getText().toString().isEmpty() && !txtDNI.getText().toString().isEmpty() && !txtClave.getText().toString().isEmpty()){
                    String DNIR = txtDNI.getText().toString();
                    String nombre = txtNom.getText().toString();
                    String clave = txtClave.getText().toString();
                    String strFinal = DNIR+"#&"+nombre+"#&"+clave+"#&"+"-#-";

                    String text = "";

                    try {
                        text = "";

                        objFilePruebas.escribir(strFinal);
                        text = objFilePruebas.leer();

                    } catch (IOException ex) {
                        Log.e("", ex.getMessage());
                    }

                    Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_LONG).show();
                    txtDNI.setText("");
                    txtClave.setText("");
                    txtNom.setText("");
                    tvRegistro.setVisibility(View.GONE);
                    txtNom.setVisibility(View.GONE);
                    txtDNI.setVisibility(View.GONE);
                    txtClave.setVisibility(View.GONE);
                    btnRegistro.setVisibility(View.GONE);

                }

            }
        });

    }

    private void connect() {
        btnIngr = findViewById(R.id.btnIngr);
        txtIng = findViewById(R.id.txtIng);
        txtClave = findViewById(R.id.txtClave);
        txtDNI = findViewById(R.id.txtDNI);
        txtNom = findViewById(R.id.txtNom);
        btnRegistro = findViewById(R.id.btnRegistro);
        tvRegistro = findViewById(R.id.tvRegistro);
    }
}
