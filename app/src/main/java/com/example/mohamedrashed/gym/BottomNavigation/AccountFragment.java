package com.example.mohamedrashed.gym.BottomNavigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamedrashed.gym.R;

import java.util.Locale;

public class AccountFragment extends Fragment {

    CardView myOrders;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        myOrders = view.findViewById(R.id.frag_account_my_orders);
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 29.9833194, 31.2527202);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                getContext().startActivity(intent);

            }
        });


        return view;
    }

    private void maps(View v) {
//        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 29.9833194, 31.2527202);
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        getContext().startActivity(intent);

        
    }

}
