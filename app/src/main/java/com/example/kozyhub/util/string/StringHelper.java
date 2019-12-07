package com.example.kozyhub.util.string;

import java.text.DecimalFormat;

public class StringHelper {
    public static String FormatRupiah(float n) {
        DecimalFormat d = new DecimalFormat("#,###");
        return "Rp " + d.format(n);
    }
}
