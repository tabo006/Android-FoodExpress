package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class ComplaintActivity extends AppCompatActivity {
    private DatabaseReference complaintsReference;
    private List<Complaint> complaints;
    private DatabaseReference accountsReference;
    private DatabaseReference cuisinierReference;


    private TextView ClientTextName, cuisinerTextName, ComplaintText;
    private String complaintId;
    private String cuisinerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        complaints = new LinkedList<>();
        accountsReference = FirebaseDatabase.getInstance().getReference("Account");
        cuisinierReference = FirebaseDatabase.getInstance().getReference("Cuisinier");
        complaintsReference = FirebaseDatabase.getInstance().getReference("Complaints");

        ClientTextName = (TextView)findViewById(R.id.ClientTextName);
        cuisinerTextName = (TextView)findViewById(R.id.cuisinerTextName);
        ComplaintText = (TextView)findViewById(R.id.ComplaintText);

        Intent intent = getIntent();
        complaintId =  intent.getStringExtra("id");
        String clientname =  intent.getStringExtra("client");
        String cuisinername =  intent.getStringExtra("cuisiner");
        cuisinerId = intent.getStringExtra("cuisinerId");
        ClientTextName.setText(clientname);
        cuisinerTextName.setText(cuisinername);


        complaintsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                complaints.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Complaint complaint = child.getValue(Complaint.class);
                    complaints.add(complaint);
                    complaint.setId(child.getKey());
                    if (complaint.getId().equals(complaintId)){
                        ComplaintText.setText(complaint.getDescription());

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    public void dismiss(View view){

        cuisinierReference.child(cuisinerId).child("status").setValue("Active");
        Toast toast = Toast.makeText(getApplicationContext(),"La pleinte a ete rejete", Toast.LENGTH_SHORT);
        toast.show();
        complaintsReference.child(complaintId).removeValue();
        finish();
    }
    public void suspendre(View view){
        cuisinierReference.child(cuisinerId).child("status").setValue("BANNING");
        complaintsReference.child(complaintId).removeValue();
        Toast toast = Toast.makeText(getApplicationContext(),"Le compte a ete suspendu", Toast.LENGTH_SHORT);
        toast.show();
    }public void ban2days(View view){
        cuisinierReference.child(cuisinerId).child("status").setValue("ban2days");
        complaintsReference.child(complaintId).removeValue();
        Toast toast = Toast.makeText(getApplicationContext(),"Le compte a ete suspendu", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void retour (View view){
        Intent intent = new Intent(getApplicationContext(), AdminHome.class);
        startActivity(intent);
    }
}