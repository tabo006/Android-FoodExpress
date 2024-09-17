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

public class Repas extends AppCompatActivity {
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
        setContentView(R.layout.activity_repas);

        repas = new LinkedList<>();
        String id= Account.getCurrentuserID();



        nom = (TextView)findViewById(R.id.nom);
        trepas = (TextView)findViewById(R.id.trepas);
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
        Intent intent = new Intent(getApplicationContext(), Menu_cuisinier.class);
        startActivity(intent);
    }
    public void ajoutOffert(View view){

        RepaReference.child(repaId).child("offered").setValue("true");
        Toast toast = Toast.makeText(getApplicationContext(),"Le repa a ete ajouter a la liste de repas offert", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void effacerRepa(View view){

        if (offered_val.equals("false")) {
            RepaReference.child(repaId).removeValue();
            finish();
            Toast toast = Toast.makeText(getApplicationContext(), "Le repa a ete effacer du menu", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Le repa est offert donc ne peut pas etre effacer", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}