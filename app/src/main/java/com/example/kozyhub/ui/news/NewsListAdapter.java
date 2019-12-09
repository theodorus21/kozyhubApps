package com.example.kozyhub.ui.news;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kozyhub.R;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.Category;
import com.example.kozyhub.model.News;
import com.example.kozyhub.ui.booking_list.BookingListFragment;
import com.example.kozyhub.ui.booking_list.BookingListFragmentDirections;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {
    private Activity activity;
    private List<News> data;

    public void setData(List<News> data) {
        this.data = data;
    }

    public static class NewsListViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout container;
        public TextView tvTitle;
        public ImageView ivImage;
        public WebView wvImage;

        public NewsListViewHolder(LinearLayout container) {
            super(container);
            this.container = container;

            tvTitle = container.findViewById(R.id.title);
            ivImage = container.findViewById(R.id.image);
            wvImage = container.findViewById(R.id.webview);
        }
    }

    public NewsListAdapter(Activity activity, List<News> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public NewsListAdapter.NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout cl = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_list, parent, false);
        NewsListViewHolder vh = new NewsListViewHolder(cl);
        return vh;
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        final News n = data.get(position);

        holder.tvTitle.setText(n.NewsTitle);
        if (n.NewsFlagType == 2) {
            holder.wvImage.setVisibility(View.GONE);
            System.out.println(URL.BaseURL + n.NewsPict);
            Glide.with(activity).load(URL.BaseURL + n.NewsPict).into(holder.ivImage);
        } else {
            holder.ivImage.setVisibility(View.GONE);
            holder.wvImage.getSettings().setJavaScriptEnabled(true);
            holder.wvImage.loadData(n.NewsPict, "text/html", null);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                navController.navigate(NewsListFragmentDirections.actionNavigationNewsToNewsDetailFragment(n.NewsTitle, n));
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }
}
