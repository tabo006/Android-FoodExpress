package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class Main_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }
    public void connecterClient(View view){
        Intent intent = new Intent(getApplicationContext(), client_sign_in.class);
        startActivity(intent);
    }
    public void connecterCuisinier(View view){
        Intent intent = new Intent(getApplicationContext(), cuisinier_sign_in.class);
        startActivity(intent);
    }
    public void connecterAdministrateur(View view){
        Intent intent = new Intent(getApplicationContext(), administrateur_sign_in.class);
        startActivity(intent);
    }
    public void inscrireClient(View view){
        Intent intent = new Intent(getApplicationContext(), inscrire_client.class);
        startActivity(intent);
    }
    public void inscrireCuisinier(View view){
        Intent intent = new Intent(getApplicationContext(), inscrire_cuisinier.class);
        startActivity(intent);
    }
}