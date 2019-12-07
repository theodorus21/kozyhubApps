package com.example.kozyhub.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Property extends Category implements Parcelable {
    public static final int TYPE_GUEST_HOUSE = 1;
    public static final int TYPE_COWORKING_SPACE = 2;
    private int PkBranch;
    private String BranchName, BranchDesc, BranchPhone1, BranchPhone2, description;
    private String pict1, pict2, pict3, pict4, pict5, pict6;

    private int propertyType;

    public void setPropertyType(int propertyType) {
        this.propertyType = propertyType;
    }

    public int getPropertyType() {
        return propertyType;
    }

    public Property(String branchName, String branchDesc, String description) {
        this.BranchName = branchName;
        this.BranchDesc = branchDesc;
        this.description = description;
    }

    public Property(int pkBranch, String branchName, String branchDesc, String branchPhone1, String branchPhone2, String description, String pict1, String pict2, String pict3, String pict4, String pict5, String pict6) {
        PkBranch = pkBranch;
        BranchName = branchName;
        BranchDesc = branchDesc;
        BranchPhone1 = branchPhone1;
        BranchPhone2 = branchPhone2;
        this.description = description;
        this.pict1 = pict1;
        this.pict2 = pict2;
        this.pict3 = pict3;
        this.pict4 = pict4;
        this.pict5 = pict5;
        this.pict6 = pict6;
    }

    protected Property(Parcel in) {
        PkBranch = in.readInt();
        BranchName = in.readString();
        BranchDesc = in.readString();
        BranchPhone1 = in.readString();
        BranchPhone2 = in.readString();
        description = in.readString();
        pict1 = in.readString();
        pict2 = in.readString();
        pict3 = in.readString();
        pict4 = in.readString();
        pict5 = in.readString();
        pict6 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(PkBranch);
        dest.writeString(BranchName);
        dest.writeString(BranchDesc);
        dest.writeString(BranchPhone1);
        dest.writeString(BranchPhone2);
        dest.writeString(description);
        dest.writeString(pict1);
        dest.writeString(pict2);
        dest.writeString(pict3);
        dest.writeString(pict4);
        dest.writeString(pict5);
        dest.writeString(pict6);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Property> CREATOR = new Creator<Property>() {
        @Override
        public Property createFromParcel(Parcel in) {
            return new Property(in);
        }

        @Override
        public Property[] newArray(int size) {
            return new Property[size];
        }
    };

    @Override
    public String getName() {
        return getBranchName();
    }

    @Override
    public String getImage() {
        return getPict1();
    }

    public int getPkBranch() {
        return PkBranch;
    }

    public void setPkBranch(int pkBranch) {
        PkBranch = pkBranch;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getBranchDesc() {
        return BranchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        BranchDesc = branchDesc;
    }

    public String getBranchPhone1() {
        return BranchPhone1;
    }

    public void setBranchPhone1(String branchPhone1) {
        BranchPhone1 = branchPhone1;
    }

    public String getBranchPhone2() {
        return BranchPhone2;
    }

    public void setBranchPhone2(String branchPhone2) {
        BranchPhone2 = branchPhone2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPict1() {
        return pict1;
    }

    public void setPict1(String pict1) {
        this.pict1 = pict1;
    }

    public String getPict2() {
        return pict2;
    }

    public void setPict2(String pict2) {
        this.pict2 = pict2;
    }

    public String getPict3() {
        return pict3;
    }

    public void setPict3(String pict3) {
        this.pict3 = pict3;
    }

    public String getPict4() {
        return pict4;
    }

    public void setPict4(String pict4) {
        this.pict4 = pict4;
    }

    public String getPict5() {
        return pict5;
    }

    public void setPict5(String pict5) {
        this.pict5 = pict5;
    }

    public String getPict6() {
        return pict6;
    }

    public void setPict6(String pict6) {
        this.pict6 = pict6;
    }
}
