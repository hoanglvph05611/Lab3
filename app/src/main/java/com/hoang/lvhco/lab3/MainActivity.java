package com.hoang.lvhco.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnContact;
    private Button btnCallLog;
    private Button btnMedia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnContact = (Button) findViewById(R.id.btnContact);
        btnCallLog = (Button) findViewById(R.id.btnCallLog);
        btnMedia = (Button) findViewById(R.id.btnMedia);

    }

    public void Contact(View view) {
        Intent intent = new Intent(MainActivity.this,CallLog.class);
        startActivity(intent);
    }

    public void CallLog(View view) {
        Intent intent = new Intent(MainActivity.this,Contact.class);
        startActivity(intent);
    }

    public void Media(View view) {
        Intent intent = new Intent(MainActivity.this,MediaStore.class);
        startActivity(intent);
    }
}
