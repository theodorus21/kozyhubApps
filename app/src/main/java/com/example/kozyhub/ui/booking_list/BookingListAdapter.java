package com.example.kozyhub.ui.booking_list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.example.kozyhub.model.Category;
import com.example.kozyhub.ui.home.HomeFragment;
import com.example.kozyhub.ui.home.HomeFragmentDirections;

public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.BookingListViewHolder> {
    private Activity activity;
    private Class origin;
    private int destination;
    private MutableLiveData<Category[]> data;

    public static final int DESTINATION_PROPERTY = 1;
    public static final int DESTINATION_CAFE = 2;
    public static final int DESTINATION_CATERING = 3;

    public static class BookingListViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout container;
        public TextView tvName;
        public ImageView ivImage;

        public BookingListViewHolder(LinearLayout container) {
            super(container);
            this.container = container;

            tvName = container.findViewById(R.id.name);
            ivImage = container.findViewById(R.id.image);
        }
    }

    public BookingListAdapter(Activity activity, Class origin, int destination, MutableLiveData<Category[]> data) {
        this.activity = activity;
        this.origin = origin;
        this.destination = destination;
        this.data = data;

        this.data.observe((LifecycleOwner) this.activity, new Observer<Category[]>() {
            @Override
            public void onChanged(Category[] categories) {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public BookingListAdapter.BookingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout cl = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);
        BookingListViewHolder vh = new BookingListViewHolder(cl);
        return vh;
    }

    @Override
    public void onBindViewHolder(BookingListViewHolder holder, int position) {
        final Category c = data.getValue()[position];

        holder.tvName.setText(c.getName());
        if (c.getImage() != "") {
            Glide.with(activity).load(URL.BaseURL + c.getImage()).into(holder.ivImage);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                NavDirections action = null;
                if (origin == BookingListFragment.class) {
                    if (destination == DESTINATION_PROPERTY) {
                        action = BookingListFragmentDirections.actionNavigationBookingToPropertyDetailFragment(c.getName(), c);
                    } else if (destination == DESTINATION_CAFE){
                        action = BookingListFragmentDirections.actionNavigationBookingToCafeDetailFragment(c.getName(), c);
                    } else {
                        action = BookingListFragmentDirections.actionNavigationBookingToCateringFragment(c.getName(), c);
                    }
                } else if (origin == HomeFragment.class) {
                    if (destination == DESTINATION_PROPERTY) {
                        action = HomeFragmentDirections.actionNavigationHomeToPropertyDetailFragment(c.getName(), c);
                    } else if (destination == DESTINATION_CAFE){
                        action = HomeFragmentDirections.actionNavigationHomeToCafeDetailFragment(c.getName(), c);
                    } else {
                        action = HomeFragmentDirections.actionNavigationHomeToCateringFragment(c.getName(), c);
                    }
                }
                if (action != null) {
                    navController.navigate(action);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.getValue().length;
    }
}
