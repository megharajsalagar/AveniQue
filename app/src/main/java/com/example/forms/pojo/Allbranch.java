package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Allbranch implements Parcelable {

    @Expose
    @SerializedName("branch")
    ArrayList<Branch> branch;


    public Allbranch() {
    }

    protected Allbranch(Parcel in) {
    }

    public static final Creator<Allbranch> CREATOR = new Creator<Allbranch>() {
        @Override
        public Allbranch createFromParcel(Parcel in) {
            return new Allbranch(in);
        }

        @Override
        public Allbranch[] newArray(int size) {
            return new Allbranch[size];
        }
    };

    public ArrayList<Branch> getBranch() {
        return branch;
    }

    public void setBranch(ArrayList<Branch> branch) {
        this.branch = branch;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
