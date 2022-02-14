package com.example.finalprojectbcs430W;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class NutritionFacts extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_facts);

        pieChart = findViewById(R.id.pieChart);

        //Entries and Percentages to be entered in graph
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(400, "Fats"));
        entries.add(new PieEntry(500, "Proteins"));
        entries.add(new PieEntry(100, "Sodium"));
        entries.add(new PieEntry(1100, "Carbohydrates"));
        entries.add(new PieEntry(2000, "Calories"));
        entries.add(new PieEntry(140, "Sugar"));

        //Initial setup - title specifics, percentages, etc.
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.setCenterText("Daily Nutrition Values");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        //Inclusion and location of the chart legend
        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setDrawInside(false);
        l.setEnabled(true);

        //Adding pieChart entries to the data set for output (and set colors)
        PieDataSet dataSet = new PieDataSet(entries, "CATEGORY");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        //Outputting data
        PieData ntrData = new PieData(dataSet);
        ntrData.setDrawValues(true);
        ntrData.setValueFormatter(new PercentFormatter(pieChart));
        ntrData.setValueTextColor(Color.BLACK);
        ntrData.setValueTextSize(12f);

        pieChart.setData(ntrData);
        pieChart.invalidate();

        //Pie Chart Start Animation
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}