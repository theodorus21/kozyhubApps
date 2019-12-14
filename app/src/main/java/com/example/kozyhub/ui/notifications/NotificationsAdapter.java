package com.example.kozyhub.ui.notifications;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kozyhub.R;
import com.example.kozyhub.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {
    private Activity activity;
    private List<Notification> notifications;

    public static class NotificationsViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView tvDate, tvTitle, tvDescription;

        public NotificationsViewHolder(View container) {
            super(container);
            this.container = container;

            this.tvDate = container.findViewById(R.id.tv_date);
            this.tvTitle = container.findViewById(R.id.tv_title);
            this.tvDescription = container.findViewById(R.id.tv_description);
        }
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
        this.notifyDataSetChanged();
    }

    public NotificationsAdapter(Activity activity) {
        this.activity = activity;
        this.notifications = new ArrayList<>();
    }

    @Override
    public NotificationsAdapter.NotificationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cl = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        NotificationsAdapter.NotificationsViewHolder vh = new NotificationsAdapter.NotificationsViewHolder(cl);
        return vh;
    }

    @Override
    public void onBindViewHolder(final NotificationsAdapter.NotificationsViewHolder holder, int position) {
        Notification n = this.notifications.get(position);

        holder.tvDate.setText(n.TransactionDate);
        holder.tvTitle.setText(n.Title);
        holder.tvDescription.setText(n.Description);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return notifications.size();
    }
}