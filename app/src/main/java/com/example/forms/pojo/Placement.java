package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Placement implements Parcelable {

    @Expose
    @SerializedName("aptitudeQuestions")
    ArrayList<AptitudeQuestions> aptitudeQuestions;

    public Placement() {
    }

    protected Placement(Parcel in) {
        aptitudeQuestions = in.createTypedArrayList(AptitudeQuestions.CREATOR);
    }

    public static final Creator<Placement> CREATOR = new Creator<Placement>() {
        @Override
        public Placement createFromParcel(Parcel in) {
            return new Placement(in);
        }

        @Override
        public Placement[] newArray(int size) {
            return new Placement[size];
        }
    };

    public ArrayList<AptitudeQuestions> getAptitudeQuestions() {
        return aptitudeQuestions;
    }

    public void setAptitudeQuestions(ArrayList<AptitudeQuestions> aptitudeQuestions) {
        this.aptitudeQuestions = aptitudeQuestions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(aptitudeQuestions);
    }
}
