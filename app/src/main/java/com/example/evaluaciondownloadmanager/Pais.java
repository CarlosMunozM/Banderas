package com.example.evaluaciondownloadmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pais {

    String nombre, imagen, west, east, north, south;

    public Pais() {
    }


    public Pais(String nombre, String iso2, String west, String east, String north, String south) {
        this.nombre = nombre;
        this.imagen =  "http://www.geognos.com/api/en/countries/flag/"  + iso2 + ".png";
        this.west = west;
        this.east = east;
        this.north = north;
        this.south = south;
    }

    public Pais(String name, String iso2) {
        nombre = name;
        imagen =  "http://www.geognos.com/api/en/countries/flag/"  + iso2 + ".png";
    }

    public Pais(String name) {
        nombre = name;
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

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }
}
