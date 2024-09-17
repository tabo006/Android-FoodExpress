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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class inscrire_cuisinier extends AppCompatActivity {

    private DatabaseReference CuisinierReference;
    private EditText username, password,firstname,lastname,state,city,country,postalcode,streetaddress,bio;
    private String name, pwd, firstn,lastn,sta,cit,countr,pcode,saddress,biography;
    private RadioGroup role;
    private int roleid =-1;
    private List<Account> cuisinier = new ArrayList<>();
    private Role rol;
    private String id;
    private List<String> cuisinier_name= new ArrayList<>();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire_cuisinier);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password);
        firstname= (EditText) findViewById(R.id.firstname);
        lastname= (EditText) findViewById(R.id.lastname);
        state= (EditText) findViewById(R.id.state);
        city= (EditText) findViewById(R.id.city);
        country= (EditText) findViewById(R.id.country);
        postalcode= (EditText) findViewById(R.id.postalcode);
        streetaddress=(EditText) findViewById(R.id.streetaddress);
        bio=(EditText) findViewById(R.id.bio);


        CuisinierReference = FirebaseDatabase.getInstance().getReference("Cuisinier");
        bio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                biography = editable.toString();

            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                name = editable.toString();

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pwd = editable.toString();

            }
        });
        firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                firstn = editable.toString();

            }
        });
        lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                lastn = editable.toString();

            }
        });
        state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sta = editable.toString();

            }
        });
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cit = editable.toString();

            }
        });
        country.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                countr = editable.toString();

            }
        });
        postalcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pcode = editable.toString();

            }
        });
        streetaddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                saddress = editable.toString();

            }
        });

        CuisinierReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    String id=child.getKey();
                    CuisinierReference.child(id+"/name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        ///name**
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            DataSnapshot result = task.getResult();
                            cuisinier_name.add(result.getValue(String.class));


                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void inscrire(View view){
        if (isempty()){
            if (issame()){
                Menu vide= new Menu();
                Repa vide2= new Repa("x","x","x","x","x","x","x",id);
                vide2.setOffered("x");
                vide.addRepa(vide2);

                Commandes empty = new Commandes();
                Repa rep = new Repa("0","0","0","0","0","0","0",id);
                rep.setOffered("0");
                Commande none = new Commande("0","0","0","0","0",rep,"0","0","0");
                empty.addToCommandes(none);
                Rating rating= new Rating(0,0,0);
                Address address= new Address(cit,countr,saddress,sta,pcode);
                Cuisinier account = new Cuisinier(firstn,lastn,name, address,pwd,id,vide,biography,rating,empty);
                String key = CuisinierReference.push().getKey();
                CuisinierReference.child(key).setValue(account);
                Toast toast = Toast.makeText(getApplicationContext(),"Le compte a ete cree", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    public void connecter(View view){
        Intent intent = new Intent(getApplicationContext(), Main_page.class);
        startActivity(intent);
    }

    private boolean isempty(){
        if (firstn==null ||firstn ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"Le Prenom ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (lastn==null ||lastn ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"Le nom ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (name==null ||name ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"Username ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (pwd==null ||pwd ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"Le password ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (saddress==null ||saddress ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"L'addresse ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (pcode==null ||pcode ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"Le code postal ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (sta==null ||sta ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"L'état ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (cit==null ||cit ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"Le ville ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (countr==null ||countr ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"Le pay ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        Pattern pattern = Pattern.compile("[@].*[.][a-zA-z]{3}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        boolean matchFound = matcher.find();
        if (!matchFound){
            Toast toast = Toast.makeText(getApplicationContext(),"username should be a valid email", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        return true;
    }

    private boolean issame(){
        for (String account: cuisinier_name){
            if (account.equals(name)){
                Toast toast = Toast.makeText(getApplicationContext(),"Le username ne peut pas etre pareille", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }
        return true;
    }



}