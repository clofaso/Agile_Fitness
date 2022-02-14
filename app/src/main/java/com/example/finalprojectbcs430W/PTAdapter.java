package com.example.finalprojectbcs430W;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PTAdapter extends RecyclerView.Adapter<PTAdapter.MyViewHolder>{
    private List<PT> ptList;
    String recipientEmail;
    private ArrayList<String> emailList = new ArrayList<String>();
    int i = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder{


        public TextView textViewPTFirstName, textViewPTLastName, textViewPTFacebook, textViewPTCerti, textViewPTGender, textViewPTPhone, textViewPTPrefEx, textViewPTAvail, textViewPTLocation, textViewUserEmail;
        Button connect;

        public MyViewHolder(View view) {
            super(view);
            textViewPTFirstName = (TextView) view.findViewById(R.id.userFirstNameTextView);
            textViewPTLastName = (TextView) view.findViewById(R.id.userGenderTextView);
            textViewPTFacebook = (TextView) view.findViewById(R.id.userPrefWorkoutTimeTextView);
            textViewPTPrefEx = (TextView) view.findViewById(R.id.userPhoneNumTextView);
            textViewPTPhone = (TextView) view.findViewById(R.id.userHeightFeettextView);
            textViewPTGender = (TextView) view.findViewById(R.id.userHeightInchesTextView);
            textViewPTCerti = (TextView) view.findViewById(R.id.userDietPrefTextView);
            textViewPTAvail = (TextView) view.findViewById(R.id.userWeightTextView);
            textViewUserEmail = (TextView) view.findViewById(R.id.userEmailTextView);

            textViewPTLocation = (TextView) view.findViewById(R.id.userPreferedWorkoutLocation);

            connect = (Button) view.findViewById(R.id.connectButton);

            connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recipientEmail = emailList.get(i-1);
                    Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:?subject=" + "Agile Fitness"+ "&body=" + "Hello! I saw your profile on AgileFitness and I would like to connect with you!" + "&to=" + recipientEmail + "");
                    mailIntent.setData(data);
                    v.getContext().startActivity(Intent.createChooser(mailIntent, "Send mail..."));
                    recipientEmail="";
                }

            });
        }
    }


    public PTAdapter(List<PT> ptList){
        this.ptList= ptList;
    }

    @Override
    public PTAdapter.MyViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
        View  ptView = LayoutInflater.from(group.getContext())
                .inflate(R.layout.pt_recyclerview_item, group,false);
        return new PTAdapter.MyViewHolder(ptView);
    }

    @Override
    public void onBindViewHolder(PTAdapter.MyViewHolder holder, int position) {
        PT pt = ptList.get(position);

        String ptFirstName = pt.getFirstName();
        String ptLastName = pt.getLastName();
        String ptPhoneNum = pt.getPhoneNum();
        String ptAvailability = pt.getAvailability();
        String ptCertification = pt.getCert();
        String ptGender = pt.getGender();
        String ptPrefEx = pt.getPrefEx();
        String ptFacebook = pt.getFacebook();
        String ptWorkoutLocation = pt.getWorkoutLocation();
        String ptEmail = pt.getEmail();
        emailList.add(pt.getEmail());

        holder.textViewPTFirstName.setText(ptFirstName);
        holder.textViewPTLastName.setText(ptLastName);
        holder.textViewPTFacebook.setText(ptFacebook);
        holder.textViewPTAvail.setText(ptAvailability);
        holder.textViewPTCerti.setText(ptCertification);
        holder.textViewPTGender.setText(ptGender);
        holder.textViewPTPhone.setText(ptPhoneNum);
        holder.textViewPTPrefEx.setText(ptPrefEx);
        holder.textViewPTLocation.setText(ptWorkoutLocation);
        holder.textViewUserEmail.setText(ptEmail);
        i= position;


    }

    @Override
    public int getItemCount() {
        return ptList.size();
    }

    public void setData(List<PT> ptList)
    {
        this.ptList = ptList;
        notifyDataSetChanged();
    }
}