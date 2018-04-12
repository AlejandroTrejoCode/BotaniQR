package com.verde.upqroo.verdesuperior.view.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.verde.upqroo.verdesuperior.R;
import com.verde.upqroo.verdesuperior.view.view.model.Noticia;
import com.verde.upqroo.verdesuperior.view.view.model.Planta;

import java.util.ArrayList;

/**
 * Created by pprm2 on 10/04/2018.
 */

public class mRecyclerViewAdapter extends RecyclerView.Adapter<mRecyclerViewAdapter.mCardViewholder> {

    public static final int PLANTAS = 0;
    public static final int NOTICIAS = 1;
    public static final int INICIO = 2;
    int CurrentType;
    ArrayList<Planta> Plantas;
    ArrayList<Noticia> Noticias;
    ArrayList<Object> Inicio;
    ArrayList<Integer> Tipos;
    public Context context;

    public mRecyclerViewAdapter(ArrayList lista, Context context, int tipo) {
        switch (tipo){
            case PLANTAS:
                Plantas = (ArrayList<Planta>) lista;
                break;
            case NOTICIAS:
                Noticias = (ArrayList<Noticia>)lista;
                break;
        }
        CurrentType = tipo;
        this.context = context;
    }
    public mRecyclerViewAdapter(ArrayList<Object> lista, ArrayList<Integer> tipos,Context context, int tipo){
        this.Inicio = lista;
        this.Tipos = tipos;
        this.context = context;
        this.CurrentType = tipo;
    }

    @NonNull
    @Override
    public mCardViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_general,null,false);
        return new mCardViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mCardViewholder holder, int position) {

        switch (CurrentType){
            case PLANTAS:
                Planta p = Plantas.get(position);
                holder.asignarDatos(p);
                break;
            case NOTICIAS:
                Noticia n = Noticias.get(position);
                holder.asignarDatos(n);
                break;
            case INICIO:
                if(Tipos.get(position)==PLANTAS){
                    p = (Planta) Inicio.get(position);
                    holder.asignarDatos(p);
                }
                else{
                    n = (Noticia) Inicio.get(position);
                    holder.asignarDatos(n);
                }

                break;
        }
    }

    @Override
    public int getItemCount() {
        switch (CurrentType){
            case PLANTAS:
                return Plantas.size();
            case NOTICIAS:
                return Noticias.size();
            case INICIO:{
                return Inicio.size();
            }
        };
        return 0;
    }

    public void agregarDatosInicio(Noticia n){
        Inicio.add(n);
        Tipos.add(NOTICIAS);
        notifyItemInserted(getItemCount()-1);
        notifyItemRangeChanged(0,getItemCount()-1);
    }
    public void agregarDatosInicio(Planta p){
        Inicio.add(p);
        Tipos.add(PLANTAS);
        notifyItemInserted(getItemCount()-1);
        notifyItemRangeChanged(0,getItemCount()-1);
    }

    public class mCardViewholder extends RecyclerView.ViewHolder {
        private ImageView Background;
        private TextView Title;
        private TextView Subtitle;
        private TextView More;
        public mCardViewholder(View itemView) {
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
        public void asignarDatos(final  Noticia n){
            Title.setText(n.getNombre());

            Subtitle.setText(
                    n.getDescripcion().length() >= 20 ? n.getDescripcion().substring(0,20)+"..." : n.getDescripcion()
            );
            Glide.with(context).load(n.getUrl()).into(Background);
        }
    }
}
