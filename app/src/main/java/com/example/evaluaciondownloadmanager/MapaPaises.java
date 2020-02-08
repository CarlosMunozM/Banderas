package com.example.evaluaciondownloadmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapaPaises extends AppCompatActivity implements OnMapReadyCallback {

    String norte, sur, este, oeste, imagen;
    GoogleMap mapa;
    ImageView imgBandera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_paises);

        Bundle datos = this.getIntent().getExtras();
        norte = datos.getString("norte");
        sur = datos.getString("sur");
        este = datos.getString("este");
        oeste = datos.getString("oeste");
        imagen = datos.getString("imagen");

        imgBandera = findViewById(R.id.imgBandera);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);

        Glide.with(MapaPaises.this)
                .load(imagen)
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(imgBandera);

        getSupportActionBar().setTitle(datos.getString("nombre"));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;


        PolylineOptions lineas = new PolylineOptions()
                .add(new LatLng(Double.parseDouble(norte), Double.parseDouble(oeste)))
                .add(new LatLng(Double.parseDouble(norte), Double.parseDouble(este)))
                .add(new LatLng(Double.parseDouble(sur), Double.parseDouble(este)))
                .add(new LatLng(Double.parseDouble(sur), Double.parseDouble(oeste)))
                .add(new LatLng(Double.parseDouble(norte), Double.parseDouble(oeste)));
        lineas.width(8); lineas.color(Color.BLUE);
        mapa.addPolyline(lineas);

        CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(norte), Double.parseDouble(oeste)), 4);
        mapa.moveCamera(camUpd1);


    }
}
