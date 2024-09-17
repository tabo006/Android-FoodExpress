package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createComplaint extends AppCompatActivity {
    private EditText comp;
    DatabaseReference comRef;
    private String comp_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);
        comp = (EditText) findViewById(R.id.complaintBox);

        comp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                comp_text = editable.toString();

            }
        });
    }

    public void createC(View view){
        Complaint complaint = new Complaint();
        complaint.setDescription(comp_text);
        complaint.setCustomerID(Account.currentuserID);
        Intent intent= getIntent();
        String cookId = intent.getStringExtra("Cook id");
        complaint.setCookId(cookId);
        Toast toast = Toast.makeText(getApplicationContext(),"Plainte a ete cree", Toast.LENGTH_SHORT);
        toast.show();
        comRef = FirebaseDatabase.getInstance().getReference("Complaints");
        String key = comRef.push().getKey();
        comRef.child(key).setValue(complaint);
        Intent intent2 = new Intent(getApplicationContext(), orders.class);
        startActivity(intent2);
    }
}