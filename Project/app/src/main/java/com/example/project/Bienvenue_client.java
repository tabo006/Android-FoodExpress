package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Bienvenue_client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue_client);
    }

    public void deconnecter(View view){
        Intent intent = new Intent(getApplicationContext(),Main_page.class);
        startActivity(intent);
    }
    public void search(View view){
        Intent intent = new Intent(getApplicationContext(),Recherche_client.class);
        startActivity(intent);
    }
    public void orders(View view) {
        Intent intent = new Intent(getApplicationContext(), orders.class);
        startActivity(intent);
    }
}