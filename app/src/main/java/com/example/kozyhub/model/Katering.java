package com.example.kozyhub.model;

import com.example.kozyhub.util.string.StringHelper;

public class Katering {
    public String KateringName, KateringPict, kateringType, KateringDate;
    public float KateringPrice;
    public int PkKateringScheduleDtl;

    public String formatMenuPrice() {
        return StringHelper.FormatRupiah(this.KateringPrice);
    }
}
