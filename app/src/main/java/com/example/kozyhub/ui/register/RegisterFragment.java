package com.example.kozyhub.ui.register;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.kozyhub.R;

public class RegisterFragment extends Fragment {
    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText etFullname = root.findViewById(R.id.edittext_fullname);
        final EditText etUsername= root.findViewById(R.id.edittext_username);
        final EditText etPhone= root.findViewById(R.id.edittext_phone);
        final EditText etEmail= root.findViewById(R.id.edittext_email);
        final EditText etPassword= root.findViewById(R.id.edittext_password);
        final EditText etPasswordConfirm= root.findViewById(R.id.edittext_password_confirm);
        final CheckBox cbTnc = root.findViewById(R.id.checkbox_tnc);
        final Button btnSignup = root.findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(etFullname.getText().toString(),
                        etUsername.getText().toString(),
                        etPhone.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString(),
                        etPasswordConfirm.getText().toString());
            }
        });

        return root;
    }

    private void signup(String fullname,
                        String username,
                        String phone,
                        String email,
                        String password,
                        String passwordConfirm) {

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_registerFragment_to_registerSuccessFragment);

    }

}
