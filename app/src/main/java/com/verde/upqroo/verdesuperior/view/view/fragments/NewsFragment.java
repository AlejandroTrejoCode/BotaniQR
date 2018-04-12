package com.verde.upqroo.verdesuperior.view.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.verde.upqroo.verdesuperior.R;
import com.verde.upqroo.verdesuperior.view.view.VerdeSuperiorApplication;
import com.verde.upqroo.verdesuperior.view.view.mRecyclerViewAdapter;
import com.verde.upqroo.verdesuperior.view.view.model.Noticia;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    ArrayList<Noticia> Noticias;
    RecyclerView rv;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_news, container, false);
        PlantasInit(getContext());
        rv = layout.findViewById(R.id.homerv);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false));
        return layout;
    }

    public void PlantasInit(final Context context){
        Noticias = new ArrayList<>();
        Query FBnoticias = VerdeSuperiorApplication.FirebaseReference.child("informacion_noticias").orderByKey();
        FBnoticias.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,HashMap> noticiasReciver;
                noticiasReciver = (HashMap) dataSnapshot.getValue();
                for (HashMap<String,String> noticia: noticiasReciver.values()) {
                    Noticias.add(new Noticia(
                            noticia.get("nombre"),
                            noticia.get("descripcion"),
                            noticia.get("url")
                    ));
                }
                mRecyclerViewAdapter recyclerviewadapter = new mRecyclerViewAdapter(Noticias,context,mRecyclerViewAdapter.NOTICIAS);
                rv.setAdapter(recyclerviewadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
