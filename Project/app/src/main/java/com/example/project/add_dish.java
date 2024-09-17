package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class add_dish extends AppCompatActivity {

    private DatabaseReference MenuReference;
    private EditText name, typecuisine,typerepa,prix,ingredients,description,allergene;
    private String nom, tCuisine, tRepas,price,ingredient,desc,allergi,id;
    private List<Repa> repas = new ArrayList<>();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        name = (EditText) findViewById(R.id.name);
        typecuisine = (EditText) findViewById(R.id.typeCuisine);
        typerepa= (EditText) findViewById(R.id.typeRepas);
        prix= (EditText) findViewById(R.id.prix);
        allergene= (EditText) findViewById(R.id.allergene);
        description= (EditText) findViewById(R.id.rating_repa);
        ingredients= (EditText) findViewById(R.id.ingredients);

        String id= Account.getCurrentuserID();

        MenuReference = FirebaseDatabase.getInstance().getReference("Cuisinier/"+id+"/menu/repa_list");

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                nom = editable.toString();

            }
        });
        typecuisine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tCuisine = editable.toString();

            }
        });
        prix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                price = editable.toString();

            }
        });
        allergene.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                allergi = editable.toString();

            }
        });
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                desc = editable.toString();

            }
        });
        typerepa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tRepas = editable.toString();

            }
        });
        ingredients.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ingredient = editable.toString();

            }
        });
        MenuReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    Repa account = child.getValue(Repa.class);
                    repas.add(account);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



    }

    public void add(View view){
        if (isempty()){
            Repa repa= new Repa(nom,tRepas,tCuisine,ingredient,allergi,price,desc,id);
            repa.setOffered("false");
            String key = MenuReference.push().getKey();
            MenuReference.child(key).setValue(repa);
            Toast toast = Toast.makeText(getApplicationContext(),"Le repa a ete ajouter au menu", Toast.LENGTH_SHORT);
            toast.show();

        }

    }

    public void retour(View view){
        Intent intent = new Intent(getApplicationContext(), Menu_cuisinier.class);
        startActivity(intent);
    }

    private boolean isempty(){
        if (nom==null ||nom.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Le nom ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (tRepas==null ||tRepas.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Le type de repa ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (tCuisine==null ||tCuisine.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Le type de cuisine peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (ingredient==null ||ingredient.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Les ingredients peu pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (allergi==null ||allergi.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Les allergene ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (price==null ||price.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Le prix ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (desc==null ||desc.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"La description ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }


        return true;
    }
}