package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Bienvenue_cuisinier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue_cuisinier);
    }
    public void goMenu(View view){
        Intent intent = new Intent(getApplicationContext(), Menu_cuisinier.class);
        startActivity(intent);
    }
    public void goOffered(View view){
        Intent intent = new Intent(getApplicationContext(), menu_displayed.class);
        startActivity(intent);
    }
    public void deconnecter(View view){
        Intent intent = new Intent(getApplicationContext(),Main_page.class);
        startActivity(intent);
    }
    public void goOrders(View view){
        Intent intent = new Intent(getApplicationContext(), Commande_list_cuisinier.class);
        startActivity(intent);
    }
    public void goEvaluation(View view){
        Intent intent = new Intent(getApplicationContext(), evaluation_cuisinier.class);
        startActivity(intent);
    }
    public void goProfil(View view){
        Intent intent = new Intent(getApplicationContext(), cookinfo_cuisinier.class);
        startActivity(intent);
    }
}