package com.verde.upqroo.verdesuperior.view.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.verde.upqroo.verdesuperior.R;

public class InformacionNoticias extends AppCompatActivity {
    ImageView Banner;
    TextView Titulo;
    TextView Descripcion;
    public static final String NOMBRE = "31";
    public static final String IMAGEN_URL = "32";
    public static final String DESCRIPCION = "33";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_noticias);
        Bundle data = getIntent().getExtras();
        Banner = findViewById(R.id.BannerImage);
        Titulo = findViewById(R.id.BannerTitle);
        Descripcion = findViewById(R.id.BannerDescriptionContent);

        Glide.with(this).load(data.getString(IMAGEN_URL)).into(Banner);
        Titulo.setText(data.getString(NOMBRE));
        Descripcion.setText(data.getString(DESCRIPCION));

    }
}
