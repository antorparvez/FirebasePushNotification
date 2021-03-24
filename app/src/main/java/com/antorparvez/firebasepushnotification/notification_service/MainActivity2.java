package com.antorparvez.firebasepushnotification.notification_service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.antorparvez.firebasepushnotification.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toast.makeText(this, "notifi", Toast.LENGTH_SHORT).show();
    }
}