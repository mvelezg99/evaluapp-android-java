package com.example.asus.evaluapp;

import java.util.ArrayList;

/**
 * Created by ASUS on 17/03/2018.
 */

public class Prueba {
    private String nombrePrueba;
    private ArrayList<Preguntas> preguntas = new ArrayList<>();

    public Prueba(String nombrePrueba,ArrayList<Preguntas> preguntas){
        this.setNombrePrueba(nombrePrueba);
        this.preguntas = preguntas;
    }

    public ArrayList<Preguntas> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Preguntas> preguntas) {
        this.preguntas = preguntas;
    }

    public String getNombrePrueba() {
        return nombrePrueba;
    }

    public void setNombrePrueba(String nombrePrueba) {
        this.nombrePrueba = nombrePrueba;
    }
}
