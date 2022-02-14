package com.example.finalprojectbcs430W;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FindPersonalTrainer extends AppCompatActivity implements Filterable {

    private List<PT> ptList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PTAdapter adapter;
    String mDocumentID;
    String email;

    private FirebaseFirestore db;

    public static final int SHOW_SUB_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_personal_trainer);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFindPT);
        adapter = new PTAdapter(ptList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();

        retriveData();
    }

    private void retriveData()
    {

        db.collection("PersonalTrainer")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            PT pt = null;
                            ptList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mDocumentID = document.getId();


                                pt = new PT(document.getString("First Name"),
                                        document.getString("Last Name"),
                                        document.getString("Phone Number"),
                                        document.getString("Certifications"),
                                        document.getString("Availability"),
                                        document.getString("Preferred Exercise"),
                                        document.getString("Facebook Username"),
                                        document.getString("Gender"),
                                        document.getString("Preferred Workout Location"),
                                        document.getString("email"));
                                ptList.add(pt);
                            }

                                adapter.notifyDataSetChanged();
                                getApplicationContext();

                            }
                        }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recyclerview_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Sign Out");
                alert.setMessage("Are you sure you would like to sign out?");
                alert.setPositiveButton("Confirm", (v,a)->{
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    Toast logout = Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT);
                    logout.show();
                    finish();
                });
                alert.setNegativeButton("Cancel", (view, al)->{
                    closeOptionsMenu();
                });
                alert.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Filter getFilter() {
        return ptFilter;
    }

    private Filter ptFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PT> filteredList = new ArrayList<>();

            //If search is empty, show all users
            if(constraint.toString().isEmpty() || constraint.length() == 0){
                db.collection("PersonalTrainer").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            PT pt = null;
                            ptList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mDocumentID = document.getId();

                                pt = new PT(document.getString("First Name"), document.getString("Last Name"), document.getString("Phone Number"),
                                        document.getString("Certifications"), document.getString("Availability"),
                                        document.getString("Facebook Username"), document.getString("Gender"),
                                        document.getString("Preferred Exercise"), document.getString("Workout Location"),
                                        document.getString("Email"));
                                email = document.getString("Email");
                                ptList.add(pt);
                            }
                            adapter.notifyDataSetChanged();

                            getApplicationContext();
                        }
                    }
                });
            } else{
                db.collection("PersonalTrainer").whereEqualTo("Preferred Exercise", constraint.toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            PT pt = null;
                            ptList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mDocumentID = document.getId();

                                pt = new PT(document.getString("First Name"), document.getString("Last Name"), document.getString("Phone Number"),
                                        document.getString("Certifications"), document.getString("Availability"),
                                        document.getString("Facebook Username"), document.getString("Gender"),
                                        document.getString("Preferred Exercise"), document.getString("Workout Location"),
                                        document.getString("Email"));
                                email = document.getString("Email");
                                ptList.add(pt);
                            }
                            adapter.notifyDataSetChanged();

                            getApplicationContext();
                        }
                    }
                });
            }

            //Return the filtered results
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ptList.clear();
            ptList.addAll((List)results.values);
            adapter.notifyDataSetChanged();
        }
    };

}