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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class orders_cuisinier extends AppCompatActivity {

    private DatabaseReference comRef,ratingRef;
    private EditText rating;
    private String rate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_cuisinier);

        String id  = Account.getCurrentuserID();


        TextView orderName = (TextView)findViewById(R.id.orderN);
        TextView orderDesc = (TextView) findViewById(R.id.orderD);
        TextView orderPrice = (TextView) findViewById(R.id.orderP);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView orderStatus = (TextView) findViewById(R.id.order_status);


        Intent intent = getIntent();
        String nom = intent.getStringExtra("Nom");
        String desc = intent.getStringExtra("Description");
        String price = intent.getStringExtra("Price");
        String status = intent.getStringExtra("Status");

        orderName.setText(nom);
        orderDesc.setText(desc);
        orderPrice.setText(price);
        orderStatus.setText(status);

    }
    public void Accept(View view){
        Intent intent=getIntent();
        String command_id= intent.getStringExtra("id");
       DatabaseReference commandeReference=FirebaseDatabase.getInstance().getReference("Cuisinier/"+Account.getCurrentuserID()+"/commandes/commandes");
       commandeReference.child(command_id).child("status").setValue("ACCEPTÉ");
        Toast toast = Toast.makeText(getApplicationContext(),"La commande a ete accepte", Toast.LENGTH_SHORT);
        toast.show();
        String client_id= intent.getStringExtra("Client id");
        String client_commande_id=intent.getStringExtra("Commande id");
        DatabaseReference clientReference=FirebaseDatabase.getInstance().getReference("Client/"+client_id+"/commandes/commandes");
        System.out.println(client_commande_id);
        clientReference.child(client_commande_id).child("status").setValue("ACCEPTÉ");



       // Intent intent = new Intent(getApplicationContext(), orders.class);
       // startActivity(intent);
    }
    public void reject(View view){
        Intent intent=getIntent();
        String command_id= intent.getStringExtra("id");
        DatabaseReference commandeReference=FirebaseDatabase.getInstance().getReference("Cuisinier/"+Account.getCurrentuserID()+"/commandes/commandes");
        commandeReference.child(command_id).child("status").setValue("REJETÉ");
        Toast toast = Toast.makeText(getApplicationContext(),"La commande a ete rejete", Toast.LENGTH_SHORT);
        toast.show();
        String client_id= intent.getStringExtra("Client id");
        String client_commande_id=intent.getStringExtra("Commande id");
        DatabaseReference clientReference=FirebaseDatabase.getInstance().getReference("Client/"+client_id+"/commandes/commandes");
        System.out.println(client_commande_id);
        clientReference.child(client_commande_id).child("status").setValue("REJETÉ");
    }
    public void back(View view){
        Intent intent = new Intent(getApplicationContext(), Commande_list_cuisinier.class);
        startActivity(intent);
    }
}