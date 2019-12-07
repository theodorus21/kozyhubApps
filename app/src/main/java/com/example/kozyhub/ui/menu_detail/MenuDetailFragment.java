package com.example.kozyhub.ui.menu_detail;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kozyhub.R;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.Menu;

public class MenuDetailFragment extends Fragment {
    private Menu menu;

    private ImageView ivImage;
    private TextView tvName, tvShortDescription, tvLongDescription, tvBody;

    public MenuDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu_detail, container, false);

        this.ivImage = root.findViewById(R.id.image);
        this.tvName = root.findViewById(R.id.name);
        this.tvShortDescription = root.findViewById(R.id.description_short);
        this.tvLongDescription = root.findViewById(R.id.description_long);
        this.tvBody = root.findViewById(R.id.body);

        this.menu = MenuDetailFragmentArgs.fromBundle(getArguments()).getMenu();
        if (this.menu != null) {
            Glide.with(getActivity()).load(URL.BaseURL + this.menu.MenuPict).into(this.ivImage);
            this.tvName.setText(menu.MenuName);
            this.tvShortDescription.setText(menu.formatMenuPrice());
            this.tvLongDescription.setVisibility(View.GONE);
            this.tvBody.setText(menu.MenuDesc);
        }

        return root;
    }

}
