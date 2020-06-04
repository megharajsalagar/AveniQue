package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Sems implements Parcelable {

    @SerializedName("name")
    String name;
    @SerializedName("subject")
    ArrayList<Subject> subject;

    public Sems() {
    }

    protected Sems(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Sems> CREATOR = new Creator<Sems>() {
        @Override
        public Sems createFromParcel(Parcel in) {
            return new Sems(in);
        }

        @Override
        public Sems[] newArray(int size) {
            return new Sems[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Subject> getSubject() {
        return subject;
    }

    public void setSubject(ArrayList<Subject> subject) {
        this.subject = subject;
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
