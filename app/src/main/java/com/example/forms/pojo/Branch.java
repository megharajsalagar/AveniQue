package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Branch implements Parcelable {

    @Expose
    @SerializedName("name")
    String name;
    @Expose
    @SerializedName("sems")
    ArrayList<Sems> sems;


    public Branch() {
    }

    protected Branch(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Branch> CREATOR = new Creator<Branch>() {
        @Override
        public Branch createFromParcel(Parcel in) {
            return new Branch(in);
        }

        @Override
        public Branch[] newArray(int size) {
            return new Branch[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Sems> getSems() {
        return sems;
    }

    public void setSems(ArrayList<Sems> sems) {
        this.sems = sems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
