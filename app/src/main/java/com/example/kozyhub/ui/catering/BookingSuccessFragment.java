package com.example.kozyhub.ui.catering;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kozyhub.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingSuccessFragment extends Fragment {


    public BookingSuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_booking_success, container, false);

        Button btnHome = root.findViewById(R.id.btn_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_bookingSuccessFragment_to_navigation_home);
            }
        });

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
            getActivity().getActionBar().setHomeButtonEnabled(false);
        }

        return root;
    }

}
