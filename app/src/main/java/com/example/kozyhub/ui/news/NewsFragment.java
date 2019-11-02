package com.example.kozyhub.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.kozyhub.model.ProductData;
import com.example.kozyhub.ui.product_list.ProductListFragment;

public class NewsFragment extends ProductListFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        ProductData[] data = {
                new ProductData("Design Thinking Workshop", "At KozyHub Palmerah", "20 December 2019"),
                new ProductData("Worklife Balance Workshop", "At Glora House", "29 December 2019")
        };
        this.setData(data);

        return root;
    }
}