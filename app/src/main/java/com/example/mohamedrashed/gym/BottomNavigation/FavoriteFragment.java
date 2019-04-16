package com.example.mohamedrashed.gym.BottomNavigation;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mohamedrashed.gym.R;

import java.util.Locale;

public class FavoriteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_favorite, container, false);

        WebView myWebView = (WebView) view.findViewById(R.id.webView);
        myWebView.loadUrl("https://www.facebook.com/pg/goldsgym/photos/?tab=album&album_id=10151636513051309");
        //myWebView.setBackgroundColor(Color.TRANSPARENT);
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient());

        return view;

    }



}
