package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class administrateur_sign_in extends AppCompatActivity {
    private DatabaseReference AccountReference;
    private EditText username;
    private EditText password;
    private Role role=null;
    private String name, pwd,status;
    private List<Account> accounts = new ArrayList<>();
    private String id_tmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrateur_sign_in);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        AccountReference = FirebaseDatabase.getInstance().getReference("Account");

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

        AccountReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    Account account = child.getValue(Account.class);
                    account.setId(child.getKey());
                    accounts.add(account);
                }
                System.out.println(accounts);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void inscrire_client(View view){
        Intent intent = new Intent(getApplicationContext(),inscrire_client.class);
        startActivity(intent);
    }
    public void inscrire_cuisinier(View view){
        Intent intent = new Intent(getApplicationContext(),inscrire_cuisinier.class);
        startActivity(intent);
    }
    public void retour(View view){
        Intent intent = new Intent(getApplicationContext(),Main_page.class);
        startActivity(intent);
    }

    public void connecter(View view){
        if (isempty()) {

            if (namePwdCorrect()) {
                Intent intent;
                if(name.equals("Administrateur")){
                    intent = new Intent(getApplicationContext(), AdminHome.class);

                }
                else{
                    if(role.toString().equals("client")){
                        intent = new Intent(getApplicationContext(), Bienvenue_client.class);

                    }
                    else{
                        intent = new Intent(getApplicationContext(), Bienvenue_cuisinier.class);

                    }
                    Account dummy= new Account();
                    dummy.setCurrentuserID(id_tmp);
                }
                startActivity(intent);

            }
        }
    }

    private boolean isempty(){
        if (name==null ||name==""){
            Toast toast = Toast.makeText(getApplicationContext(),"Username ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (pwd==null ||pwd ==""){
            Toast toast = Toast.makeText(getApplicationContext(),"Le password ne peut pas etre vide", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }


    private boolean namePwdCorrect(){
        Account acc = null;
        role=null;
        for (Account account: accounts){
            if (account.getName().equals(name)){
                acc = account;
                role=acc.getRole();
                id_tmp=acc.getId();
            }
        }
        if (acc == null){
            Toast toast = Toast.makeText(getApplicationContext(),"Le username n'existe pas", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (! acc.getPassword().equals(pwd)){
            Toast toast = Toast.makeText(getApplicationContext(),"Le password n'est pas correct", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if(! acc.getStatus().equals("Active")){
            if(acc.getStatus().equals("ban2days")){
                Intent intent;
                intent  = new Intent(getApplicationContext(), ban2days.class);
                startActivity(intent);
                return false;

            }else{
                Intent intent;
                intent  = new Intent(getApplicationContext(), Banning.class);
                startActivity(intent);
                return false;
            }

        }
        return true;
    }

}