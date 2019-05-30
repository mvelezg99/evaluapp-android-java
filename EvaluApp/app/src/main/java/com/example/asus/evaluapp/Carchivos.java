package com.example.asus.evaluapp;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ASUS on 5/03/2018.
 */

public class Carchivos {

    private String nombreArchivo;
    Context ctx;
    FileOutputStream fos;
    FileInputStream fis;


    public Carchivos(Context ctx,String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.ctx = ctx;
    }

    public void escribir(String textoArchivo)throws IOException {
        try{
            fos = ctx.openFileOutput(nombreArchivo,Context.MODE_APPEND);
            fos.write((textoArchivo.getBytes()));

            fos.close();
        } catch (FileNotFoundException e){
            Log.e("", e.getMessage());
        } catch (IOException ex){
            Log.e("",ex.getMessage());
        }
    }

    public void sobreEscribir(String textoArchivo)throws IOException {
        try{
            fos = ctx.openFileOutput(nombreArchivo,Context.MODE_PRIVATE);
            fos.write((textoArchivo.getBytes()));

            fos.close();
        } catch (FileNotFoundException e){
            Log.e("", e.getMessage());
        } catch (IOException ex){
            Log.e("",ex.getMessage());
        }
    }

    public String leer(){


        String lectura="";
        try{
            fis = ctx.openFileInput(nombreArchivo);
            int i;
            char caracter = 'a';
            do{
                i = fis.read();
                if(i != '\n'){
                    caracter = (char)i;
                    lectura = lectura + caracter;
                } else {
                    lectura += "\n";
                }
            } while (i > 0);
            lectura += "\n";
        } catch (Exception e){
            Log.e("",e.getMessage());
        }

        return (lectura);
    }
}
