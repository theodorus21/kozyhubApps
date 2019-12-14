package com.example.kozyhub.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kozyhub.R;
import com.example.kozyhub.constant.Types;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.Menu;
import com.example.kozyhub.model.Notification;
import com.example.kozyhub.ui.booking_list.BookingListAdapter;
import com.example.kozyhub.ui.booking_list.BookingListFragment;
import com.example.kozyhub.util.session.SessionManager;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NotificationsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private View viewGuest(@NonNull LayoutInflater inflater,
                           ViewGroup container) {
        View root = inflater.inflate(R.layout.fragment_notification_guest, container, false);

        root.findViewById(R.id.btn_login_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_notifications_to_navigation_profile);
            }
        });

        return root;
    }

    private View viewMember(@NonNull LayoutInflater inflater,
                            ViewGroup container) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = root.findViewById(R.id.rv_notification);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NotificationsAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        populateData();

        return root;
    }

    private void populateData() {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL.BaseURL + "api/categories/notification.php?pkPerson=" + SessionManager.getInstance().getUser(getActivity()).PkPerson)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody responseBody = response.body();

                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    String jsonResponse = responseBody.string();
                    Moshi moshi = new Moshi.Builder().build();

                    JsonAdapter<com.example.kozyhub.model.Response<Notification>> jsonAdapter = moshi.adapter(Types.ResponseNotification);
                    final com.example.kozyhub.model.Response res = jsonAdapter.fromJson(jsonResponse);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setNotifications(res.result);
                        }
                    });

                } catch (IOException e) {
                    System.out.println("IOException" + e.getStackTrace());
                }
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root;
        if (!SessionManager.getInstance().isIsLoggedIn(getActivity())) {
            root = viewGuest(inflater, container);
        } else {
            root = viewMember(inflater, container);
        }

        return root;
    }
}