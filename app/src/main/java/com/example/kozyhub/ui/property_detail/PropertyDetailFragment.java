package com.example.kozyhub.ui.property_detail;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kozyhub.R;
import com.example.kozyhub.constant.Types;
import com.example.kozyhub.constant.URL;
import com.example.kozyhub.model.Cafe;
import com.example.kozyhub.model.Category;
import com.example.kozyhub.model.Property;
import com.example.kozyhub.model.Success;
import com.example.kozyhub.model.User;
import com.example.kozyhub.ui.cafe.CafeDetailFragmentArgs;
import com.example.kozyhub.util.session.SessionManager;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PropertyDetailFragment extends Fragment {
    private TextView tvName, tvShortDescription, tvLongDescription;
    private ImageView ivImage;

    private View formInquiry;
    private EditText etDate, etDuration, etRoom, etName, etEmail, etPhone, etNotes;
    private Button btnSubmit;

    private Property property;
    private boolean isSendingInquiry = false;

    public PropertyDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_property_detail, container, false);

        tvName = root.findViewById(R.id.name);
        tvShortDescription = root.findViewById(R.id.description_short);
        tvLongDescription = root.findViewById(R.id.description_long);
        ivImage = root.findViewById(R.id.image);
        formInquiry = root.findViewById(R.id.form_inquiry);
        etDate = root.findViewById(R.id.edittext_date);
        etDuration = root.findViewById(R.id.edittext_duration);
        etRoom = root.findViewById(R.id.edittext_room);
        etName = root.findViewById(R.id.edittext_name);
        etEmail = root.findViewById(R.id.edittext_email);
        etPhone = root.findViewById(R.id.edittext_phone);
        etNotes = root.findViewById(R.id.edittext_notes);
        btnSubmit = root.findViewById(R.id.btn_submit);

        if (SessionManager.isIsLoggedIn()) {
            formInquiry.setVisibility(View.VISIBLE);

            User user = SessionManager.getUser();
            etName.setText(user.getName());
            etEmail.setText(user.getEmail());
            etPhone.setText(user.getPhone());
        }

        property = (Property) CafeDetailFragmentArgs.fromBundle(getArguments()).getCategory();
        if (property != null) {
            tvName.setText(property.getName());
            tvShortDescription.setText(property.getBranchDesc());
            tvLongDescription.setText(property.getDescription());
            Glide.with(getActivity()).load(URL.BaseURL + property.getImage()).into(ivImage);
        }
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSendingInquiry) {
                    Toast.makeText(getContext(), "Transcaction is progress", Toast.LENGTH_LONG).show();
                } else {
                    sendInquiry(etDate.getText().toString(), etDuration.getText().toString(), etRoom.getText().toString(),
                            etName.getText().toString(), etEmail.getText().toString(), etPhone.getText().toString(),
                            property.getPkBranch() + "", etNotes.getText().toString(), property.getPropertyType() + "");
                }
            }
        });

        return root;
    }

    public void sendInquiry(String tgl, String dur, String qty, String name, String email, String phone, String loc, String note, String type) {
        isSendingInquiry = true;

        final OkHttpClient client = new OkHttpClient();

        System.out.println("tgl:" + tgl);
        System.out.println("dur:" + dur);
        System.out.println("qty:" + qty);
        System.out.println("name:" + name);
        System.out.println("email:" + email);
        System.out.println("phone:" + phone);
        System.out.println("loc:" + loc);
        System.out.println("note:" + note);
        System.out.println("type:" + type);

        RequestBody body = new FormBody.Builder()
                .add("tgl", tgl)
                .add("dur", dur)
                .add("qty", qty)
                .add("name", name)
                .add("email", email)
                .add("phone", phone)
                .add("loc", loc)
                .add("note", note)
                .add("type", type).build();

        Request request = new Request.Builder()
                .url("https://kozyhub.com/api/categories/inquiry.php").post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                isSendingInquiry = false;
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                isSendingInquiry = false;

                ResponseBody responseBody = response.body();

                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                String jsonResponse = responseBody.string();
                Moshi moshi = new Moshi.Builder().build();

                JsonAdapter<Success> jsonAdapter = moshi.adapter(Success.class);
                final Success res = jsonAdapter.fromJson(jsonResponse);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res.info.equals("success")) {
                            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                            navController.navigate(R.id.action_propertyDetailFragment_to_thankyouFragment);
                        } else {
                            Toast.makeText(getActivity(), res.result, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setEtDate(etDate);
        newFragment.show(this.getActivity().getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        private EditText etDate;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void setEtDate(EditText etDate) {
            this.etDate = etDate;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            etDate.setText(String.format("%d-%2d-%2d", year, month + 1, day));
        }
    }


}
