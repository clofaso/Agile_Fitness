package com.example.finalprojectbcs430W;

import android.widget.Button;

public class User {
    private String firstname, lastname, dietPref, facebookUser, phoneNum, prefWorkoutTime, weight, heightFeet, heightInches, gender, gymLoc, email;
    private Button connect;

    public User() {

    }

    public User(String firstname, String lastname, String dietPref, String facebookUser, String phoneNum, String prefWorkoutTime, String weight, String heightFeet, String heightInches, String gender, String gymLocation, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.dietPref = dietPref;
        this.facebookUser = facebookUser;
        this.phoneNum = phoneNum;
        this.prefWorkoutTime = prefWorkoutTime;
        this.weight = weight;
        this.gender = gender;
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;
        this.gymLoc = gymLocation;
        this.email = email;
    }

    public String getFirstName(){return firstname;}
    public void  setFirstName(String fn){this.firstname=fn;}

    public String getLastName(){return lastname;}
    public void  setLastName(String ln){this.lastname=ln;}

    public String getDietPref(){return dietPref;}
    public void  setDietPref(String dp){this.dietPref=dp;}

    public String getPhoneNum(){return phoneNum;}
    public void  setPhoneNum(String pn){this.phoneNum=pn;}

    public String getWeight(){return weight;}
    public void  setWeight(String w){this.weight=w;}

    public String getGender(){return gender;}
    public void  setGender(String g){this.gender=g;}

    public String getFacebookUser(){return facebookUser;}
    public void  setFacebookUser(String fu){this.facebookUser=fu;}

    public String getPrefWorkoutTime(){return prefWorkoutTime;}
    public void  setPrefWorkoutTime(String pwt){this.prefWorkoutTime=pwt;}

    public String getHeightFeet(){return heightFeet;}
    public void  setHeightFeet(String hf){this.heightFeet=hf;}

    public String getHeightInches(){return heightInches;}
    public void  setHeightInches(String hi){this.heightInches=hi;}

    public String getGymLocation(){return gymLoc;}
    public void setGymLocation(String gL){this.gymLoc = gL;}

    public String getEmail(){return email;}
    public void  setEmail(String e){this.email=e;}

}
