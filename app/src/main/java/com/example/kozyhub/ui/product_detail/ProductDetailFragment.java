package com.example.kozyhub.ui.product_detail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kozyhub.R;
import com.example.kozyhub.model.ProductData;

public class ProductDetailFragment extends Fragment {
    private ProductData data;

    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data = (ProductData) ProductDetailFragmentArgs.fromBundle(getArguments()).getCategory();

        final View root = inflater.inflate(R.layout.fragment_product_detail, container, false);

        if (data != null) {
            ((TextView) root.findViewById(R.id.name)).setText(data.getName());
            ((TextView) root.findViewById(R.id.description_short)).setText(data.getShortDescription());
            ((TextView) root.findViewById(R.id.description_long)).setText(data.getLongDescription());
        }

        return root;
    }
}
