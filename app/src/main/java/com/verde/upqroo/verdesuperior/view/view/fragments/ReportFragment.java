package com.verde.upqroo.verdesuperior.view.view.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.verde.upqroo.verdesuperior.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    String to, subject, message, place, attachment;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;
    TextInputEditText messageReport, placeReport;

    public ReportFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report, container, false);

        ImageButton adjuntarButton = (ImageButton) view.findViewById(R.id.adjuntarButton);
        Button sendReport = (Button) view.findViewById(R.id.sendReport);
        messageReport = (TextInputEditText) view.findViewById(R.id.messageReport);
        placeReport = (TextInputEditText) view.findViewById(R.id.placeReport);



        adjuntarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });

        sendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "COMPRUEBA EL MENSAJE", Toast.LENGTH_SHORT).show();
                sendEmail();
            }
        });

        return view;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            attachment = cursor.getString(columnIndex);
            Log.e("Attachment Path:", attachment);
            URI = Uri.parse("file://" + attachment);
            cursor.close();
        }
    }

    public void sendEmail()
    {
        try
        {
            to = "alejandrotrejocode@gmail.com";
            subject = "REPORTE DE ANOMALIA HUERTO";
            message = messageReport.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { to });
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,subject);
            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
            this.startActivity(Intent.createChooser(emailIntent,"Enviando Correo..."));
        }
        catch (Throwable t)
        {
            Toast.makeText(getContext(), "Error: Código : " + t.toString(),Toast.LENGTH_LONG).show();
        }
    }


    public void openFolder()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Selecciona tu galería"), PICK_FROM_GALLERY);
    }

}
