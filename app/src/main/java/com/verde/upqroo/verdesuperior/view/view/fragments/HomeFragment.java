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
import com.google.firebase.database.ValueEventListener;
import com.verde.upqroo.verdesuperior.R;
import com.verde.upqroo.verdesuperior.view.view.VerdeSuperiorApplication;
import com.verde.upqroo.verdesuperior.view.view.mRecyclerViewAdapter;
import com.verde.upqroo.verdesuperior.view.view.model.Planta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<Planta> Plantas;
    RecyclerView rv;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PlantasInit(getContext());
        View layout = inflater.inflate(R.layout.fragment_home, container, false);
        rv = layout.findViewById(R.id.homerv);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false));
        return layout;
    }

    public void PlantasInit(final Context context){
        Plantas = new ArrayList<>();
        DatabaseReference FBplantass = VerdeSuperiorApplication.FirebaseReference.child("informacion_plantas");
        FBplantass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,HashMap> plantasReciver;
                plantasReciver = (HashMap) dataSnapshot.getValue();
                for (HashMap<String,String> planta: plantasReciver.values()) {
                    Plantas.add(new Planta(
                            planta.get("nombre"),
                            planta.get("cientifico"),
                            planta.get("descripcion"),
                            planta.get("taxonomia"),
                            planta.get("aplicaciones"),
                            planta.get("url")
                    ));
                }
                mRecyclerViewAdapter recyclerviewadapter = new mRecyclerViewAdapter(Plantas,context);
                rv.setAdapter(recyclerviewadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
