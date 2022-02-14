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

public class WorkoutLog extends AppCompatActivity {

    private FirebaseFirestore db;

    private EditText editTextWorkOutName;
    private EditText editTextWorkoutCategory;
    private EditText editTextCaloriesBurned;
    private EditText editTextIntensityLevel;
    private EditText editTextAverageHeartRate;
    private EditText editTextDurationinMinutes;
    private EditText editTextGymEquipmentRequired;
    private EditText editTextRiskLevel;
    private EditText editTextSpotterNeeded;

    private Button buttonWorkoutSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);



        //button handling for the search workout button
        buttonWorkoutSearch = findViewById(R.id.buttonWorkoutSearch);

        db =  FirebaseFirestore.getInstance();
        buttonWorkoutSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextWorkOutName = findViewById(R.id.editTextWorkOutName);
                editTextWorkoutCategory = findViewById(R.id.editTextWorkoutCategory);
                editTextCaloriesBurned = findViewById(R.id.editTextCaloriesBurned);
                editTextIntensityLevel = findViewById(R.id.editTextIntensityLevel);
                editTextAverageHeartRate = findViewById(R.id.editTextAverageHeartRate);
                editTextDurationinMinutes = findViewById(R.id.editTextDurationinMinutes);
                editTextGymEquipmentRequired = findViewById(R.id.editTextGymEquipmentRequired);
                editTextRiskLevel  = findViewById(R.id.editTextRiskLevel);
                editTextSpotterNeeded = findViewById(R.id.editTextSpotterNeeded);

                String workoutInputByUser = editTextWorkOutName.getText().toString();

                db.collection("Workouts")
                        .whereEqualTo("WorkoutType", workoutInputByUser)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().getDocuments().size()>0)
                                    {
                                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                                        editTextWorkoutCategory.setText(documentSnapshot.getString("WorkoutCategory"));
                                        editTextCaloriesBurned.setText(String.valueOf(documentSnapshot.getDouble("CaloriesBurned")));
                                        editTextIntensityLevel.setText(documentSnapshot.getString("IntesityLevel"));
                                        editTextAverageHeartRate.setText(String.valueOf(documentSnapshot.getDouble("AverageHeartRate")));
                                        editTextDurationinMinutes.setText(String.valueOf(documentSnapshot.getDouble("DurationInMinutes")));
                                        editTextGymEquipmentRequired.setText(documentSnapshot.getString("GymEquipmentRequired"));
                                        editTextRiskLevel.setText(documentSnapshot.getString("RiskLevel"));
                                        editTextSpotterNeeded.setText(documentSnapshot.getString("SpotterNeeded"));


                                    }

                                    else
                                    {
                                        Log.d("MYDEBUG", "Entered Workout name is not on the database", task.getException());
                                        Toast.makeText(getApplicationContext(), "Entered Workout name is NOT on the database", Toast.LENGTH_LONG).show();

                                        editTextWorkOutName.setText("");
                                    }

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "workout NOT-SUCCESSFULLY read on the datbase", Toast.LENGTH_LONG);
                                }
                            }
                        });

            }
        });

    }
}