package com.example.kozyhub.ui.product_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kozyhub.R;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {
    private ProductListViewModel vm;

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout cl;
        public TextView tvName, tvShortDesc, tvLongDesc;

        public ProductListViewHolder(ConstraintLayout cl) {
            super(cl);
            this.cl = cl;
            
            tvName = cl.findViewById(R.id.name);
            tvShortDesc = cl.findViewById(R.id.description_short);
            tvLongDesc = cl.findViewById(R.id.description_long);
        }
    }

    public ProductListAdapter(ProductListViewModel vm) {
        this.vm = vm;
    }

    @Override
    public ProductListAdapter.ProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout cl = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list, parent, false);
        ProductListViewHolder vh = new ProductListViewHolder(cl);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ProductListViewHolder holder, int position) {
        ProductListData d = vm.getData().getValue()[position];
        holder.tvName.setText(d.getName());
        holder.tvShortDesc.setText(d.getShortDescription());
        holder.tvLongDesc.setText(d.getLongDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return vm.getData().getValue().length;
    }
}
