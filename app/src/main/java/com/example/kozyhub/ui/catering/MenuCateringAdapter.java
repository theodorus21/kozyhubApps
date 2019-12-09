package com.example.kozyhub.ui.catering;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kozyhub.R;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.CateringBooking;
import com.example.kozyhub.model.Katering;

public class MenuCateringAdapter extends RecyclerView.Adapter<MenuCateringAdapter.MenuCateringViewHolder> {
    private Activity activity;
    private CateringBooking cb;
    private Katering[] data;

    public static class MenuCateringViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView tvName, tvPrice, tvQty;
        public ImageView ivImage;
        public Button btnMinus, btnPlus;

        public MenuCateringViewHolder(View container) {
            super(container);
            this.container = container;

            tvName = container.findViewById(R.id.text_view_name);
            tvPrice = container.findViewById(R.id.text_view_price);
            tvQty = container.findViewById(R.id.tv_qty);
            ivImage = container.findViewById(R.id.image_menu);
            btnMinus = container.findViewById(R.id.btn_min);
            btnPlus = container.findViewById(R.id.btn_plus);
        }

        public int updateQty(int op) {
            int qty = 0;
            try {
                qty = Integer.parseInt(tvQty.getText().toString());
            } catch (NumberFormatException e) {
                qty = 0;
            }

            qty += op;
            if (qty < 0) {
                qty = 0;
            }
            tvQty.setText(qty + "");
            return qty;
        }
    }

    public MenuCateringAdapter(Activity activity, CateringBooking cb, Katering[] data) {
        this.activity = activity;
        this.cb = cb;
        this.data = data;
    }

    @Override
    public MenuCateringAdapter.MenuCateringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cl = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_catering_menu_single, parent, false);
        MenuCateringViewHolder vh = new MenuCateringViewHolder(cl);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MenuCateringViewHolder holder, int position) {
        final Katering m = data[position];

        holder.tvName.setText(m.KateringName);
        holder.tvPrice.setText(m.formatMenuPrice());
        if (!m.KateringPict.equals("")) {
            Glide.with(activity).load(URL.BaseURL + m.KateringPict).into(holder.ivImage);
        }

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = holder.updateQty(-1);
                cb.update(m.PkKateringScheduleDtl, m, qty);
            }
        });
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = holder.updateQty(+1);
                cb.update(m.PkKateringScheduleDtl, m, qty);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.length;
    }
}