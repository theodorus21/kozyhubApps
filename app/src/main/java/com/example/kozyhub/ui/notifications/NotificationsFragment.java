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

import com.example.kozyhub.R;
import com.example.kozyhub.util.session.SessionManager;

public class NotificationsFragment extends Fragment {
    private final int LOGGEDIN_FRAGMENT = R.layout.fragment_notifications;
    private final int GUEST_FRAGMENT = R.layout.fragment_notification_guest;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);

        int fragmentId = GUEST_FRAGMENT;
        if (SessionManager.getInstance().isIsLoggedIn(getActivity())) {
            fragmentId = LOGGEDIN_FRAGMENT;
        }

        View root = inflater.inflate(fragmentId, container, false);

        if (!SessionManager.getInstance().isIsLoggedIn(getActivity())) {
            root.findViewById(R.id.btn_login_link).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.action_navigation_notifications_to_navigation_profile);
                }
            });
        }

        return root;
    }
}