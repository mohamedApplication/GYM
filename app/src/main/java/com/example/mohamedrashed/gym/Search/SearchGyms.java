package com.example.mohamedrashed.gym.Search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mohamedrashed.gym.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchGyms extends AppCompatActivity {

    public static String gymsLocationRef = "Maadi";

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    ArrayList<GymsModel> gyms = new ArrayList<>();

    GymSearchAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gyms);

        adapter = new GymSearchAdapter(this, gyms);

        ListView listView = (ListView) findViewById(R.id.GymSearchListView);

        listView.setAdapter(adapter);

        GymsModel Gyms = new GymsModel("استرونج ماشين", "سعر الاشتراك : 200 جنية");

        ref.child("Gyms").child(gymsLocationRef).push().setValue(Gyms);


        ref.child("Gyms").child(gymsLocationRef).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gyms.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    gyms.add(dataSnapshot1.getValue(GymsModel.class));
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static class GymsModel {
        private String gymName, gymPrice;

        public GymsModel() {

        }


        GymsModel(String gymName, String gymPrice) {
            this.gymName = gymName;
            this.gymPrice = gymPrice;
        }

        public String getGymName() {
            return gymName;
        }

        public void setGymName(String gymName) {
            this.gymName = gymName;
        }

        public String getGymPrice() {
            return gymPrice;
        }

        public void setGymPrice(String gymPrice) {
            this.gymPrice = gymPrice;
        }
    }

    class GymSearchAdapter extends ArrayAdapter<GymsModel> {


        public GymSearchAdapter(Context context, ArrayList<GymsModel> users) {
            super(context, 0, users);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Check if an existing view is being reused, otherwise inflate the view
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.gyms_search_model, parent, false);
            }

            // Get the {@link Word} object located at this position in the list
            GymsModel gyms = getItem(position);

            TextView txtGymName = view.findViewById(R.id.gsm_gym_name);

            txtGymName.setText(gyms.getGymName());

            TextView txtGymPrice = view.findViewById(R.id.gsm_gym_price);

            txtGymPrice.setText(gyms.getGymPrice());

            return view;
        }

    }
}

