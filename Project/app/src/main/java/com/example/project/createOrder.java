package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class createOrder extends AppCompatActivity {

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
        setContentView(R.layout.activity_create_order);

        repas = new LinkedList<>();



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

    }

    public void order(View view){
        Intent intent = getIntent();
        String namerepa =  intent.getStringExtra("Nom");
        String prixx= intent.getStringExtra("Prix");
        String trepa =  intent.getStringExtra("Type de repa");
        String ingre= intent.getStringExtra("Ingredients");
        String aller= intent.getStringExtra("Allergenes");
        String tcuisinee =  intent.getStringExtra("Type de cuisine");
        String desc= intent.getStringExtra("Description");
        String repa_id= intent.getStringExtra("id");
        String cuisiner= intent.getStringExtra("Cuisinier name");
        String cuisinier_id= intent.getStringExtra("Cuisinier id");

        DatabaseReference cuisinierReference=FirebaseDatabase.getInstance().getReference("Cuisinier/"+cuisinier_id+"/commandes/commandes");
        DatabaseReference clientReference = FirebaseDatabase.getInstance().getReference("Client/"+Account.getCurrentuserID()+"/commandes/commandes");
        DatabaseReference nameReference = FirebaseDatabase.getInstance().getReference("Client/"+Account.getCurrentuserID()+"/firstName");

        final String[] rdata = new String[1];
        nameReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                rdata[0] = (String) snapshot.getValue();
                Repa newrepa= new Repa(namerepa,trepa,tcuisinee,ingre,aller, prixx, desc, repa_id);
                Commande commande= new Commande(namerepa,desc,cuisiner,repa_id,prixx,newrepa,cuisinier_id,Account.currentuserID,rdata[0]);

                String key = clientReference.push().getKey();
                commande.setCommandeid(key);
                clientReference.child(key).setValue(commande);

                String key1 = cuisinierReference.push().getKey();
                cuisinierReference.child(key1).setValue(commande);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Toast toast = Toast.makeText(getApplicationContext(),"Order created", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent1 = new Intent(getApplicationContext(), orders.class);
        startActivity(intent1);
    }
    public void retour(View view){
        Intent intent = new Intent(getApplicationContext(), Bienvenue_client.class);
        startActivity(intent);
    }

}