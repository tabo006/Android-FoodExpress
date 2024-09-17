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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class order extends AppCompatActivity {
    private DatabaseReference comRef,ratingRef;
    private EditText rating;
    private String rate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        rating = (EditText) findViewById(R.id.rating);
        String id  = Account.getCurrentuserID();
        rating.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                rate = editable.toString();

            }
        });

        TextView orderName = (TextView)findViewById(R.id.orderN);
        TextView orderDesc = (TextView) findViewById(R.id.orderD);
        TextView orderPrice = (TextView) findViewById(R.id.orderP);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView orderStatus = (TextView) findViewById(R.id.order_status);
        TextView cookName = (TextView)findViewById(R.id.ncook);

        Intent intent = getIntent();
        String nom = intent.getStringExtra("Nom");
        String desc = intent.getStringExtra("Description");
        String price = intent.getStringExtra("Price");
        String status = intent.getStringExtra("Status");
        String nomCook = intent.getStringExtra("Cook");



        orderName.setText(nom);
        orderDesc.setText(desc);
        orderPrice.setText(price);
        orderStatus.setText(status);
        cookName.setText(nomCook);
        comRef = FirebaseDatabase.getInstance().getReference("Client/"+id+"/commandes/commandes");

    }
    public void back(View view){
        Intent intent = new Intent(getApplicationContext(), orders.class);
        startActivity(intent);
    }
    public void complaint(View view){
        Intent intent = getIntent();
        String cook = intent.getStringExtra("Cook");
        String cookid=intent.getStringExtra("Cook id");

        Intent intent1 = new Intent(getApplicationContext(), createComplaint.class);
        intent1.putExtra("Cook",cook);
        intent1.putExtra("Cook id",cookid);
        startActivity(intent1);
    }
    public void cookInfo(View view){
        Intent intent0 = getIntent();
        String cook = intent0.getStringExtra("Cook");
        String cookId = intent0.getStringExtra("Cook id");
        Intent intent = new Intent(getApplicationContext(),cookInfo.class);
        intent.putExtra("Cook",cook);
        intent.putExtra("Cook id", cookId);
        startActivity(intent);
    }
    public void rate(View view){
        Intent intent = getIntent();
        String cookid=intent.getStringExtra("Cook id");
        int result=0;
        if (rate==null){
            Toast toast = Toast.makeText(getApplicationContext(),"Entree un rating", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (!(rate.equals("0") |rate.equals("1") |rate.equals("2") |rate.equals("3") |rate.equals("4") |rate.equals("5"))){
            Toast toast = Toast.makeText(getApplicationContext(),"Please enter a valid whole number rating between 1-5", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            if(rate.equals("0")) {
                result=0;
            }
            else if(rate.equals("1")) {
                result=1;
            }
            else if(rate.equals("2")) {
                result=2;
            }
            else if(rate.equals("3")) {
                result=3;
            }
            else if(rate.equals("4")) {
                result=4;
            }
            else if(rate.equals("5")) {
                result=5;
            }
            final Rating[] rating = new Rating[1];
            ratingRef= FirebaseDatabase.getInstance().getReference("Cuisinier/"+cookid);
            int finalResult = result;
            ratingRef.child("/rating").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                ///name**
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    DataSnapshot result1 = task.getResult();
                    rating[0] = result1.getValue(Rating.class);
                    rating[0].addRate(finalResult);
                    ratingRef.child("rating").child("rating").setValue(rating[0].getRating());
                    ratingRef.child("rating").child("num_rates").setValue(rating[0].getNum_rates());
                    ratingRef.child("rating").child("rating_total").setValue(rating[0].getRating_total());
                    Toast toast = Toast.makeText(getApplicationContext(),"Le repa a ete evaluer", Toast.LENGTH_SHORT);
                    toast.show();

                }
            });

            //System.out.println(rdata[0]);
           // System.out.println(ldata[0]);

            //Rating rating= new Rating(ldata[0],rdata[0]);
            //rating.addRate(result);
           // ratingRef= FirebaseDatabase.getInstance().getReference("Cuisinier/"+cookid+"/rating");
           // ratingRef.child("rating").setValue(rating.getRating());
           // ratingRef.child("num_rates").setValue(rating.getNum_rates());

        }

    }
}