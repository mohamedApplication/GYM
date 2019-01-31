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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamedrashed.gym.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private ProgressBar progressBar ;


    ArrayList<NewsFragment.NewsModel> NewsArray = new ArrayList<>();

    NewsAdapter newsAdapter;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        progressBar = view.findViewById(R.id.fragn_progress_bar);


        newsAdapter = new NewsFragment.NewsAdapter(getContext(), NewsArray);

        final ListView NewslistView = view.findViewById(R.id.fragn_news_list);

        NewslistView.setAdapter(newsAdapter);

        //ref.child("News").push().setValue(new NewsModel("عنوان الخبر", "محتوى الخبر"));
        //ref.child("News").child("-LX_1rCgU8UYsADBvTui").child("Likes").child("").setValue("Amir Mohammed");


        ref.child("News").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Toast.makeText(getActivity(),"" + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();

                NewsArray.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    //Toast.makeText(getActivity(),"" + shot.child("productPrice").getValue().toString(), Toast.LENGTH_SHORT).show();
                    NewsArray.add(shot.getValue(NewsModel.class));

                }
                newsAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

                //progressBar.setVisibility(View.GONE);
                //Collections.sort(productsArray, SearchAreas.AreasModel.AreasSort);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

    public static class NewsModel {

        private String Title, Subtitle;


        public NewsModel(String title, String subtitle) {
            this.Title = title;
            this.Subtitle = subtitle;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            this.Title = title;
        }

        public String getSubtitle() {
            return Subtitle;
        }

        public void setSubtitle(String subtitle) { Subtitle= subtitle;
        }

        public NewsModel() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

    }

    class NewsAdapter extends ArrayAdapter<NewsModel> {


        public NewsAdapter(Context context, ArrayList<NewsModel> prosucts) {
            super(context, 0, prosucts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Check if an existing view is being reused, otherwise inflate the view
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.news_list_item, parent, false);
            }
            // Get the {@link Word} object located at this position in the list

            NewsFragment.NewsModel news = getItem(position);

            TextView txtTitle = view.findViewById(R.id.title);

            txtTitle.setText(news.getTitle());

            TextView txtSubtitle = view.findViewById(R.id.subtitle);

            txtSubtitle.setText(news.getSubtitle());

            final ImageView imageViewLoveLike = view.findViewById(R.id.news_love_like);

            imageViewLoveLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (imageViewLoveLike.getResources().equals(getResources().getDrawable(R.drawable.ic_like_off))) {
                        Toast.makeText(getActivity(), "True", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(), "" + imageViewLoveLike.getResources(), Toast.LENGTH_SHORT).show();
                }
            });

            return view;

        }
    }
}

