package com.example.kozyhub.ui.menu_list;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kozyhub.R;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.Cafe;
import com.example.kozyhub.model.Category;
import com.example.kozyhub.model.Menu;
import com.example.kozyhub.ui.cafe.CafeDetailFragmentDirections;
import com.example.kozyhub.ui.home.HomeFragment;
import com.example.kozyhub.ui.home.HomeFragmentDirections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder> {
    private Activity activity;
    private Class origin;
    private MutableLiveData<List<Menu>> data;
    private List<MenuRow> menuRows;

    public static class MenuRow {
        public Menu menuRight, menuLeft;
    }

    public static List<MenuRow> buildMenuRow(List<Menu> menus) {
        List<MenuRow> mr = new ArrayList<MenuRow>();

        if (menus == null) {
            return mr;
        }

        for (int i = 0; i < menus.size(); i += 2) {
            MenuRow row = new MenuRow();
            row.menuLeft = menus.get(i);
             if (i + 1 < menus.size()) {
                row.menuRight = menus.get(i + 1);
            }

            mr.add(row);
        }

        return mr;
    }

    public static class MenuListViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public View cardMenuLeft, cardMenuRight;
        public ImageView ivMenuLeft, ivMenuRight;
        public TextView tvNameLeft, tvNameRight, tvPriceLeft, tvPriceRight;

        public MenuListViewHolder(View container) {
            super(container);
            this.container = container;

            this.cardMenuLeft = container.findViewById(R.id.card_menu_left);
            this.cardMenuRight = container.findViewById(R.id.card_menu_right);
            this.ivMenuLeft = container.findViewById(R.id.image_menu_left);
            this.ivMenuRight = container.findViewById(R.id.image_menu_right);
            this.tvNameLeft = container.findViewById(R.id.text_view_name_left);
            this.tvNameRight = container.findViewById(R.id.text_view_name_right);
            this.tvPriceLeft = container.findViewById(R.id.text_view_price_left);
            this.tvPriceRight = container.findViewById(R.id.text_view_price_right);
        }
    }

    public MenuListAdapter(Activity activity, Class origin, MutableLiveData<List<Menu>> data) {
        this.activity = activity;
        this.origin = origin;
        this.data = data;
        this.menuRows = buildMenuRow(data.getValue());

        this.data.observe((LifecycleOwner) this.activity, new Observer<List<Menu>>() {
            @Override
            public void onChanged(List<Menu> menus) {
                System.out.println("data changed");
                menuRows = buildMenuRow(menus);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public MenuListAdapter.MenuListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cl = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_menu, parent, false);
        return new MenuListViewHolder(cl);
    }

    @Override
    public void onBindViewHolder(MenuListViewHolder holder, int position) {
        final MenuRow mr = menuRows.get(position);
        if (mr == null) {
            return;
        }

        holder.cardMenuLeft.setVisibility(View.GONE);
        if (mr.menuLeft != null) {
            holder.cardMenuLeft.setVisibility(View.VISIBLE);
            holder.tvNameLeft.setText(mr.menuLeft.MenuName);
            holder.tvPriceLeft.setText(mr.menuLeft.MenuPrice + "");
            if (mr.menuLeft.MenuPict != "") {
                Glide.with(activity).load(URL.BaseURL + mr.menuLeft.MenuPict).into(holder.ivMenuLeft);
            }

            holder.cardMenuLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                    NavDirections action = CafeDetailFragmentDirections.actionCafeDetailFragmentToMenuDetailFragment(mr.menuLeft.MenuName, mr.menuLeft);
                    navController.navigate(action);
                }
            });
        }

        holder.cardMenuRight.setVisibility(View.GONE);
        if (mr.menuRight != null) {
            holder.cardMenuRight.setVisibility(View.VISIBLE);
            holder.tvNameRight.setText(mr.menuRight.MenuName);
            holder.tvPriceRight.setText(mr.menuRight.MenuPrice + "");
            if (mr.menuRight.MenuPict != "") {
                Glide.with(activity).load(URL.BaseURL + mr.menuRight.MenuPict).into(holder.ivMenuRight);
            }

            holder.cardMenuRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                    NavDirections action = CafeDetailFragmentDirections.actionCafeDetailFragmentToMenuDetailFragment(mr.menuRight.MenuName, mr.menuRight);
                    navController.navigate(action);
                }
            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return menuRows.size();
    }
}
