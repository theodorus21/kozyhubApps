package com.example.kozyhub.ui.booking_list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kozyhub.constant.Types;
import com.example.kozyhub.model.Cafe;
import com.example.kozyhub.model.Category;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BookingListViewModel extends ViewModel {
    private MutableLiveData<Category[]> dataGuestHouse;
    private MutableLiveData<Category[]> dataCoworkingSpace;
    private MutableLiveData<Category[]> dataCafe;
    private MutableLiveData<Category[]> dataCatering;

    public BookingListViewModel() {
        dataGuestHouse = new MutableLiveData<>();
        dataCoworkingSpace = new MutableLiveData<>();
        dataCafe = new MutableLiveData<>();
        dataCatering = new MutableLiveData<>();

        // seeds
        Category[] seedGuestHouse = {
                new Category("guest house 1", ""),
                new Category("guest house 2", "")
        };
        dataGuestHouse.setValue(seedGuestHouse);
        Category[] seedCoworkingSpace = {
                new Category("coworking space 1", ""),
                new Category("coworking space 2", "")
        };
        dataCoworkingSpace.setValue(seedCoworkingSpace);
        Category[] seedCafe = {};
        dataCafe.setValue(seedCafe);
        Category[] seedCatering = {
                new Category("catering 1", ""),
                new Category("catering 2", "")
        };
        dataCatering.setValue(seedCatering);

        populateCafeData();
    }

    public MutableLiveData<Category[]> getDataGuestHouse() {
        return dataGuestHouse;
    }

    public void setDataGuestHouse(MutableLiveData<Category[]> dataGuestHouse) {
        this.dataGuestHouse = dataGuestHouse;
    }

    public MutableLiveData<Category[]> getDataCoworkingSpace() {
        return dataCoworkingSpace;
    }

    public void setDataCoworkingSpace(MutableLiveData<Category[]> dataCoworkingSpace) {
        this.dataCoworkingSpace = dataCoworkingSpace;
    }

    public MutableLiveData<Category[]> getDataCafe() {
        return dataCafe;
    }

    public void setDataCafe(MutableLiveData<Category[]> dataCafe) {
        this.dataCafe = dataCafe;
    }

    public MutableLiveData<Category[]> getDataCatering() {
        return dataCatering;
    }

    public void setDataCatering(MutableLiveData<Category[]> dataCatering) {
        this.dataCatering = dataCatering;
    }

    public void populateCafeData() {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://kozyhub.com/api/categories/cafe_list.php")
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

                    JsonAdapter<com.example.kozyhub.model.Response<Cafe>> jsonAdapter = moshi.adapter(Types.ResponseCafe);
                    com.example.kozyhub.model.Response res = jsonAdapter.fromJson(jsonResponse);

                    Category[] categories = new Category[res.result.size()];
                    for (int i = 0; i < res.result.size(); i++) {
                        categories[i] = (Cafe) res.result.get(i);
                    }
                    dataCafe.postValue(categories);


                } catch (IOException e) {
                    System.out.println("IOException" + e.getStackTrace());
                }
            }
        });
    }
}