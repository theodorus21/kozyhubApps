package com.example.kozyhub.ui.catering;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kozyhub.R;
import com.example.kozyhub.model.Catering;
import com.example.kozyhub.model.CateringBooking;
import com.example.kozyhub.model.Success;
import com.example.kozyhub.ui.menu_detail.MenuDetailFragmentArgs;
import com.example.kozyhub.util.session.SessionManager;
import com.google.gson.Gson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingSummaryFragment extends Fragment {
    private boolean isDoingSomething = false;

    private Catering catering;
    private CateringBooking cb;
    private TextView tvSummary;
    private Button btnConfirm;

    private String fkCatering = "";
    private String qty = "";

    public BookingSummaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_booking_summary, container, false);

        this.catering = BookingSummaryFragmentArgs.fromBundle(getArguments()).getCatering();
        this.cb = BookingSummaryFragmentArgs.fromBundle(getArguments()).getCateringBooking();

        tvSummary = root.findViewById(R.id.tv_booking_summary);
        btnConfirm = root.findViewById(R.id.btn_confirm);

        tvSummary.setText(generateString());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDoingSomething) {
                    Toast.makeText(getContext(), "Transcaction is progress", Toast.LENGTH_LONG).show();
                } else {
                    doConfirm();
                }
            }
        });

        return root;
    }

    private String generateString() {
        String x = "";

        Set<Integer> keys = cb.bookings.keySet();
        Iterator<Integer> itr = keys.iterator();

        boolean isFirst = true;
        while (itr.hasNext()) {
            Integer fk = itr.next();
            CateringBooking.Booking booking = cb.bookings.get(fk);
            x += booking.katering.KateringDate + "\n";
            x += booking.katering.kateringType + "\n";
            x += booking.katering.KateringName + " (" + booking.katering.formatMenuPrice() + "): " + booking.quantity + "\n";
            x += "========================================\n";

            if (!isFirst) {
                fkCatering += ";";
                qty += ";";
            } else {
                isFirst = false;
            }

            fkCatering += booking.katering.PkKateringScheduleDtl;
            qty += booking.quantity;
        }

        return x;
    }

    private void doConfirm() {
        isDoingSomething = true;

        final OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("fkPerson", SessionManager.getInstance().getUser(getActivity()).PkPerson + "")
                .add("fkBranch", catering.getPkBranch() + "")
                .add("fkCatering", fkCatering)
                .add("qty", qty)
                .build();

        Request request = new Request.Builder()
                .url("https://kozyhub.com/api/categories/send_catering.php").post(body).build();

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
                            navController.navigate(R.id.action_bookingSummaryFragment_to_bookingSuccessFragment);
                        } else {
                            Toast.makeText(getActivity(), res.result, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
