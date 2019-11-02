package com.example.kozyhub.ui.coworking_space_list;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kozyhub.R;
import com.example.kozyhub.model.ProductData;
import com.example.kozyhub.ui.product_list.ProductListFragment;

public class CoworkingSpaceListFragment extends ProductListFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.destinationAction = R.id.action_coworkingSpaceListFragment_to_productDetailFragment;

        View root = super.onCreateView(inflater, container, savedInstanceState);

        ProductData[] data = {
                new ProductData("Blockchain Guesthouse", "At Palmerah", "50,000 IDR/hour"),
                new ProductData("Goden Guesthouse", "At Palmerah", "50,000 IDR/hour")
        };
        this.setData(data);

        return root;
    }
}
