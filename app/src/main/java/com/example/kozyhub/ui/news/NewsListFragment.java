package com.example.kozyhub.ui.news;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kozyhub.R;
import com.example.kozyhub.constant.Types;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.Cafe;
import com.example.kozyhub.model.Category;
import com.example.kozyhub.model.Menu;
import com.example.kozyhub.model.News;
import com.example.kozyhub.model.Property;
import com.google.gson.Gson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment {

    private RecyclerView rvNewsList;
    private NewsListAdapter mAdapterNewsList;
    private RecyclerView.LayoutManager lmNewsList;

    private List<News> data;

    public NewsListFragment() {
        data = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_list, container, false);

        rvNewsList = root.findViewById(R.id.recycler_view_news_list);
        rvNewsList.setHasFixedSize(true);

        lmNewsList = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rvNewsList.setLayoutManager(lmNewsList);

        mAdapterNewsList = new NewsListAdapter(getActivity(), data);
        rvNewsList.setAdapter(mAdapterNewsList);

        populateData();

        return root;
    }

    private void populateData() {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL.BaseURL + "api/categories/news_list.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody responseBody = response.body();

                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    String jsonResponse = responseBody.string();
                    Moshi moshi = new Moshi.Builder().build();

                    JsonAdapter<com.example.kozyhub.model.Response<News>> jsonAdapter = moshi.adapter(Types.ResponseNews);
                    com.example.kozyhub.model.Response res = jsonAdapter.fromJson(jsonResponse);

                    final List<News> news = new ArrayList<>();
                    for (int i = 0; i < res.result.size(); i++) {
                        News p = (News) res.result.get(i);
                        news.add(p);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapterNewsList.setData(news);
                            mAdapterNewsList.notifyDataSetChanged();
                        }
                    });

                } catch (IOException e) {
                    System.out.println("IOException" + e.getStackTrace());
                }
            }
        });
    }

}
