package com.example.mohamedrashed.gym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mohamedrashed.gym.Maps.MapsActivity;

public class GymInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_info);
    }

    public void openMaps(View view) {
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
    }
}
