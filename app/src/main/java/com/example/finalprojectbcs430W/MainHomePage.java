package com.example.finalprojectbcs430W;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.facebookOut:
                Intent i = new Intent(MainHomePage.this, Facebook.class);
                startActivity(i);

                return true;

            case R.id.logout:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Sign Out");
                alert.setMessage("Are you sure you would like to sign out?");
                alert.setPositiveButton("Confirm", (v,a)->{
                    Intent intent = new Intent(MainHomePage.this, MainActivity.class);
                    startActivity(intent);
                    Toast logout = Toast.makeText(MainHomePage.this, "Logout Successful", Toast.LENGTH_SHORT);
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
    
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card1:{
                Intent myIntent = new Intent(MainHomePage.this, WorkoutLog.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card2:{
                Intent myIntent = new Intent(MainHomePage.this, NutritionLog.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card3:{
                Intent myIntent = new Intent(MainHomePage.this, Calendar.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card4:{
                Intent myIntent = new Intent(MainHomePage.this, NutritionFacts.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card5:{
                Intent myIntent = new Intent(MainHomePage.this, FindFriend.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card6:{
                Intent myIntent = new Intent(MainHomePage.this, FindPersonalTrainer.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
        }
    }

}
