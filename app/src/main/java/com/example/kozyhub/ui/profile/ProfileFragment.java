package com.example.kozyhub.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.kozyhub.R;
import com.example.kozyhub.model.User;
import com.example.kozyhub.util.session.SessionManager;

public class ProfileFragment extends Fragment {
    private View getGuestLayout(@NonNull LayoutInflater inflater, ViewGroup container) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        final Button btnLogin = root.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.setUser(new User("wkwk", "wkwk@wkwk.com", "081234567", 100000));
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_profile_to_navigation_home);
                Toast.makeText(getContext(), "Welcome, wkwk", Toast.LENGTH_LONG).show();
            }
        });

        final View btnSignup = root.findViewById(R.id.textview_signup_link);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_profile_to_registerFragment);
            }
        });

        return root;
    }

    private View getMemberLayout(@NonNull LayoutInflater inflater, ViewGroup container) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        return root;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root;

        if (SessionManager.isIsLoggedIn()) {
            root = getMemberLayout(inflater, container);
        } else {
            root = getGuestLayout(inflater, container);
        }

        return root;
    }
}