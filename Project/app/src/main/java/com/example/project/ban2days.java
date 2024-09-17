package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ban2days extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban2days);
    }
    public void retour(View view){
        Intent intent = new Intent(getApplicationContext(), Main_page.class);
        startActivity(intent);
    }
}