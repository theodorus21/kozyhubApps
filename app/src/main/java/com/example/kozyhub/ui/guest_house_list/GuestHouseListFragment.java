package com.example.kozyhub.ui.guest_house_list;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kozyhub.R;
import com.example.kozyhub.ui.product_list.ProductListData;
import com.example.kozyhub.ui.product_list.ProductListFragment;

public class GuestHouseListFragment extends ProductListFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        ProductListData[] data = {
                new ProductListData("Blockchain Guesthouse", "At Palmerah", "50,000 IDR/hour"),
                new ProductListData("Goden Guesthouse", "At Palmerah", "50,000 IDR/hour")
        };
        this.setData(data);

        return root;
    }
}
