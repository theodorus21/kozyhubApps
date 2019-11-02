package com.example.kozyhub.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kozyhub.R;
import com.example.kozyhub.ui.product_list.ProductListData;
import com.example.kozyhub.ui.product_list.ProductListFragment;

public class NewsFragment extends ProductListFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        ProductListData[] data = {
                new ProductListData("Design Thinking Workshop", "At KozyHub Palmerah", "20 December 2019"),
                new ProductListData("Worklife Balance Workshop", "At Glora House", "29 December 2019")
        };
        this.setData(data);

        return root;
    }
}