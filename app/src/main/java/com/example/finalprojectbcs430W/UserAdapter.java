package com.example.finalprojectbcs430W;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<User> userList;
    private ArrayList<String> emailList = new ArrayList<String>();
    //private Context mContext;
    String recipientEmail, tempEmail;
    int i = 0;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public void RecyclerViewAdapter(Context context){
            context = context;
        }

        public TextView textViewFirstName, textViewLastName, textViewGender, textViewWeight, textViewHeightFeet, textViewHeightInches, textViewPrefDiet, textViewPrefWorkoutTime, textViewPhoneNum, textViewFacebookUser, textViewGymLocation, textViewEmail;
        Button connect;

        public MyViewHolder(View view) {
            super(view);
            //Intent intent = ((FindFriend) context).getIntent();
            ///Bundle extras = intent.getExtras();
            //ArrayList<String> emailList = extras.getStringArrayList("emailList");
            textViewFirstName = (TextView) view.findViewById(R.id.userFirstNameTextView);
            textViewLastName = (TextView) view.findViewById(R.id.userLastNameTextView);
            textViewGender=(TextView) view.findViewById(R.id.userGenderTextView);
            textViewWeight = (TextView) view.findViewById(R.id.userWeightTextView);
            textViewHeightInches = (TextView) view.findViewById(R.id.userHeightInchesTextView);
            textViewHeightFeet = (TextView) view.findViewById(R.id.userHeightFeettextView);
            textViewPrefDiet = (TextView) view.findViewById(R.id.userDietPrefTextView);
            textViewPrefWorkoutTime = (TextView) view.findViewById(R.id.userPrefWorkoutTimeTextView);
            textViewPhoneNum = (TextView) view.findViewById(R.id.userPhoneNumTextView);
            textViewFacebookUser = (TextView) view.findViewById(R.id.userFacebookUserTextView);
            textViewGymLocation = (TextView) view.findViewById(R.id.userGymLocationTextView);
            textViewEmail = (TextView) view.findViewById(R.id.userEmailTextView);
            connect = (Button) view.findViewById(R.id.connectButton);
            //emailList.clear();





            connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    recipientEmail = emailList.get(i-1);
                    Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:?subject=" + "Agile Fitness"+ "&body=" + "Hello! I saw your profile on AgileFitness and I would like to connect with you!" + "&to=" + recipientEmail + "");

                    mailIntent.setData(data);
                    v.getContext().startActivity(Intent.createChooser(mailIntent, "Send mail..."));
                    recipientEmail = "";

                }
            });

        }

    }
    public UserAdapter(List<User> userList){
        this.userList= userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
        View  userView = LayoutInflater.from(group.getContext())
                .inflate(R.layout.user_recyclerview_item, group,false);
        return new MyViewHolder(userView);






    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        User user = userList.get(position);


        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String userGender = user.getGender();
        String userWeight = user.getWeight();
        String userHeightFeet = user.getHeightFeet();
        String userHeightInches = user.getHeightInches();
        String userPhoneNum = user.getPhoneNum();
        String userFacebookUser = user.getFacebookUser();
        String userDietPref = user.getDietPref();
        String userPrefWorkout = user.getPrefWorkoutTime();
        String userGymLocation = user.getGymLocation();
        String userEmail = user.getEmail();
        emailList.add(user.getEmail());



        holder.textViewFirstName.setText(firstName);
        holder.textViewLastName.setText(lastName);
        holder.textViewGender.setText(userGender);
        holder.textViewWeight.setText(userWeight);
        holder.textViewHeightInches.setText(userHeightInches);
        holder.textViewHeightFeet.setText(userHeightFeet);
        holder.textViewPhoneNum.setText(userPhoneNum);
        holder.textViewFacebookUser.setText(userFacebookUser);
        holder.textViewPrefDiet.setText(userDietPref);
        holder.textViewPrefWorkoutTime.setText(userPrefWorkout);
        holder.textViewGymLocation.setText(userGymLocation);
        holder.textViewEmail.setText(userEmail);
        i=position;
        //emailList.add(userEmail);




    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setData(List<User> userList)
    {
        this.userList = userList;
        notifyDataSetChanged();
    }
}
