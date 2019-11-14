package com.example.kozyhub.ui.product_list;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kozyhub.R;
import com.example.kozyhub.model.ProductData;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {
    private Activity activity;
    private int destinationAction;
    private ProductListViewModel vm;

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout container;
        public TextView tvName, tvShortDesc, tvLongDesc;
        public ImageView ivImage;

        public ProductListViewHolder(LinearLayout container) {
            super(container);
            this.container = container;
            
            tvName = container.findViewById(R.id.name);
            tvShortDesc = container.findViewById(R.id.description_short);
            tvLongDesc = container.findViewById(R.id.description_long);
            ivImage = container.findViewById(R.id.image);
        }
    }

    public ProductListAdapter(Activity activity, int destinationAction, ProductListViewModel vm) {
        this.activity = activity;
        this.destinationAction = destinationAction;
        this.vm = vm;
    }

    @Override
    public ProductListAdapter.ProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout cl = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list, parent, false);
        ProductListViewHolder vh = new ProductListViewHolder(cl);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ProductListViewHolder holder, int position) {
        final ProductData d = vm.getData().getValue()[position];
        holder.tvName.setText(d.getName());
        holder.tvShortDesc.setText((d.getShortDescription()));
        holder.tvLongDesc.setText(d.getLongDescription());

        Glide.with(activity).load("https://picsum.photos/200/300").into(holder.ivImage);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                Bundle bundle = new Bundle();
                bundle.putParcelable("productdata", d);

                navController.navigate(destinationAction, bundle);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return vm.getData().getValue().length;
    }
}
