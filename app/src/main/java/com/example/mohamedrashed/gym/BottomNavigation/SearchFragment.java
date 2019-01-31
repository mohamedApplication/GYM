package com.example.mohamedrashed.gym.BottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.daimajia.slider.library.SliderLayout;
import com.example.mohamedrashed.gym.R;
import com.example.mohamedrashed.gym.Search.SearchAreas;

public class SearchFragment extends Fragment {

    CardView cardViewSearchByArea;

    ScrollView sv;
    SliderLayout slNews;
    SliderLayout slStore;
    int[] images = {
            R.drawable.search,
            R.drawable.capture};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        cardViewSearchByArea = view.findViewById(R.id.cardview_search_by_area);
        cardViewSearchByArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchAreas.class);
                startActivityForResult(intent,0);
            }
        });





        return view;
    }
}

