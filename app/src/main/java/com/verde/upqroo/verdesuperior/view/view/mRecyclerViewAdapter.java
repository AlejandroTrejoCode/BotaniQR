package com.verde.upqroo.verdesuperior.view.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.verde.upqroo.verdesuperior.R;
import com.verde.upqroo.verdesuperior.view.view.model.Planta;

import java.util.ArrayList;

/**
 * Created by pprm2 on 10/04/2018.
 */

public class mRecyclerViewAdapter extends RecyclerView.Adapter<mRecyclerViewAdapter.Viewholder> {

    ArrayList<Planta> Plantas;
    public Context context;

    public mRecyclerViewAdapter(ArrayList<Planta> plantas, Context context) {
        Plantas = plantas;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_general,null,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Planta p = Plantas.get(position);
        holder.asignarDatos(p);
    }

    @Override
    public int getItemCount() {
        return Plantas.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView Background;
        private TextView Title;
        private TextView Subtitle;
        private TextView More;
        public Viewholder(View itemView) {
            super(itemView);
            Background = itemView.findViewById(R.id.pictureCard);
            Title = itemView.findViewById(R.id.titleCardHome);
            Subtitle = itemView.findViewById(R.id.ctitleCardHome);
            More = itemView.findViewById(R.id.moreCardHome);
        }


        public void asignarDatos(final Planta p){
            Title.setText(p.getNombre());
            Subtitle.setText(p.getNombre_cientifico());
            Glide.with(context).load(p.getImagen_url()).into(Background);
            More.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context,InformacionPlanta.class);
                    i.putExtra(InformacionPlanta.NOMBRE,p.getNombre());
                    i.putExtra(InformacionPlanta.NOMBRE_CIENTIFICO,p.getNombre_cientifico());
                    i.putExtra(InformacionPlanta.IMAGEN_URL,p.getImagen_url());
                    i.putExtra(InformacionPlanta.DESCRIPCION,p.getDescripcion());
                    i.putExtra(InformacionPlanta.TAXONOMIA,p.getTaxonomia());
                    i.putExtra(InformacionPlanta.APLICACIONES,p.getAplicaciones());

                    context.startActivity(i);
                }
            });
        }
    }
}
