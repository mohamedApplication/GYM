package com.example.mohamedrashed.gym.BottomNavigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamedrashed.gym.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoreFragment extends Fragment {

    ProgressBar progressBar ;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    ArrayList<ProsuctsMoel> productsArray = new ArrayList<ProsuctsMoel>();

    ProductsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_store, container, false);

        progressBar = view.findViewById(R.id.frags_progress_bar);

        adapter = new ProductsAdapter(getContext(), productsArray);

        final GridView listView = view.findViewById(R.id.frags_list_view);

        listView.setAdapter(adapter);

        ref.child("Store").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Toast.makeText(getActivity(),"" + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();

                productsArray.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    ProsuctsMoel prosuctsMoel = new ProsuctsMoel(shot.child("productName").getValue().toString(),
                            shot.child("productPrice").getValue().toString());
                    //Toast.makeText(getActivity(),"" + shot.child("productPrice").getValue().toString(), Toast.LENGTH_SHORT).show();
                    productsArray.add(prosuctsMoel);

                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                //Collections.sort(productsArray, SearchAreas.AreasModel.AreasSort);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

    public static class ProsuctsMoel {

        private String productName, ProductPrice;

        public ProsuctsMoel(String prouctName, String productPrice) {
            this.productName = prouctName;
            ProductPrice = productPrice;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductPrice() {
            return ProductPrice;
        }

        public void setProductPrice(String productPrice) {
            ProductPrice = productPrice;
        }

        public ProsuctsMoel() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

    }

    class ProductsAdapter extends ArrayAdapter<ProsuctsMoel> {


        public ProductsAdapter(Context context, ArrayList<ProsuctsMoel> prosucts) {
            super(context, 0, prosucts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Check if an existing view is being reused, otherwise inflate the view
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.fragment_store_model, parent, false);
            }

            // Get the {@link Word} object located at this position in the list
            ProsuctsMoel products = getItem(position);

            TextView txtProductName = view.findViewById(R.id.fragsm_txt_product_name);

            txtProductName.setText(products.getProductName());

            TextView txtProductPrice = view.findViewById(R.id.fragsm_txt_product_price);

            txtProductPrice.setText(products.getProductPrice());

            return view;
        }


    }


}
