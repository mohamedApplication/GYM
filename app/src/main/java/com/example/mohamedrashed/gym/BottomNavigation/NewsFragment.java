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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamedrashed.gym.FireBaseTools;
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

    ArrayList<LoveListModel> arrayListLikes = new ArrayList();

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


        //ref.child("News").child("a1").setValue(new NewsModel("عنوان الخبر", "محتوى الخبر"));
        //ref.child("News").child("a1").child("Likes").push().setValue(new LoveListModel(user.getUid(), "Amir Mohamed"));


        ref.child("News").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Toast.makeText(getActivity(),"" + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();

                NewsArray.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    //Toast.makeText(getActivity(),"" + shot.child("productPrice").getValue().toString(), Toast.LENGTH_SHORT).show();
                    NewsArray.add(shot.getValue(NewsModel.class));

                    for (DataSnapshot snapshotLikes : shot.child("Likes").getChildren()) {
                        arrayListLikes.add(snapshotLikes.getValue(LoveListModel.class));
                    }

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
        public View getView(final int position, View convertView, ViewGroup parent) {

            // Check if an existing view is being reused, otherwise inflate the view
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.news_list_item, parent, false);
            }
            // Get the {@link Word} object located at this position in the list

            final NewsFragment.NewsModel news = getItem(position);

            TextView txtTitle = view.findViewById(R.id.title);

            txtTitle.setText(news.getTitle());

            TextView txtSubtitle = view.findViewById(R.id.subtitle);

            txtSubtitle.setText(news.getSubtitle());

            final ImageView imageViewLoveLike = view.findViewById(R.id.news_love_like);

            if (arrayListLikes.get(position).getUID().equals(user.getUid())) {
                imageViewLoveLike.setImageResource(R.drawable.ic_like_on);
            }

            imageViewLoveLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (arrayListLikes.get(position).getUID().equals(user.getUid())) {
//                        imageViewLoveLike.setImageResource(R.drawable.ic_like_off);
//                    }





                }
            });

            return view;

        }
    }

    public static class LoveListModel {
        private String UID, Name;

        public LoveListModel() {

        }

        public LoveListModel(String UID, String name) {
            this.UID = UID;
            Name = name;
        }

        public String getUID() {
            return UID;
        }

        public void setUID(String UID) {
            this.UID = UID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }
    }

}

