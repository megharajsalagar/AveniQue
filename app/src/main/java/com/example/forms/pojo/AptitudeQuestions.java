package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AptitudeQuestions implements Parcelable {

    @Expose
    @SerializedName("name")
    String name;
    @Expose
    @SerializedName("pdfLink")
    String pdfLink;


    public AptitudeQuestions() {
    }

    protected AptitudeQuestions(Parcel in) {
        name = in.readString();
        pdfLink = in.readString();
    }

    public static final Creator<AptitudeQuestions> CREATOR = new Creator<AptitudeQuestions>() {
        @Override
        public AptitudeQuestions createFromParcel(Parcel in) {
            return new AptitudeQuestions(in);
        }

        @Override
        public AptitudeQuestions[] newArray(int size) {
            return new AptitudeQuestions[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdfLink() {
        return pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pdfLink);
    }
}
