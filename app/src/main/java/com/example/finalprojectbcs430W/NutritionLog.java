package com.example.finalprojectbcs430W;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class NutritionLog extends AppCompatActivity {

    private FirebaseFirestore db;

    private EditText editTextFoodName;
    private EditText editTextCalories;
    private EditText editTextCarbohydrate;
    private EditText editTextFoodType;
    private EditText editTextProtein;
    private EditText editTextSaturatedFat;
    private EditText editTextSodium;
    private EditText editTextSugar;


    private Button buttonSearchFoodItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_log);

        //button handling for the search button
        buttonSearchFoodItem = findViewById(R.id.buttonSearchFoodItem);

        db = FirebaseFirestore.getInstance();
        buttonSearchFoodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextFoodName = findViewById(R.id.editTextFoodName);

                editTextCalories = findViewById(R.id.editTextCalories);
                editTextCarbohydrate = findViewById(R.id.editTextCarbohydrate);
                editTextFoodType = findViewById(R.id.editTextFoodType);
                editTextProtein = findViewById(R.id.editTextProtein);
                editTextSaturatedFat = findViewById(R.id.editTextSaturatedFat);
                editTextSodium = findViewById(R.id.editTextSodium);
                editTextSugar = findViewById(R.id.editTextSugar);

                String foodNameInputByUser = editTextFoodName.getText().toString();

                db.collection("NutritionFacts")

                        .whereEqualTo("ItemName", foodNameInputByUser)

                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().getDocuments().size() > 0)
                                    {
                                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                                        editTextFoodName.setText(documentSnapshot.getString("ItemName"));
                                        editTextFoodType.setText(documentSnapshot.getString("FoodType"));


                                        //String CaloriesAsString = String.valueOf(documentSnapshot.getString("Calories"));
                                        //editTextCalories.setText(CaloriesAsString);
                                        editTextCalories.setText(String.valueOf(documentSnapshot.getDouble("Calories")));

                                        //editTextCarbohydrate.setText(documentSnapshot.getString("Carbohydrate"));
                                        editTextCarbohydrate.setText(String.valueOf(documentSnapshot.getDouble("Carbohydrate")));



                                        //editTextProtein.setText(documentSnapshot.getString("Protein"));
                                        editTextProtein.setText(String.valueOf(documentSnapshot.getDouble("Protein")));

                                        //editTextSaturatedFat.setText(documentSnapshot.getString("SaturatedFat"));
                                        editTextSaturatedFat.setText(String.valueOf(documentSnapshot.getDouble("SaturatedFat")));

                                        //editTextSodium.setText(documentSnapshot.getString("Sodium"));
                                        editTextSodium.setText(String.valueOf(documentSnapshot.getDouble("Sodium")));

                                        //editTextSugar.setText(documentSnapshot.getString("Sugar"));
                                        editTextSugar.setText(String.valueOf(documentSnapshot.getDouble("Sugar")));
                                    }

                                    else
                                    {
                                        Log.d("MYDEBUG", "Entered food item name is not on the database", task.getException() );
                                        Toast.makeText(getApplicationContext(), "Entered food item name is NOT on the database", Toast.LENGTH_LONG ).show();

                                        editTextFoodName.setText("");
                                    }

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), " food item  NOT-SUCCESSFULLY read on the database", Toast.LENGTH_LONG ).show();
                                }
                            }
                        });

            }
        });
    }
}