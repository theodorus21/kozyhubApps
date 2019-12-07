package com.example.kozyhub.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
                SessionManager.getInstance().setUser(getActivity(), new User("wkwk", "wkwk", "wkwk@wkwk.com", "phone"));
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_profile_to_navigation_home);
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

        final EditText etFullname = root.findViewById(R.id.edittext_fullname);
        final EditText etUsername= root.findViewById(R.id.edittext_username);
        final EditText etPhone= root.findViewById(R.id.edittext_phone);
        final EditText etEmail= root.findViewById(R.id.edittext_email);
        final Button btnSave = root.findViewById(R.id.btn_save);

        User user = SessionManager.getInstance().getUser(getActivity());
        etFullname.setText(user.getFullname());
        etUsername.setText(user.getUsername());
        etPhone.setText(user.getPhone());
        etEmail.setText(user.getEmail());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        final Button btnLogout = root.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.getInstance().invalidateUser(getActivity());
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_profile_to_navigation_home);
            }
        });

        return root;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root;

        if (SessionManager.getInstance().isIsLoggedIn(getActivity())) {
            root = getMemberLayout(inflater, container);
        } else {
            root = getGuestLayout(inflater, container);
        }

        return root;
    }
}