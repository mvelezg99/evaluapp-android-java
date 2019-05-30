package com.example.asus.evaluapp;

/**
 * Created by ASUS on 19/03/2018.
 */

public class Estudiante {
    private String DNI;
    private String nombre;
    private String clave;

    public Estudiante(String DNI, String nombre, String clave){
        this.setDNI(DNI);
        this.setNombre(nombre);
        this.setClave(clave);
    }


    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
