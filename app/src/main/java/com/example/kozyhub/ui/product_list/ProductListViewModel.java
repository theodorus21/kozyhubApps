package com.example.kozyhub.ui.product_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kozyhub.model.ProductData;

public class ProductListViewModel extends ViewModel {

    private MutableLiveData<ProductData[]> data;

    public ProductListViewModel() {
        data = new MutableLiveData<>();

        ProductData[] pd = {};

        data.setValue(pd);
    }

    public void setData(ProductData[] data) {
        this.data.setValue(data);
    }

    public LiveData<ProductData[]> getData() {
        return data;
    }
}