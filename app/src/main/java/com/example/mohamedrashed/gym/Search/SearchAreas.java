package com.example.mohamedrashed.gym.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamedrashed.gym.FireBaseTools;
import com.example.mohamedrashed.gym.R;
import com.example.mohamedrashed.gym.Search.SearchGyms;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchAreas extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    ArrayList<AreasModel> areas = new ArrayList<AreasModel>();

    AreasSearchAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_areas);

        adapter = new AreasSearchAdapter(this, areas);
        final ListView listView = findViewById(R.id.areas_search_list_view);
        listView.setAdapter(adapter);
        ref.child("Areas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                areas.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()) {

                    AreasModel areasModel = new
                            AreasModel(shot.child("areaName").getValue(String.class),
                            shot.child("areaRef").getValue(String.class),
                            shot.child("visitors").getChildrenCount());

                    areas.add(areasModel);
                    adapter.notifyDataSetChanged();
                }
                Collections.sort(areas, AreasModel.AreasSort);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SearchView searchView = findViewById(R.id.sa_search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

//                Query fireQuery = ref.child("Areas").orderByChild("areaName").equalTo(query);
//                fireQuery.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.getValue() == null) {
//                            Toast.makeText(SearchAreas.this, "هذه المنطقة غير متوفرة حاليا", Toast.LENGTH_SHORT).show();
//                        } else {
//                            ArrayList<AreasModel> areasModels = new ArrayList<>();
//                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                                areasModels.add(snapshot.getValue(AreasModel.class));
//                                adapter = new AreasSearchAdapter(SearchAreas.this, areasModels);
//                                //adapter.notifyDataSetChanged();
//                                listView.setAdapter(adapter);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                int textLength = s.length();
                ArrayList<AreasModel> tempArrayList = new ArrayList<>();
                for (AreasModel c : areas) {
                    if (textLength <= c.getAreaName().length()) {
                        if (c.getAreaName().toLowerCase().contains(s.toLowerCase())) {
                            tempArrayList.add(c);

                        }
                    }
                }
                adapter = new AreasSearchAdapter(getApplicationContext(), tempArrayList);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);


                return true;
            }
        });


    }


    public static class AreasModel {

        private String AreaName, AreaRef;
        private long visitor;

        public AreasModel(String areaName, String areaRef, long visitor) {
            AreaName = areaName;
            AreaRef = areaRef;
            this.visitor = visitor;
        }

        public AreasModel(String areaName, String areaRef) {
            AreaName = areaName;
            AreaRef = areaRef;
        }

        public String getAreaName() {
            return AreaName;
        }

        public void setAreaName(String areaName) {
            AreaName = areaName;
        }

        public String getAreaRef() {
            return AreaRef;
        }

        public void setAreaRef(String areaRef) {
            AreaRef = areaRef;
        }

        public long getVisitor() {
            return visitor;
        }

        public void setVisitor(long visitor) {
            this.visitor = visitor;
        }

        public AreasModel() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public static Comparator<AreasModel> AreasSort = new Comparator<AreasModel>() {

            @Override
            public int compare(AreasModel areasModel1, AreasModel areasModel2) {
                long area1 = areasModel1.getVisitor();
                long area2 = areasModel2.getVisitor();

                int result = Integer.parseInt("" + (area2 - area1));
                return result;
            }
        };
    }

    class AreasSearchAdapter extends ArrayAdapter<AreasModel> implements Filterable {


        public AreasSearchAdapter(Context context, ArrayList<AreasModel> areas) {
            super(context, 0, areas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Check if an existing view is being reused, otherwise inflate the view
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.areas_search_model, parent, false);
            }

            // Get the {@link Word} object located at this position in the list
            final AreasModel areas = getItem(position);

            TextView txtGymName = view.findViewById(R.id.asm_area_name);

            txtGymName.setText(areas.getAreaName());

            TextView txtVisitorsCount = view.findViewById(R.id.asm_area_visitors);

            txtVisitorsCount.setText(String.valueOf(areas.getVisitor()));

            txtGymName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(SearchAreas.this, "" + areas.getAreaRef(), Toast.LENGTH_SHORT).show();
                    FireBaseTools.visits("Areas", areas.getAreaRef());
                    //TestAreasToGyms.childFromSearchArea = areas.getAreaRef();
                    SearchGyms.gymsLocationRef = areas.getAreaRef();
                    startActivity(new Intent(getContext(), SearchGyms.class));
                }
            });

            txtVisitorsCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(SearchAreas.this, "" + areas.getAreaRef(), Toast.LENGTH_SHORT).show();
                    //FireBaseTools.visits("Areas", areas.getAreaRef());
                }
            });

            return view;
        }


    }

}
