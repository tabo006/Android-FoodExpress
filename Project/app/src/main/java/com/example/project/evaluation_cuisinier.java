package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class evaluation_cuisinier extends AppCompatActivity {
    private DatabaseReference comRef,ratingRef;
    private TextView rating_text;
    private String rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_cuisinier);
        rating_text = (TextView) findViewById(R.id.rating_repa);
        String id = Account.getCurrentuserID();
        final Rating[] rating = new Rating[1];
        ratingRef = FirebaseDatabase.getInstance().getReference("Cuisinier/" + id);
        ratingRef.child("/rating").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            ///name**
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot result1 = task.getResult();
                rating[0] = result1.getValue(Rating.class);
                String txt= String.valueOf(rating[0].getRating());
                rating_text.setText(txt);

            }
        });
    }
    public void retour(View view){
        Intent intent = new Intent(getApplicationContext(), Bienvenue_cuisinier.class);
        startActivity(intent);
    }
}