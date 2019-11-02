package com.example.kozyhub.ui.product_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kozyhub.R;
import com.example.kozyhub.model.ProductData;

public class ProductListFragment extends Fragment {

    private ProductListViewModel vm;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    protected int destinationAction;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vm = ViewModelProviders.of(this).get(ProductListViewModel.class);

        View root = inflater.inflate(R.layout.fragment_product_list, container, false);

        recyclerView = root.findViewById(R.id.recycler_view_product_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ProductListAdapter(getActivity(), destinationAction, vm);
        recyclerView.setAdapter(mAdapter);

        return root;
    }

    public void setData(ProductData[] data) {
        this.vm.setData(data);
    }
}