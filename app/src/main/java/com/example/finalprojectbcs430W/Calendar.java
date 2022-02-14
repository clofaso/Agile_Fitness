package com.example.finalprojectbcs430W;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity {

    private CalendarView calendar;
    private mySQLiteDBHandler dbHandler;
    private EditText editText;
    private String selectedDate;
    private SQLiteDatabase sqLiteDatabase;
    //code partially taken from https://programmerworld.co/android/how-to-create-a-custom-calendar-app-to-store-reminders-and-events-using-sqlite-database-in-android-studio/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = findViewById(R.id.calendarView);
        editText = findViewById(R.id.editTextEvent);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
                ReadDatabase(view);
            }
        });

        try{
            dbHandler = new mySQLiteDBHandler(this, "CalendarDatabase", null, 1);
            sqLiteDatabase = dbHandler.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE EventCalendar(Date TEXT, Event TEXT)");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void InsertDatabase(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", selectedDate);
        contentValues.put("Event", editText.getText().toString());
        sqLiteDatabase.insert("EventCalendar", null, contentValues);
    }

    public void ReadDatabase(View view){
        String query = "Select Event from EventCalendar where Date = " + selectedDate;
        try{
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            cursor.moveToFirst();
            editText.setText(cursor.getString(0));
        }
        catch (Exception e){
            e.printStackTrace();
            editText.setText("");
        }
    }

}