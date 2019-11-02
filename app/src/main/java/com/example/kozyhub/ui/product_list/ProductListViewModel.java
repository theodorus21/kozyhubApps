package com.example.kozyhub.ui.product_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductListViewModel extends ViewModel {

    private MutableLiveData<ProductListData[]> data;

    public ProductListViewModel() {
        data = new MutableLiveData<>();

        ProductListData[] pd = {
                new ProductListData("Design Thinking Workshop", "At KozyHub Palmerah", "20 December 2019"),
                new ProductListData("Worklife Balance Workshop", "At Glora House", "29 December 2019")
        };

        data.setValue(pd);
    }

    public LiveData<ProductListData[]> getData() {
        return data;
    }
}