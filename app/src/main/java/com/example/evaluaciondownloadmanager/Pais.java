package com.example.evaluaciondownloadmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pais {

    String nombre, imagen;

    public Pais(String name) {
        nombre = name;
    }

    public Pais(String name, String iso2) {
        nombre = name;
        imagen =  "http://www.geognos.com/api/en/countries/flag/"  + iso2 + ".png";
    }

    public Pais(JSONObject a) throws JSONException {
        nombre = a.getString("Name").toString();
        imagen =  "http://www.geognos.com/api/en/countries/flag/"  + a.getString("iso2") + ".png";
    }

    public static ArrayList<Pais> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Pais> noticias = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            noticias.add(new Pais(datos.getJSONObject(i)));
        }
        return noticias;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


}
