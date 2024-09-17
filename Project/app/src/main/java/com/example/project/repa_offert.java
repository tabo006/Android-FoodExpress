package com.example.project;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

public class repa_offert extends AppCompatActivity {
    private DatabaseReference RepaReference;
    private List<Repa> repas;
    private DatabaseReference accountsReference;

    private TextView nom, trepas, tcuisine, ingredients, allergene, description, prix;
    private String repaId;
    private String offered_val;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repa_offert);

        repas = new LinkedList<>();
        String id= Account.getCurrentuserID();



        nom = (TextView)findViewById(R.id.nom);
        trepas = (TextView)findViewById(R.id.type_de_repa);
        tcuisine = (TextView)findViewById(R.id.tcuisine);
        ingredients = (TextView)findViewById(R.id.ingrerepas);
        allergene = (TextView)findViewById(R.id.allergene);
        description = (TextView)findViewById(R.id.rating_repa);
        prix = (TextView)findViewById(R.id.prix);


        Intent intent = getIntent();
        repaId= intent.getStringExtra("id");
        offered_val= intent.getStringExtra("Offered");
        String clientname =  intent.getStringExtra("Nom");
        String trepa =  intent.getStringExtra("Type de repa");
        String tcuisinee =  intent.getStringExtra("Type de cuisine");
        String prixx= intent.getStringExtra("Prix");
        String aller= intent.getStringExtra("Allergenes");
        String ingre= intent.getStringExtra("Ingredients");
        String desc= intent.getStringExtra("Description");
        nom.setText(clientname);
        trepas.setText(trepa);
        tcuisine.setText(tcuisinee);
        prix.setText(prixx);
        allergene.setText(aller);
        ingredients.setText(ingre);
        description.setText(desc);
        RepaReference= FirebaseDatabase.getInstance().getReference("Cuisinier/"+id+"/menu/repa_list");

    }

    public void retourAuMenu(View view){
        Intent intent = new Intent(getApplicationContext(), menu_displayed.class);
        startActivity(intent);
    }

    public void effacerRepa(View view){

        RepaReference.child(repaId).child("offered").setValue("false");
        Toast toast = Toast.makeText(getApplicationContext(),"Le repa a ete enlever de la liste des repa offert", Toast.LENGTH_SHORT);
        toast.show();
    }
}