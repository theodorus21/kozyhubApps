package com.example.kozyhub.ui.catering;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kozyhub.R;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.Catering;
import com.example.kozyhub.model.CateringBooking;
import com.example.kozyhub.model.Katering;
import com.example.kozyhub.model.Menu;
import com.example.kozyhub.model.MenuCatering;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class CateringFragment extends Fragment {
    private Catering catering;

    public CateringBooking cateringBooking;

    private Button btnNext;
    private View order1, order2, order3;
    private DateOrder do1, do2, do3;

    private MutableLiveData<List<Katering>> menuListData;

    public CateringFragment() {
    }

    private class DateOrder {
        public View container;
        public TextView tvDate;
        public RecyclerView rvBreakfast, rvLunch, rvDinner;

        public Map<String, Katering[]> data;

        private MenuCateringAdapter adBreakfast, adLunch, adDinner;
        private RecyclerView.LayoutManager lmBreakfast, lmLunch, lmDinner;

        public DateOrder(View container, String date, Map<String, Katering[]> data) {
            this.container = container;
            this.data = data;

            tvDate = container.findViewById(R.id.tv_date);
            {
                rvBreakfast = container.findViewById(R.id.rv_breakfast);
                rvBreakfast.setHasFixedSize(true);

                lmBreakfast = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rvBreakfast.setLayoutManager(lmBreakfast);

                adBreakfast = new MenuCateringAdapter(getActivity(), cateringBooking, data.get("Breakfast"));
                rvBreakfast.setAdapter(adBreakfast);
            }
            {
                rvLunch = container.findViewById(R.id.rv_lunch);
                rvLunch.setHasFixedSize(true);

                lmLunch = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rvLunch.setLayoutManager(lmLunch);

                adLunch = new MenuCateringAdapter(getActivity(), cateringBooking, data.get("Lunch"));
                rvLunch.setAdapter(adLunch);
            }
            {
                rvDinner = container.findViewById(R.id.rv_dinner);
                rvDinner.setHasFixedSize(true);

                lmDinner = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rvDinner.setLayoutManager(lmDinner);

                adDinner = new MenuCateringAdapter(getActivity(), cateringBooking, data.get("Dinner"));
                rvDinner.setAdapter(adDinner);
            }

            tvDate.setText(date);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        menuListData = new MutableLiveData<>();

        View root = inflater.inflate(R.layout.fragment_catering, container, false);

        cateringBooking = new CateringBooking();

        order1 = root.findViewById((R.id.it_catering_1));
        order2 = root.findViewById((R.id.it_catering_2));
        order3 = root.findViewById((R.id.it_catering_3));

        catering = (Catering) CateringFragmentArgs.fromBundle(getArguments()).getCategory();
        btnNext = root.findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book(cateringBooking);
            }
        });

        populateMenuData();

        return root;
    }

    private void populateMenuData() {
        if (catering == null) {
            return;
        }

        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL.BaseURL + "api/categories/menu_catering.php")
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
//                    jsonResponse = "{\"info\":\"success\",\"result\":{\"2019-12-12\":{\"Breakfast\":[{\"kateringType\":\"Breakfast\",\"PkKateringScheduleDtl\":14,\"KateringName\":\"Bubur Ayam\",\"KateringPrice\":15000,\"KateringPict\":\"upload\\/cafe\\/Nasi Ayam Rica_image_1.jpeg\",\"KateringDate\":\"2019-12-12\"},{\"kateringType\":\"Breakfast\",\"PkKateringScheduleDtl\":15,\"KateringName\":\"Nasi Goreng Ayam\",\"KateringPrice\":20000,\"KateringPict\":\"upload\\/cafe\\/Nasi Ayam Rica_image_1.jpeg\",\"KateringDate\":\"2019-12-12\"}],\"Lunch\":[{\"kateringType\":\"Lunch\",\"PkKateringScheduleDtl\":16,\"KateringName\":\"Nasi Goreng Ayam\",\"KateringPrice\":20000,\"KateringPict\":\"upload\\/cafe\\/Nasi Ayam Rica_image_1.jpeg\",\"KateringDate\":\"2019-12-12\"}],\"Dinner\":[{\"kateringType\":\"Dinner\",\"PkKateringScheduleDtl\":17,\"KateringName\":\"Ayam Bakar Paket\",\"KateringPrice\":35000,\"KateringPict\":\"upload\\/cafe\\/Nasi Ayam Rica_image_1.jpeg\",\"KateringDate\":\"2019-12-12\"}]},\"2019-12-13\":{\"Breakfast\":[],\"Lunch\":[],\"Dinner\":[]},\"2019-12-14\":{\"Breakfast\":[],\"Lunch\":[],\"Dinner\":[]}}}";
                    Moshi moshi = new Moshi.Builder().build();

                    System.out.println(jsonResponse);

                    JsonAdapter<MenuCatering> jsonAdapter = moshi.adapter(MenuCatering.class);
                    final MenuCatering res = jsonAdapter.fromJson(jsonResponse);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateMenuList(res);
                        }
                    });

                } catch (IOException e) {
                    System.out.println("IOException" + e.getStackTrace());
                }
            }
        });
    }

    private void updateMenuList(MenuCatering mc) {
        String[] keys = mc.result.keySet().toArray(new String[mc.result.keySet().size()]);

        do1 = new DateOrder(order1, keys[0], mc.result.get(keys[0]));
        do2 = new DateOrder(order2, keys[1], mc.result.get(keys[1]));
        do3 = new DateOrder(order3, keys[2], mc.result.get(keys[2]));
    }

    private void book(CateringBooking cb) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        NavDirections action = CateringFragmentDirections.actionCateringFragmentToBookingSummaryFragment(catering, cb);
        navController.navigate(action);
    }

}
