package com.verde.upqroo.verdesuperior.view.view.fragments;


import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.verde.upqroo.verdesuperior.R;
import com.verde.upqroo.verdesuperior.view.view.InformacionPlanta;
import com.verde.upqroo.verdesuperior.view.view.VerdeSuperiorApplication;

import java.io.IOException;
import java.util.HashMap;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment implements ZXingScannerView.ResultHandler {


    public static final int CAMERA_PERMISSION = 11;
    private ZXingScannerView mScannerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity());
        pedirPermiso();
        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result result) {
        mScannerView.stopCamera();
        final DatabaseReference database = VerdeSuperiorApplication.FirebaseReference.child("informacion_plantas").child(result.getText());
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot != null) {
                    HashMap<String, String> planta = (HashMap<String, String>) dataSnapshot.getValue();
                    Intent i = new Intent(getContext(), InformacionPlanta.class);
                    i.putExtra(InformacionPlanta.NOMBRE, planta.get("nombre"));
                    i.putExtra(InformacionPlanta.NOMBRE_CIENTIFICO, planta.get("cientifico"));
                    i.putExtra(InformacionPlanta.IMAGEN_URL, planta.get("url"));
                    i.putExtra(InformacionPlanta.DESCRIPCION, planta.get("descripcion"));
                    i.putExtra(InformacionPlanta.TAXONOMIA, planta.get("taxonomia"));
                    i.putExtra(InformacionPlanta.APLICACIONES, planta.get("aplicaciones"));
                    startActivity(i);
                }
                else Toast.makeText(getContext(),"Codigo QR invalido", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mScannerView.resumeCameraPreview(this);
    }



    public void pedirPermiso(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation
            if (this.shouldShowRequestPermissionRationale(
                    Manifest.permission.CAMERA)) {


            } else {


                this.requestPermissions(
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERMISSION);
            }
        }
        else inicializarCAMARA();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    inicializarCAMARA();

                } else {

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    public void inicializarCAMARA(){
        mScannerView.startCamera();
    }
}
