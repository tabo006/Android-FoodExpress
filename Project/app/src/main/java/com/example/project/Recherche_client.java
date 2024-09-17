package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Recherche_client extends AppCompatActivity {
    private EditText tcuisine, trepa;
    private String cuisine, repa;
    private RadioGroup typeresearch;
    private Integer tresearch =0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_client);

        tcuisine = (EditText) findViewById(R.id.type_cuisine);
        trepa = (EditText) findViewById(R.id.type_repa);
        typeresearch = (RadioGroup) findViewById(R.id.Tsearch);


        tcuisine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cuisine = editable.toString();

            }
        });

        trepa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                repa = editable.toString();

            }
        });

        typeresearch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                tresearch = radioGroup.getCheckedRadioButtonId();
                if(tresearch == R.id.Tcuisine ){
                    tresearch = 1;
                }
                else if(tresearch == R.id.Trepa ){
                    tresearch = 2;
                }
                else if(tresearch == R.id.Tboth ){
                    tresearch = 3;
                }
                else tresearch=0;
            }
        });
    }
    public void search(View view){
        if (isSelect() && isFormat()){
            Intent intent = new Intent(getApplicationContext(), Rechercher_result.class);
            intent.putExtra("cuisine",cuisine);
            intent.putExtra("repa",repa);
            intent.putExtra("tsearch",tresearch.toString());
            if(tresearch==1){
                intent.putExtra("id",1);

            }
            else if(tresearch==2){
                intent.putExtra("id",2);
            }
            else if(tresearch==3){
                intent.putExtra("id",3);
            }

            startActivity(intent);
        }

    }

    public boolean isSelect(){
        boolean result=true;
        if (tresearch==0){
            Toast toast = Toast.makeText(getApplicationContext(),"Le type de rechercher doit etre choisi", Toast.LENGTH_SHORT);
            toast.show();
            result= false;
        }
        else result= true;

        return result;
    }
    public void retour(View view){
        Intent intent = new Intent(getApplicationContext(), Bienvenue_client.class);
        startActivity(intent);
    }
    public boolean isFormat(){
        boolean result=true;

        if (tresearch==1){
            if (cuisine==null){
                Toast toast = Toast.makeText(getApplicationContext(),"Le type de cuisine ne peut pas etre vide", Toast.LENGTH_SHORT);
                toast.show();
                result= false;
            }
            else if(cuisine.equals("")){
                Toast toast = Toast.makeText(getApplicationContext(),"Le type de cuisine ne peut pas etre vide", Toast.LENGTH_SHORT);
                toast.show();
                result= false;
            }

        }
        else if (tresearch==2){
            if (repa==null){
                Toast toast = Toast.makeText(getApplicationContext(),"Le nom du repa ne peut pas etre vide", Toast.LENGTH_SHORT);
                toast.show();
                result= false;
            }
            else if(repa.equals("")){
                Toast toast = Toast.makeText(getApplicationContext(),"Le nom du repa ne peut pas etre vide", Toast.LENGTH_SHORT);
                toast.show();
                result= false;
            }

        }
        else if (tresearch==3){
            if (cuisine==null | repa==null){
                Toast toast = Toast.makeText(getApplicationContext(),"Le type de cuisine et le nome du repa ne peuvent pas etre vide", Toast.LENGTH_SHORT);
                toast.show();
                result= false;
            }
            else if(repa.equals("") | cuisine.equals("")){
                Toast toast = Toast.makeText(getApplicationContext(),"Le type de cuisine et le nome du repa ne peuvent pas etre vide", Toast.LENGTH_SHORT);
                toast.show();
                result= false;
            }
        }

        else result= true;

        return result;
    }


}

