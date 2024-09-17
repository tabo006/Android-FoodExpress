package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Commande_list_cuisinier extends AppCompatActivity {

    private DatabaseReference clientRef;
    private DatabaseReference commandeRef;

    private List<Commande> commandeList;

    private ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande_list_cuisinier);

        listView=(ListView)findViewById(R.id.listOrder);
        commandeList = new LinkedList<>();
        String id = Account.getCurrentuserID();

        clientRef = FirebaseDatabase.getInstance().getReference("Cuisinier/"+id+"/commandes/commandes");

        clientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commandeList.clear();
                List<Map<String,String>> data = new LinkedList<>();

                for(DataSnapshot child: snapshot.getChildren()){
                    Commande c = child.getValue(Commande.class);
                    c.setId(child.getKey());
                    if(c.getId().equals("0")==false){
                        commandeList.add(c);

                        Map<String, String> dataMap = new HashMap<>();
                        dataMap.put("name",c.getName());
                        dataMap.put("cook", c.getCook());
                        dataMap.put("price", c.getPrice());
                        dataMap.put("status", c.getStatus());
                        dataMap.put("id", c.getId());
                        dataMap.put("Description", c.getDesc());
                        dataMap.put("Cook id", c.getCookID());
                        dataMap.put("Client name", c.getClientname());
                        dataMap.put("Client id", c.getClientID());
                        dataMap.put("Commande id", c.getCommandeid());
                        data.add(dataMap);
                    }
                }

                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),data,R.layout.activity_orders_list_view,
                        new String[]{"name", "status", "Client name", "price","Description","Cook id","id","Client id", "Commande id"},new int[]{R.id.ordN, R.id.status,R.id.cookN,R.id.ordP,R.id.ordDesc, R.id.cook_id,R.id.commande_id, R.id.client_id,R.id.client_commande_id});
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView nameTextView = (TextView) view.findViewById(R.id.ordN);
                        TextView statusTextView =(TextView) view.findViewById(R.id.status);
                        TextView cookTextView = (TextView)view.findViewById(R.id.cookN);
                        TextView priceTextView = (TextView)view.findViewById(R.id.ordP);
                        TextView descTextView = (TextView)view.findViewById(R.id.ordDesc);
                        TextView cookidTextView = (TextView)view.findViewById(R.id.cook_id);
                        TextView commandeidTextView = (TextView)view.findViewById(R.id.commande_id);
                        TextView clientidTextView = (TextView)view.findViewById(R.id.client_id);
                        TextView clientCommandeidTextView = (TextView)view.findViewById(R.id.client_commande_id);
                        Intent intent = new Intent(getApplicationContext(), orders_cuisinier.class);
                        intent.putExtra("Nom",nameTextView.getText());
                        intent.putExtra("Status",statusTextView.getText());
                        intent.putExtra("Client name", cookTextView.getText());
                        intent.putExtra("Price", priceTextView.getText());
                        intent.putExtra("Description", descTextView.getText());
                        intent.putExtra("Cook id", cookidTextView.getText());
                        intent.putExtra("id", commandeidTextView.getText());
                        intent.putExtra("Client id", clientidTextView.getText());
                        intent.putExtra("Commande id", clientCommandeidTextView.getText());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void back (View view){
        Intent intent = new Intent(getApplicationContext(), Bienvenue_cuisinier.class);
        startActivity(intent);
    }
}