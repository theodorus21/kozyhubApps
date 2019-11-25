package com.example.kozyhub.ui.booking_list;

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
import com.example.kozyhub.util.session.SessionManager;

public class BookingListFragment extends Fragment {
    private BookingListViewModel bookingViewModel;

    private RecyclerView rvGuestHouse;
    private RecyclerView.Adapter mAdapterGuestHouse;
    private RecyclerView.LayoutManager lmGuestHouse;

    private RecyclerView rvCoworkingSpace;
    private RecyclerView.Adapter mAdapterCoworkingSpace;
    private RecyclerView.LayoutManager lmCoworkingSpace;

    private RecyclerView rvCafe;
    private RecyclerView.Adapter mAdapterCafe;
    private RecyclerView.LayoutManager lmCafe;

    private RecyclerView rvCatering;
    private RecyclerView.Adapter mAdapterCatering;
    private RecyclerView.LayoutManager lmCatering;

    protected int destinationAction;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookingViewModel =
                ViewModelProviders.of(this).get(BookingListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_booking, container, false);

        rvGuestHouse = root.findViewById(R.id.recycler_view_guest_house_list);
        rvGuestHouse.setHasFixedSize(true);

        lmGuestHouse = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvGuestHouse.setLayoutManager(lmGuestHouse);

        mAdapterGuestHouse = new BookingListAdapter(getActivity(), BookingListFragment.class, BookingListAdapter.DESTINATION_PROPERTY, bookingViewModel.getDataGuestHouse());
        rvGuestHouse.setAdapter(mAdapterGuestHouse);

        // coworking space
        rvCoworkingSpace = root.findViewById(R.id.recycler_view_coworking_space_list);
        rvCoworkingSpace.setHasFixedSize(true);

        lmCoworkingSpace = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCoworkingSpace.setLayoutManager(lmCoworkingSpace);

        mAdapterCoworkingSpace = new BookingListAdapter(getActivity(), BookingListFragment.class, BookingListAdapter.DESTINATION_PROPERTY, bookingViewModel.getDataCoworkingSpace());
        rvCoworkingSpace.setAdapter(mAdapterCoworkingSpace);

        // cafe
        rvCafe = root.findViewById(R.id.recycler_view_cafe_list);
        rvCafe.setHasFixedSize(true);

        lmCafe = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCafe.setLayoutManager(lmCafe);

        mAdapterCafe = new BookingListAdapter(getActivity(), BookingListFragment.class, BookingListAdapter.DESTINATION_CAFE, bookingViewModel.getDataCafe());
        rvCafe.setAdapter(mAdapterCafe);

        // catering
        View cateringListContainer = root.findViewById(R.id.view_catering_list);
        cateringListContainer.setVisibility(View.GONE);
        if (SessionManager.isIsLoggedIn()) {
            cateringListContainer.setVisibility(View.VISIBLE);
            rvCatering = root.findViewById(R.id.recycler_view_catering_list);
            rvCatering.setHasFixedSize(true);

            lmCatering = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
            rvCatering.setLayoutManager(lmCatering);

            mAdapterCatering = new BookingListAdapter(getActivity(), BookingListFragment.class, BookingListAdapter.DESTINATION_CAFE, bookingViewModel.getDataCatering());
            rvCatering.setAdapter(mAdapterCatering);
        }

        return root;
    }
}