package com.example.mohamedrashed.gym.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamedrashed.gym.R;

public class SearchFilter extends AppCompatActivity {

    // Variables returns from SearchArea Class
    public static String areaNameReturned = "اختر منطقة", areaRefReturned = "Maadi";

    // Variables from spinners
    public static String genderSelected = "Men";

    // Component Deceleration
    TextView txtAreaName;
    Spinner spinnerGender;

    public static int spinnerPriceFilter = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        // Decelerations
        txtAreaName = findViewById(R.id.txt_area_filter);

        spinnerGender = findViewById(R.id.spinner_filter_gender);


        final Spinner spinner = findViewById(R.id.spinner_filter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.travelReasons, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int item = spinner.getSelectedItemPosition();
                switch (item) {
                    case 0:
                        spinnerPriceFilter = 200;
                        break;
                    case 1:
                        spinnerPriceFilter = 400;
                        break;
                    case 2:
                        spinnerPriceFilter = 600;
                        break;
                    case 3:
                        spinnerPriceFilter = 800;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,
                R.array.spinner_gender, R.layout.support_simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapterGender);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int Item = spinnerGender.getSelectedItemPosition();
                switch (Item) {
                    case 0 :
                        genderSelected = "Men";
                        break;
                    case 1 :
                        genderSelected = "Women";
                        break;
                    case 2 :
                        genderSelected = "Mix";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        txtAreaName.setText(areaNameReturned);
        Toast.makeText(this, "Area Name : " + areaNameReturned + " | Area Ref : " + areaRefReturned, Toast.LENGTH_LONG).show();
    }

    public void openSearchGyms(View view) {
        startActivity(new Intent(getApplicationContext(), SearchGyms.class));
    }

    public void selectArea(View view) {
        startActivity(new Intent(getApplicationContext(), SearchAreas.class));
    }


}
