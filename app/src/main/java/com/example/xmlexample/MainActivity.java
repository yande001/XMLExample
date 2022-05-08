package com.example.xmlexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onReadButton(View v){
        Intent intent = new Intent(this, ReadXMLActivity.class);
        startActivity(intent);
    }

    public void onWriteButton(View v){
        Intent intent = new Intent(this, WriteXMLActivity.class);
        startActivity(intent);
    }
}