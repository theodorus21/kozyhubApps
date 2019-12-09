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
import android.widget.Toast;

import com.example.kozyhub.R;
import com.example.kozyhub.model.Success;
import com.example.kozyhub.model.SuccessLogin;
import com.example.kozyhub.model.User;
import com.example.kozyhub.util.session.SessionManager;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RegisterFragment extends Fragment {
    private boolean isDoingSomething = false;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText etFullname = root.findViewById(R.id.edittext_fullname);
        final EditText etUsername = root.findViewById(R.id.edittext_username);
        final EditText etPhone = root.findViewById(R.id.edittext_phone);
        final EditText etEmail = root.findViewById(R.id.edittext_email);
        final EditText etPassword = root.findViewById(R.id.edittext_password);
        final EditText etPasswordConfirm = root.findViewById(R.id.edittext_password_confirm);
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

        isDoingSomething = true;

        final OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("userName", username)
                .add("name", fullname)
                .add("email", email)
                .add("phone", phone)
                .add("password", password)
                .add("password2", passwordConfirm)
                .build();

        Request request = new Request.Builder()
                .url("https://kozyhub.com/api/categories/sign_up.php").post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                isDoingSomething = false;
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                isDoingSomething = false;

                ResponseBody responseBody = response.body();

                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                final String jsonResponse = responseBody.string();
                Moshi moshi = new Moshi.Builder().build();

                JsonAdapter<Success> jsonAdapter = moshi.adapter(Success.class);
                final Success res = jsonAdapter.fromJson(jsonResponse);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res.info.equals("success")) {
                            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                            navController.navigate(R.id.action_registerFragment_to_registerSuccessFragment);
                        } else {
                            Toast.makeText(getActivity(), res.result, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
