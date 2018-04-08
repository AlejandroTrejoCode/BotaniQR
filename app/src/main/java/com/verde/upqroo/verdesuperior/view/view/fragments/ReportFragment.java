package com.verde.upqroo.verdesuperior.view.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.verde.upqroo.verdesuperior.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {


    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_report, container, false);

        ImageButton adjuntarButton = (ImageButton) view.findViewById(R.id.adjuntarButton);
        Button sendReport = (Button) view.findViewById(R.id.sendReport);

        adjuntarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Adjuntado", Toast.LENGTH_SHORT).show();
            }
        });

        sendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "COMPRUEBA EL MENSAJE", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

}
