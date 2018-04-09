package com.verde.upqroo.verdesuperior.view.view.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.verde.upqroo.verdesuperior.R;
import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    String subject, message, place, attachment;
    public String[] to = {"info@upqroo.edu.mx"};
    TextInputEditText messageReport, placeReport;
    public static String networkErrorMessage = "Sin Conexión a Internet";
    public static boolean checkInternetConnection = true;
    public static boolean showErrorMessage = true;

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
                try {
                    subject = "REPORTE ANOMALIA HUERTO";
                    message = messageReport.getText().toString()  + placeReport.getText().toString();
                    to[0] = "info@upqroo.edu.mx";
                    browseDocuments();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    Uri uri = data.getData();
                    sendMail(this.getContext(), to[0], subject, message, null, uri);
                    messageReport.setText("");
                    placeReport.setText("");
                }
                break;
        }
    }

    private void browseDocuments() {

        String[] mimeTypes = {"image/jpeg", "image/png", "image/gif"};

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (String mimeType : mimeTypes) {
                mimeTypesStr += mimeType + "|";
            }
            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
        }
        startActivityForResult(Intent.createChooser(intent, "ChooseFile"), 100);
    }

    public void sendMail(Context context, String to, String subject, String message, File attachment, Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        // Need to grant this permission
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // Attachment
        intent.setType("vnd.android.cursor.dir/email");

        if (attachment != null)
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(attachment));
        else if (uri != null)
            intent.putExtra(Intent.EXTRA_STREAM, uri);

        if (!TextUtils.isEmpty(subject))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);

        intent.putExtra(Intent.EXTRA_EMAIL, "info@upqroo.edu.mx");

        if (isNetworkAvailable(context)) {
            if (isAppAvailable(context, "com.google.android.gm"))
                intent.setPackage("com.google.android.gm");
            startActivityForResult(intent, 101);
        }
    }

    public static Boolean isAppAvailable(Context context, String appName) {
        PackageManager pm = context.getPackageManager();
        boolean isInstalled;
        try {
            pm.getPackageInfo(appName,PackageManager.GET_ACTIVITIES);
            isInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isInstalled = false;
        }
        return isInstalled;
    }


    public static boolean isNetworkAvailable(Context context) {

        if (checkInternetConnection) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting())
                return true;
            else {
                if (showErrorMessage)
                    Toast.makeText(context, networkErrorMessage, Toast.LENGTH_SHORT).show();

                return false;
            }
        } else
            return true;
    }
}
