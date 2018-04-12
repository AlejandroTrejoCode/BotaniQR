package com.verde.upqroo.verdesuperior.view.view.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.verde.upqroo.verdesuperior.R;
import com.verde.upqroo.verdesuperior.view.view.InformacionPlanta;
import com.verde.upqroo.verdesuperior.view.view.VerdeSuperiorApplication;
import com.verde.upqroo.verdesuperior.view.view.mRecyclerViewAdapter;
import com.verde.upqroo.verdesuperior.view.view.model.Noticia;
import com.verde.upqroo.verdesuperior.view.view.model.Planta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<Object> lista;
    ArrayList<Integer> tipos;
    RecyclerView rv;
    mRecyclerViewAdapter mra;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_home, container, false);
        rv = layout.findViewById(R.id.homerv);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false));
        NoticiasInit();

        return layout;
    }


    int contador=0;
    public void NoticiasInit( ){
        tipos = new ArrayList<>();
        lista = new ArrayList<>();
        Query FBnoticias = VerdeSuperiorApplication.FirebaseReference.child("noticias").child("Reciente").orderByKey();
        FBnoticias.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot noticia: dataSnapshot.getChildren()) {
                    String key = noticia.getKey();
                    VerdeSuperiorApplication.FirebaseReference.child("informacion_noticias")
                            .child(key)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    HashMap<String, String> noticia = (HashMap<String, String>) dataSnapshot.getValue();
                                    tipos.add(mRecyclerViewAdapter.NOTICIAS);
                                    Noticia n = new Noticia(noticia.get("nombre"), noticia.get("descripcion"), noticia.get("url"));
                                    lista.add(n);
                                        mra = new mRecyclerViewAdapter(lista, tipos, getContext(), mRecyclerViewAdapter.INICIO);
                                        rv.setAdapter(mra);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                }
                initPlantas();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void initPlantas(){
        VerdeSuperiorApplication.FirebaseReference.child("plantas").child("count")
        .addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = dataSnapshot.getValue(Integer.class);
                String key1;
                getPlanta(getkey(count),count);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String getkey(int count){
        Random r = new Random();
        return "UPQROO_P_"+(r.nextInt(count) + 1);
    }

    public void getPlanta(String key, final int count){
        VerdeSuperiorApplication.FirebaseReference.child("informacion_plantas").child(key)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipos.add(mRecyclerViewAdapter.PLANTAS);
                        HashMap<String, String> planta = (HashMap<String, String>) dataSnapshot.getValue();
                        Planta p = new Planta(planta.get("nombre"),planta.get("cientifico"),
                                planta.get("descripcion"),planta.get("taxonomia"),
                                planta.get("aplicaciones"),planta.get("url"));
                        lista.add(p);
                        getPlanta(getkey(count));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
    public void getPlanta(String key){
        VerdeSuperiorApplication.FirebaseReference.child("informacion_plantas").child(key)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipos.add(mRecyclerViewAdapter.PLANTAS);
                        HashMap<String, String> planta = (HashMap<String, String>) dataSnapshot.getValue();
                        Planta p = new Planta(planta.get("nombre"),planta.get("cientifico"),
                                planta.get("descripcion"),planta.get("taxonomia"),
                                planta.get("aplicaciones"),planta.get("url"));
                        lista.add(p);
                            mra = new mRecyclerViewAdapter(lista,tipos,getContext(),mRecyclerViewAdapter.INICIO);
                            rv.setAdapter(mra);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
