package com.hoang.lvhco.lab3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Contact extends AppCompatActivity {
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvResult = findViewById(R.id.tvResult);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (ContextCompat.checkSelfPermission(Contact.this,Manifest.permission.READ_CONTACTS)
                            != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(Contact.this,
                                new  String[]{Manifest.permission.READ_CONTACTS},999);
                    } else {
                        LoadContact();
                    }
    //                loadContactṣ̣();
                }
            });
    }
    public void LoadContact(){
        Uri uri = Uri.parse("");
        Cursor c1 = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

        if (c1!=null && c1.getCount()>0){
            c1.moveToFirst();
            String data = "";
            while (c1.isAfterLast()==false){
                String id = c1.getString(c1.getColumnIndex(ContactsContract.Contacts._ID));
                String name = c1.getString(c1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                data = data + "\n" + id + "-" +name;
                c1.moveToNext();
            }
            c1.close();
            tvResult.setText(data);
        }
    }

}
