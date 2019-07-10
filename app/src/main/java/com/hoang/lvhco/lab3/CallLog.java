package com.hoang.lvhco.lab3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CallLog extends AppCompatActivity {
    private static android.provider.CallLog.Calls Calls;
    TextView tvCallLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvCallLog = findViewById(R.id.tvCallLog);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(CallLog.this,Manifest.permission.READ_CALL_LOG)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CallLog.this,
                            new  String[]{Manifest.permission.READ_CALL_LOG},989);
                } else {
                    TheCallLog();
                }

            }
        });
    }
    public void TheCallLog() {
        String[] projection = new String[]{
                CallLog.Calls.DATE,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI, projection, CallLog.Calls.DURATION + "<?", new String[]{"30"}, CallLog.Calls.DATE + " Asc");
        c.moveToFirst();
        String s = "";
        while (c.isAfterLast() == false) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                s += c.getString(i) + " - ";
            }
            s += "\n";
            c.moveToNext();
        }
        c.close();
       tvCallLog.append(s);
    }
}
