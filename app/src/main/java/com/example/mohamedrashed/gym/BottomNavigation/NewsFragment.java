package com.example.mohamedrashed.gym.BottomNavigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mohamedrashed.gym.R;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    ArrayList<NewsFragment.NewsModel> NewsArray = new ArrayList<>();

    NewsAdapter newsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        newsAdapter = new NewsFragment.NewsAdapter(getContext(), NewsArray);

        final ListView NewslistView = view.findViewById(R.id.news_list);

        NewslistView.setAdapter((ListAdapter) newsAdapter);

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

    class NewsAdapter extends ArrayList<NewsFragment.NewsModel>{


        public NewsAdapter(Context context, ArrayList<NewsFragment.NewsModel> news) {
            super(context,0,news);
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

            return view;
        }


    }
}
