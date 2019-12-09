package com.example.kozyhub.ui.news;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kozyhub.R;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.News;
import com.example.kozyhub.ui.menu_detail.MenuDetailFragmentArgs;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetailFragment extends Fragment {

    private News news;
    private TextView tvTitle;
    private ImageView ivImage;
    private WebView wvImage, wvBody;

    public NewsDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_detail, container, false);

        this.news = NewsDetailFragmentArgs.fromBundle(getArguments()).getNews();

        tvTitle = root.findViewById(R.id.title);
        ivImage = root.findViewById(R.id.image);
        wvImage = root.findViewById(R.id.webview);
        wvBody = root.findViewById(R.id.body);

        tvTitle.setText(news.NewsTitle);
        wvBody.loadData(news.NewsDesc,"text/html", null);
        if (news.NewsFlagType == 2) {
            wvImage.setVisibility(View.GONE);
            Glide.with(getActivity()).load(URL.BaseURL + news.NewsPict).into(ivImage);
        } else {
            ivImage.setVisibility(View.GONE);
            wvImage.getSettings().setJavaScriptEnabled(true);
            wvImage.loadData(news.NewsPict, "text/html", null);
        }

        return root;
    }

}
