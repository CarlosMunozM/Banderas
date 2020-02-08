package com.example.evaluaciondownloadmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("http://www.geognos.com/api/en/countries/info/all.json", datos, this, this);
        ws.execute("");

        ListView lstOpciones = (ListView) findViewById(R.id.lstLista);
        lstOpciones.setOnItemClickListener(this);
    }

    Pais paisMOD;
    Pais[] pais;

    @Override
    public void processFinish(String result) throws JSONException {


        ArrayList<Pais> listaArticulo;
        JSONObject articulos=  new JSONObject(result);

        ArrayList<Pais> paises = new ArrayList<>();

        JSONObject results=articulos.getJSONObject("Results");
        JSONArray namesBD = results.names();

        for (int i = 0; i < namesBD.length(); i++) {

            String a= namesBD.getString(i);
            JSONObject d=results.getJSONObject(a);
            String nombrePais=d.getString("Name");

            JSONObject countryCodes= d.getJSONObject("CountryCodes");
            String iso2=countryCodes.getString("iso2");


            JSONObject geoRectangle = d.getJSONObject("GeoRectangle");
            String west = geoRectangle.getString("West");
            String east = geoRectangle.getString("East");
            String north = geoRectangle.getString("North");
            String south = geoRectangle.getString("South");



            paises.add(new Pais(nombrePais,iso2, west, east, north, south));
            //paises.add(new Pais(nombrePais,iso2));
        }



        AdaptadorPais adaptadorArticulos = new AdaptadorPais(this, paises);
        ListView listaOpciones =(ListView)findViewById(R.id.lstLista);
        listaOpciones.setAdapter(adaptadorArticulos);

        getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        getPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

    }

    public void getPermission(String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED))
                ActivityCompat.requestPermissions(this, new String[]{permission}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            Toast.makeText(this.getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //DownloadManager.Request request = new DownloadManager.Request(
        //Uri.parse(((Pais) adapterView.getItemAtPosition(position)).getImagen()));

        paisMOD = (Pais) (adapterView.getItemAtPosition(position));


        Intent intent = new Intent(this, MapaPaises.class);
        intent.putExtra("oeste", paisMOD.getWest());
        intent.putExtra("este", paisMOD.getEast());
        intent.putExtra("norte", paisMOD.getNorth());
        intent.putExtra("sur", paisMOD.getSouth());
        intent.putExtra("imagen", paisMOD.getImagen());
        intent.putExtra("nombre", paisMOD.getNombre());
        startActivity(intent);

        /*
        Bundle bundle = new Bundle();

        bundle.putString("oeste", paisMOD.getWest());
        bundle.putString("este", paisMOD.getEast());
        bundle.putString("norte", paisMOD.getNorth());
        bundle.putString("sur", paisMOD.getSouth());
        bundle.putString("imagen", paisMOD.getImagen());

        Intent intent = new Intent(this, MapaPaises.class);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);

        startActivity(intent);
        //startActivity(new Intent(this, ));
        */
    }
}
