package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class cookInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_info);
        Intent intent = getIntent();
        String id = intent.getStringExtra("Cook id");

        final Address[] ad = new Address[1];
        DatabaseReference addressRef = FirebaseDatabase.getInstance().getReference("Cuisinier/"+id);


        addressRef.child("/useraddress").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            ///name**
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot result1 = task.getResult();
                ad[0] = result1.getValue(Address.class);
                System.out.println(ad[0].getCity());
                String city = ad[0].getCity();
                String country = ad[0].getCountry();
                String pcode=ad[0].getPostalCode();
                String state=ad[0].getState();
                String street =ad[0].getStreet();
                String address = city+"  "+country+"  "+pcode+"  "+state+"  "+street;
                TextView addr = (TextView) findViewById(R.id.adresse);
                addr.setText(address);
            }
        });


        final String[] nom = new String[1];
        DatabaseReference nameRef = FirebaseDatabase.getInstance().getReference("Cuisinier/"+id);
        nameRef.child("/name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            ///name**
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot result1 = task.getResult();
                nom[0] = result1.getValue(String.class);
                TextView name = (TextView) findViewById(R.id.nameC);
                name.setText(nom[0]);
            }
        });

        final String[] bio = new String[1];
        DatabaseReference bioRef = FirebaseDatabase.getInstance().getReference("Cuisinier/"+id);
        nameRef.child("/bio").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            ///name**
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot result1 = task.getResult();
                bio[0] = result1.getValue(String.class);
                TextView bioC = (TextView) findViewById(R.id.bioC);
                bioC.setText(bio[0]);
            }
        });

        final Rating[] rating = new Rating[1];
        DatabaseReference ratingRef= FirebaseDatabase.getInstance().getReference("Cuisinier/"+id);

        ratingRef.child("/rating").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            ///name**
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot result1 = task.getResult();
                rating[0] = result1.getValue(Rating.class);
                TextView rate  = (TextView)findViewById(R.id.eval);
                //ratingRef.child("rating").child("rating").setValue(rating[0].getRating());
                String x  =  String.valueOf(rating[0].getRating());
                rate.setText(x);

            }
        });





    }

    public void back(View view){
        Intent intent =  new Intent(getApplicationContext(), orders.class);
        startActivity(intent);
    }
}