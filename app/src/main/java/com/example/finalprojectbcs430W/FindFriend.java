package com.example.finalprojectbcs430W;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FindFriend extends AppCompatActivity  implements Filterable {

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    String mDocumentID;
    String email;
    Bundle bundle = new Bundle();

    private FirebaseFirestore db;

    public static final int SHOW_SUB_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFindFriend);
        adapter = new UserAdapter(userList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();

        retrieveData();
    }

    private void retrieveData()
    {
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            User user = null;
                            String email;
                            userList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()){
                                mDocumentID = document.getId();

                                user = new User(
                                        document.getString("First Name"),
                                        document.getString("Last Name"),
                                        document.getString("Weight"),
                                        document.getString("Phone Number"),
                                        document.getString("Gender"),
                                        document.getString("Preferred Workout Time"),
                                        document.getString("Diet Preference"),
                                        document.getString("Height (Feet)"),
                                        document.getString("Height (Inches)"),
                                        document.getString("Facebook Username"),
                                        document.getString("Gym Location"),
                                        document.getString("Email"));

                                userList.add(user);

                            }
                            adapter.notifyDataSetChanged();
                            getApplicationContext();
                        }
                    }
                });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recyclerview_menu, menu);

        //Search Feature for Find Friend
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

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Filter getFilter() {
        return userFilter;
    }

    private Filter userFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<User> filteredList = new ArrayList<>();

            //If search is empty, show all users
            if(constraint.toString().isEmpty() || constraint.length() == 0){
                db.collection("user").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            User user = null;
                            userList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mDocumentID = document.getId();

                                user = new User(document.getString("First Name"), document.getString("Last Name"), document.getString("Diet Preference"),
                                        document.getString("Facebook Username"), document.getString("Phone Number"), document.getString("Preferred Workout Time"),
                                        document.getString("Weight"), document.getString("Height (Feet)"), document.getString("Height (Inches)"),
                                        document.getString("Gender"), document.getString("Gym Location"), document.getString("Email"));
                                email = document.getString("Email");
                                userList.add(user);
                            }
                            adapter.notifyDataSetChanged();

                            getApplicationContext();
                        }
                    }
                });
            } else{
                db.collection("user").whereEqualTo("Preferred Workout Time", constraint.toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            User user = null;
                            userList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mDocumentID = document.getId();

                                user = new User(document.getString("First Name"), document.getString("Last Name"), document.getString("Diet Preference"),
                                        document.getString("Facebook Username"), document.getString("Phone Number"), document.getString("Preferred Workout Time"),
                                        document.getString("Weight"), document.getString("Height (Feet)"), document.getString("Height (Inches)"),
                                        document.getString("Gender"), document.getString("Gym Location"), document.getString("Email"));
                                email = document.getString("Email");
                                userList.add(user);
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
            userList.clear();
            userList.addAll((List)results.values);
            adapter.notifyDataSetChanged();
        }
    };

}
