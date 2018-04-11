package com.verde.upqroo.verdesuperior.view.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.verde.upqroo.verdesuperior.R;

public class InformacionPlanta extends AppCompatActivity {

    public static final String NOMBRE = "21";
    public static final String NOMBRE_CIENTIFICO = "22";
    public static final String IMAGEN_URL = "23";
    public static final String DESCRIPCION = "24";
    public static final String TAXONOMIA = "25";
    public static final String APLICACIONES = "26";


    ImageView bannerBackgroundiv;
    TextView bannerTitletv;
    TextView bannerSubtitletv;
    TextView descripciontv;
    TextView aplicacionestv;
    TextView taxonnomiatv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacionplanta);
        Bundle data = getIntent().getExtras();
        String nombre = data.getString(NOMBRE);
        String cientifico = data.getString(NOMBRE_CIENTIFICO);
        String descripcion = data.getString(DESCRIPCION);
        String taxonomia = data.getString(TAXONOMIA);
        String aplicaciones = data.getString(APLICACIONES);
        String url = data.getString(IMAGEN_URL);
        bannerBackgroundiv = findViewById(R.id.BannerImage);
        bannerTitletv = findViewById(R.id.BannerTitle);
        bannerSubtitletv = findViewById(R.id.BannerCTitle);
        descripciontv = findViewById(R.id.BannerDescriptionContent);
        aplicacionestv = findViewById(R.id.BannerAplicacionContent);
        taxonnomiatv = findViewById(R.id.BannerTaxonomiaContent);


        Glide.with(this).load(url).into(bannerBackgroundiv);
        bannerTitletv.setText(nombre);
        bannerSubtitletv.setText(cientifico);
        descripciontv.setText(descripcion);
        aplicacionestv.setText(aplicaciones);
        taxonnomiatv.setText(taxonomia);
    }
}
