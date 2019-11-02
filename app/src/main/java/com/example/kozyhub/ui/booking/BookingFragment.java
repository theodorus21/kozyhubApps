package com.example.kozyhub.ui.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kozyhub.R;
import com.example.kozyhub.ui.news.NewsFragment;

public class BookingFragment extends Fragment {

    private BookingViewModel bookingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        bookingViewModel =
                ViewModelProviders.of(this).get(BookingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_booking, container, false);

        final View thumbGuestHouse = root.findViewById(R.id.booking_guest_house_thumb);
        final View thumbCoworkingSpace = root.findViewById(R.id.booking_coworing_space_thumb);
        final View thumbPropertyManagement = root.findViewById(R.id.booking_property_management_thumb);
        final View thumbRestoCafe = root.findViewById(R.id.booking_resto_cafe_thumb);
        final View thumbCatering = root.findViewById(R.id.booking_catering_thumb);

        thumbGuestHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_booking_to_guestHouseListFragment);
            }
        });
        thumbCoworkingSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_booking_to_coworkingSpaceListFragment);
            }
        });
        thumbRestoCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_booking_to_restaurantListFragment);
            }
        });
        return root;
    }
}