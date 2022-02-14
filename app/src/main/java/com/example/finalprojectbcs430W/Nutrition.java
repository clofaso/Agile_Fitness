package com.example.finalprojectbcs430W;

public class Nutrition {

    private double Calories;
    private double Carbohydrates;
    private String FoodType;
    private String ItemName;
    private double Protein;
    private double SaturatedFat;
    private double Sodium;
    private double Sugar;

    public Nutrition(double cal, double carb, String f_type, String I_name, double protein, double sat_fat, double sodium, double sugar)
    {
        this.Calories=cal;
        this.Carbohydrates = carb;
        this.FoodType = f_type;
        this.ItemName = I_name;
        this.Protein = protein;
        this.SaturatedFat = sat_fat;
        this.Sodium = sodium;
        this.Sugar = sugar;
    }

    public double getCalories() {return Calories; }
    public void setCalories(double c) {this.Calories = c; }


    public double getCarbohydrates() {return Carbohydrates; }
    public void setCarbohydrates(double carb) {this.Carbohydrates = carb; }


    public String getFoodType() {return FoodType; }
    public void setFoodType(String  ft) {this.FoodType = ft; }

    public String getItemName() {return ItemName; }
    public void setItemName(String  iN) {this.ItemName = iN; }

    public double getProtein() {return Protein; }
    public void setProtein(double prot) {this.Protein = prot; }

    public double getSaturatedFat() {return SaturatedFat; }
    public void setSaturatedFat(double s_fat) {this.SaturatedFat = s_fat; }

    public double getSodium() {return Sodium; }
    public void setSodium(double s) {this.Sodium = s; }

    public double getSugar() {return Sugar; }
    public void setSugar(double sugar) {this.Sugar = sugar; }



}
