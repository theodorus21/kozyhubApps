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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kozyhub.R;
import com.example.kozyhub.constant.Types;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.Menu;
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

public class ProfileFragment extends Fragment {

    private boolean isDoingSomething = false;

    private void doLogin(String username, String password) {
        isDoingSomething = true;

        final OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password).build();

        Request request = new Request.Builder()
                .url("https://kozyhub.com/api/categories/login.php").post(body).build();

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

                JsonAdapter<SuccessLogin> jsonAdapter = moshi.adapter(SuccessLogin.class);
                final SuccessLogin res = jsonAdapter.fromJson(jsonResponse);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res.info.equals("success")) {
                            User u = res.session_info.get(0);
                            SessionManager.getInstance().setUser(getActivity(), new User(u.PersonName, u.UserName, u.PersonEmail, u.PersonPhone1));
                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_profile_to_navigation_home);
                            Toast.makeText(getActivity(), "Welcome " + u.PersonName, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), res.result, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void doEditProfile(String username, String password) {
        isDoingSomething = true;

        final OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password).build();

        Request request = new Request.Builder()
                .url("https://kozyhub.com/api/categories/login.php").post(body).build();

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

                JsonAdapter<SuccessLogin> jsonAdapter = moshi.adapter(SuccessLogin.class);
                final SuccessLogin res = jsonAdapter.fromJson(jsonResponse);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res.info.equals("success")) {
                            User u = res.session_info.get(0);
                            SessionManager.getInstance().setUser(getActivity(), new User(u.PersonName, u.UserName, u.PersonEmail, u.PersonPhone1));
                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_profile_to_navigation_home);
                            Toast.makeText(getActivity(), "Welcome " + u.PersonName, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), res.result, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private View getGuestLayout(@NonNull LayoutInflater inflater, ViewGroup container) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        final EditText etEmail = root.findViewById(R.id.edittext_email);
        final EditText etPassword = root.findViewById(R.id.edittext_password);

        final Button btnLogin = root.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDoingSomething) {
                    Toast.makeText(getContext(), "Transcaction is progress", Toast.LENGTH_LONG).show();
                } else {
                    doLogin(etEmail.getText().toString(), etPassword.getText().toString());
                }
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
        final EditText etUsername = root.findViewById(R.id.edittext_username);
        final EditText etPhone = root.findViewById(R.id.edittext_phone);
        final EditText etEmail = root.findViewById(R.id.edittext_email);
        final Button btnSave = root.findViewById(R.id.btn_save);

        User user = SessionManager.getInstance().getUser(getActivity());
        etFullname.setText(user.PersonName);
        etUsername.setText(user.UserName);
        etPhone.setText(user.PersonPhone1);
        etEmail.setText(user.PersonEmail);

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