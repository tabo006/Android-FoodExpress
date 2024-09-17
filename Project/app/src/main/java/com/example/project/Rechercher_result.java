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

public class Rechercher_result extends AppCompatActivity {

    private DatabaseReference cuisinierReference;
    private DatabaseReference menuReference;

    private List<Repa> repas;
    private List<Account> accounts;

    private ListView listView;
    private String tcuisine, trepa, tsearch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher_result);

        Intent intent = getIntent();
        tcuisine= intent.getStringExtra("cuisine");
        trepa= intent.getStringExtra("repa");
        tsearch= intent.getStringExtra("tsearch");

        listView = findViewById(R.id.search_result_1);
        Client dummy= new Client();
        repas = new LinkedList<>();
        accounts = new LinkedList<Account>();

        cuisinierReference = FirebaseDatabase.getInstance().getReference("Cuisinier");


        cuisinierReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Map<String,String >> data = new LinkedList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Account account = child.getValue(Account.class);
                    account.setId(child.getKey());
                    if (account.getStatus().equals("Active")) {
                        accounts.add(account);
                        menuReference = FirebaseDatabase.getInstance().getReference("Cuisinier/" + account.getId() + "/menu/repa_list");

                        menuReference.addValueEventListener(new ValueEventListener() {
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot child : snapshot.getChildren()) {
                                    Repa repa = child.getValue(Repa.class);
                                    repa.setId(child.getKey());

                                    if (repa.getId().equals("0") == false && repa.getOffered().equals("true")) {
                                        if (tsearch.equals("1") && repa.getTypecuisine().equals(tcuisine)) {
                                            repas.add(repa);
                                            Map<String, String> dataMap = new HashMap<>();
                                            dataMap.put("name", repa.getNom());
                                            dataMap.put("Type de cuisine", repa.getTypecuisine());
                                            dataMap.put("Ingredients", repa.getIngredients());
                                            dataMap.put("Type de repas", repa.getTyperepas());
                                            dataMap.put("Prix", repa.getPrix());
                                            dataMap.put("Allergenes", repa.getAllergene());
                                            dataMap.put("Description", repa.getDescription());
                                            dataMap.put("id", repa.getId());
                                            dataMap.put("Offered", repa.getOffered());
                                            dataMap.put("Cuisinier name", account.getName());
                                            dataMap.put("Cuisinier id", account.getId());
                                            data.add(dataMap);
                                        } else if (tsearch.equals("2") && repa.getNom().equals(trepa)) {
                                            repas.add(repa);
                                            Map<String, String> dataMap = new HashMap<>();
                                            dataMap.put("name", repa.getNom());
                                            dataMap.put("Type de cuisine", repa.getTypecuisine());
                                            dataMap.put("Ingredients", repa.getIngredients());
                                            dataMap.put("Type de repas", repa.getTyperepas());
                                            dataMap.put("Prix", repa.getPrix());
                                            dataMap.put("Allergenes", repa.getAllergene());
                                            dataMap.put("Description", repa.getDescription());
                                            dataMap.put("id", repa.getId());
                                            dataMap.put("Offered", repa.getOffered());
                                            dataMap.put("Cuisinier name", account.getName());
                                            dataMap.put("Cuisinier id", account.getId());
                                            data.add(dataMap);
                                        } else if (tsearch.equals("3") && (repa.getNom().equals(trepa) | repa.getTypecuisine().equals(tcuisine))) {
                                            repas.add(repa);
                                            Map<String, String> dataMap = new HashMap<>();
                                            dataMap.put("name", repa.getNom());
                                            dataMap.put("Type de cuisine", repa.getTypecuisine());
                                            dataMap.put("Ingredients", repa.getIngredients());
                                            dataMap.put("Type de repas", repa.getTyperepas());
                                            dataMap.put("Prix", repa.getPrix());
                                            dataMap.put("Allergenes", repa.getAllergene());
                                            dataMap.put("Description", repa.getDescription());
                                            dataMap.put("id", repa.getId());
                                            dataMap.put("Offered", repa.getOffered());
                                            dataMap.put("Cuisinier name", account.getName());
                                            dataMap.put("Cuisinier id", account.getId());
                                            data.add(dataMap);
                                        }
                                    }
                                }

                                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),data,R.layout.search_list_view,
                                        new String[]{"name","Type de cuisine","Ingredients","Type de repas","Allergenes", "Prix", "Description","id","Offered", "Cuisinier name","Cuisinier id"},new int[]{R.id.repa_name,R.id.tcuisine, R.id.ingredients,R.id.trepa,R.id.allergenerepa,R.id.prixrepa,R.id.descriptionrepa, R.id.repaid, R.id.offered, R.id.cuisinier_name, R.id.cuisinier_id});

                                listView.setAdapter(adapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        TextView nameTextView = (TextView) view.findViewById(R.id.repa_name);
                                        TextView tcuisineIdTextView = (TextView) view.findViewById(R.id.tcuisine);
                                        TextView trepaTextView = (TextView) view.findViewById(R.id.trepa);
                                        TextView allergeneTextView = (TextView) view.findViewById(R.id.allergenerepa);
                                        TextView prixTextView = (TextView) view.findViewById(R.id.prixrepa);
                                        TextView ingredientTextView = (TextView) view.findViewById(R.id.ingredients);
                                        TextView descriptionTextView = (TextView) view.findViewById(R.id.descriptionrepa);
                                        TextView idTextView = (TextView) view.findViewById(R.id.repaid);
                                        TextView offerTextView = (TextView) view.findViewById(R.id.offered);
                                        TextView cuisiniernameTextView = (TextView) view.findViewById(R.id.cuisinier_name);
                                        TextView cuisinieridTextView = (TextView) view.findViewById(R.id.cuisinier_id);
                                        Intent intent = new Intent(getApplicationContext(),createOrder.class);
                                        intent.putExtra("Nom",nameTextView.getText());
                                        intent.putExtra("Ingredients",ingredientTextView.getText());
                                        intent.putExtra("Type de cuisine",tcuisineIdTextView.getText());
                                        intent.putExtra("Type de repa",trepaTextView.getText());
                                        intent.putExtra("Allergenes",allergeneTextView.getText());
                                        intent.putExtra("Prix",prixTextView.getText());
                                        intent.putExtra("Description",descriptionTextView.getText());
                                        intent.putExtra("id",idTextView.getText());
                                        intent.putExtra("Offered",offerTextView.getText());
                                        intent.putExtra("Cuisinier name",cuisiniernameTextView.getText());
                                        intent.putExtra("Cuisinier id",cuisinieridTextView.getText());
                                        startActivity(intent);

                                    }
                                });
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }




            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }






    public void retour(View view){
        Intent intent = new Intent(getApplicationContext(), Recherche_client.class);
        startActivity(intent);
    }


}