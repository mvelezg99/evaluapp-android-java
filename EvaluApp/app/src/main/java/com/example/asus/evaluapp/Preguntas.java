package com.example.asus.evaluapp;

/**
 * Created by ASUS on 17/03/2018.
 */

public class Preguntas {
    private String tipoPregunta;
    private String pregunta;
    private String respuesta;
    private double puntaje;

    public Preguntas(String pregunta,String respuesta,String tipoPregunta,double puntaje){
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.tipoPregunta = tipoPregunta;
        this.setPuntaje(puntaje);
    }


    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }
}
