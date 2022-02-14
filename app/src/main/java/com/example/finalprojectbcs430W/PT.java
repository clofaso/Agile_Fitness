package com.example.finalprojectbcs430W;

import android.widget.Button;

public class PT {
    private String firstName, lastName, phoneNum, certification, availability, facebookUsername, gender, prefEx, workoutLocation, email;
    private Button connect;
    public PT() {

    }

    public PT(String firstName, String lastName, String phoneNum, String certification, String availability, String facebookUsername, String gender, String prefEx, String workoutLocation, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.certification = certification;
        this.availability = availability;
        this.facebookUsername = facebookUsername;
        this.gender = gender;
        this.prefEx = prefEx;
        this.workoutLocation = workoutLocation;
        this.email = email;

    }

    public String getFirstName(){return firstName;}
    public void  setFirstName(String fn){this.firstName=fn;}

    public String getLastName(){return lastName;}
    public void  setLastName(String ln){this.lastName=ln;}

    public String getPhoneNum(){return phoneNum;}
    public void  setPhoneNum(String pn){this.phoneNum=pn;}

    public String getCert(){return certification;}
    public void  setCert(String c){this.certification=c;}

    public String getAvailability(){return availability;}
    public void  setAvailability(String a){this.availability=a;}

    public String getFacebook(){return facebookUsername;}
    public void  setFacebook(String fu){this.facebookUsername=fu;}

    public String getGender(){return gender;}
    public void  setGender(String g){this.gender=g;}

    public String getPrefEx(){return prefEx;}
    public void  setPrefEx(String pe){this.prefEx=pe;}

    public String getWorkoutLocation(){return workoutLocation;}
    public void  setworkoutLocation(String wl){this.workoutLocation=wl;}

    public String getEmail(){return email;}
    public void setEMail(String e){this.email = e;}
}
