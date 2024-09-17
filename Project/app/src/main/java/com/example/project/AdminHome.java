package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AdminHome extends AppCompatActivity {
    //database reference
    private DatabaseReference accountsReference;
    private DatabaseReference complaintsReference;
    private DatabaseReference clientReference;
    private DatabaseReference cuisinerReference;

    //components
    private ListView listView;

    private List<String> customerIds;
    private List<String> cookIds;
    private List<Complaint> complaints;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        listView=(ListView)findViewById(R.id.listMenu);

        customerIds = new LinkedList<>();
        cookIds = new LinkedList<>();
        complaints = new LinkedList<>();

        accountsReference = FirebaseDatabase.getInstance().getReference("Account");
        complaintsReference = FirebaseDatabase.getInstance().getReference("Complaints");
        clientReference = FirebaseDatabase.getInstance().getReference("Client");
        cuisinerReference = FirebaseDatabase.getInstance().getReference("Cuisinier");
        clientReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerIds.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Account account = child.getValue(Account.class);
                    account.setId(child.getKey());
                    customerIds.add(account.getId());
                    System.out.println(account.getId());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        cuisinerReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cookIds.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Account account = child.getValue(Account.class);
                    account.setId(child.getKey());
                    cookIds.add(account.getId());
                    System.out.println(child);
                }
                ///createRandomComplaints(10);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
//        accountsReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                customerIds.clear();
//                cookIds.clear();
//                for(DataSnapshot child : snapshot.getChildren()){
//
//                    Account account = child.getValue(Account.class);
//                    account.setId(child.getKey());
//                    switch(account.getRole()){
//                        case client:
//                            customerIds.add(account.getId());
//                            break;
//                        case cuisiner:
//                            cookIds.add(account.getId());
//                            break;
//                        default:
//                            break;
//
//                    }
//
//
//                }
//                System.out.println("1");
        ///createRandomComplaints(10);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        complaintsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                complaints.clear();
                List<Map<String,String >> data = new LinkedList<>();
                for(DataSnapshot child : snapshot.getChildren()){
                    System.out.println(child);
                    Complaint complaint = child.getValue(Complaint.class);
                    if (!child.getKey().equals("0")) {
                        complaints.add(complaint);
                        complaint.setId(child.getKey());

                        Map<String, String> dataMap = new HashMap<>();
                        dataMap.put("id", complaint.getId());
                        dataMap.put("cuisinerid", complaint.getCookId());
                        clientReference.child(complaint.getCustomerID() + "/name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            ///name**
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                DataSnapshot result = task.getResult();
                                dataMap.put("Client", result.getValue(String.class));
                            }
                        });
                        cuisinerReference.child(complaint.getCookId() + "/name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            ///name**
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                DataSnapshot result2 = task.getResult();
                                dataMap.put("Cuisiner", result2.getValue(String.class));

                            }
                        });
                        data.add(dataMap);
                    }
                }
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),data,R.layout.activity_complaints_list_view_layout,
                        new String[]{"id","cuisinerid","Client","Cuisiner"},new int[]{R.id.idtextView,R.id.cuisinerid,R.id.ClientText,R.id.CookText});
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView idTextView = (TextView) view.findViewById(R.id.idtextView);
                        TextView cuisinerIdTextView = (TextView) view.findViewById(R.id.cuisinerid);
                        TextView clientTextView = (TextView) view.findViewById(R.id.ClientText);
                        TextView cuisinerTextView = (TextView) view.findViewById(R.id.CookText);
                        Intent intent = new Intent(getApplicationContext(),ComplaintActivity.class);
                        intent.putExtra("id",idTextView.getText());
                        intent.putExtra("cuisinerId",cuisinerIdTextView.getText());
                        intent.putExtra("client",clientTextView.getText());
                        intent.putExtra("cuisiner",cuisinerTextView.getText());
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        complaintsReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                task.getResult();
            }
        });
        clientReference.child("1/name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot result = task.getResult();

            }
        });
        cuisinerReference.child("1/name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot result = task.getResult();
            }
        });




    }



    private void createRandomComplaints(int n){
        Random random = new Random();
        for (int i = 0;i<n;i++){
            Complaint complaint = new Complaint();
            complaint.setDescription("Complaint!!!!!!!!!!!!");
            String customerId = customerIds.get(random.nextInt(customerIds.size()));
            String cookId = cookIds.get(random.nextInt(cookIds.size()));
            complaint.setCookId(cookId);
            complaint.setCustomerID(customerId);
            complaintsReference.push().setValue(complaint);

        }


    }
    public void logoff(View view){
        Intent intent = new Intent(getApplicationContext(), Main_page.class);
        startActivity(intent);
    }
}