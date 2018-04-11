package com.verde.upqroo.verdesuperior.view.view;

import android.app.Application;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by pprm2 on 10/04/2018.
 */

public class VerdeSuperiorApplication extends Application {

    public static DatabaseReference FirebaseReference;



    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseReference = database.getReference();


    }
}
