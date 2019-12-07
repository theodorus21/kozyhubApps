package com.example.kozyhub.ui.cafe;

import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kozyhub.R;
import com.example.kozyhub.constant.Types;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.Cafe;
import com.example.kozyhub.model.Category;
import com.example.kozyhub.model.Menu;
import com.example.kozyhub.ui.booking_list.BookingListAdapter;
import com.example.kozyhub.ui.booking_list.BookingListFragment;
import com.example.kozyhub.ui.menu_list.MenuListAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CafeDetailFragment extends Fragment {
    private TextView tvName, tvShortDescription, tvLongDescription;
    private ImageView ivImage;
    private Cafe cafe;

    private RecyclerView rvRowMenu;
    private RecyclerView.Adapter mAdapterMenuList;
    private RecyclerView.LayoutManager lmMenuList;

    private MutableLiveData<List<Menu>> menuListData;

    public CafeDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        menuListData = new MutableLiveData<>();

        View root = inflater.inflate(R.layout.fragment_cafe_detail, container, false);

        tvName = root.findViewById(R.id.name);
        tvShortDescription = root.findViewById(R.id.description_short);
        tvLongDescription = root.findViewById(R.id.description_long);
        ivImage = root.findViewById(R.id.image);

        cafe = (Cafe) CafeDetailFragmentArgs.fromBundle(getArguments()).getCategory();
        if (cafe != null) {
            tvName.setText(cafe.getName());
            tvShortDescription.setText(cafe.getBranchDesc());
            tvLongDescription.setText(cafe.getDescription());
            Glide.with(getActivity()).load(URL.BaseURL + cafe.getImage()).into(ivImage);
        }

        rvRowMenu = root.findViewById(R.id.recycler_view_row_menu);
        rvRowMenu.setHasFixedSize(true);

        lmMenuList = new LinearLayoutManager(this.getContext());
        rvRowMenu.setLayoutManager(lmMenuList);

        mAdapterMenuList = new MenuListAdapter(getActivity(), CafeDetailFragment.class, menuListData);
        rvRowMenu.setAdapter(mAdapterMenuList);

        populateMenuData();

        return root;
    }

    private void populateMenuData() {
        if (cafe == null) {
            return;
        }

        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL.BaseURL + "api/categories/menu_cafe.php?id=" + cafe.getPkBranch())
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

                    JsonAdapter<com.example.kozyhub.model.Response<Menu>> jsonAdapter = moshi.adapter(Types.ResponseMenu);
                    com.example.kozyhub.model.Response res = jsonAdapter.fromJson(jsonResponse);

                    System.out.println(res.result);

                    menuListData.postValue(res.result);

                } catch (IOException e) {
                    System.out.println("IOException" + e.getStackTrace());
                }
            }
        });
    }
}
