package com.example.kozyhub.ui.product_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductListViewModel extends ViewModel {

    private MutableLiveData<ProductListData[]> data;

    public ProductListViewModel() {
        data = new MutableLiveData<>();

        ProductListData[] pd = {};

        data.setValue(pd);
    }

    public void setData(ProductListData[] data) {
        this.data.setValue(data);
    }

    public LiveData<ProductListData[]> getData() {
        return data;
    }
}