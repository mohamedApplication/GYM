package com.example.mohamedrashed.gym.BottomNavigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamedrashed.gym.FireBaseTools;
import com.example.mohamedrashed.gym.R;
import com.example.mohamedrashed.gym.Search.SearchAreas;
import com.example.mohamedrashed.gym.Search.SearchGyms;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StoreFragment extends Fragment {

    ListView listView;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    ArrayList<SearchAreas.AreasModel> areas = new ArrayList<SearchAreas.AreasModel>();

    StoreFragment.AreasSearchAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_store, container, false);

        adapter = new StoreFragment.AreasSearchAdapter(getActivity(), areas);
        final ListView listView = view.findViewById(R.id.frags_list_view);
        listView.setAdapter(adapter);
        ref.child("Areas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                areas.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()) {

                    StoreFragment.AreasModel areasModel = new
                            StoreFragment.AreasModel(shot.child("areaName").getValue(String.class),
                            shot.child("areaRef").getValue(String.class),
                            shot.child("visitors").getChildrenCount());

                    areas.add(areasModel);
                    adapter.notifyDataSetChanged();
                }
                Collections.sort(areas, SearchAreas.AreasModel.AreasSort);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

    public static class AreasModel extends SearchAreas.AreasModel {

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

        public static Comparator<SearchAreas.AreasModel> AreasSort = new Comparator<SearchAreas.AreasModel>() {

            @Override
            public int compare(SearchAreas.AreasModel areasModel1, SearchAreas.AreasModel areasModel2) {
                long area1 = areasModel1.getVisitor();
                long area2 = areasModel2.getVisitor();

                int result = Integer.parseInt("" + (area2 - area1));
                return result;
            }
        };
    }

    class AreasSearchAdapter extends ArrayAdapter<SearchAreas.AreasModel> implements Filterable {


        public AreasSearchAdapter(Context context, ArrayList<SearchAreas.AreasModel> areas) {
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
            final SearchAreas.AreasModel areas = getItem(position);

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
                    Toast.makeText(getActivity(), "" + areas.getAreaRef(), Toast.LENGTH_SHORT).show();
                    //FireBaseTools.visits("Areas", areas.getAreaRef());
                }
            });

            return view;
        }


    }


}
