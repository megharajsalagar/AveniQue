package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Textbook implements Parcelable {

    @SerializedName("author_name")
    String author_name;
    @SerializedName("pdflink")
    String pdflink;

    public Textbook() {
    }

    protected Textbook(Parcel in) {
        author_name = in.readString();
        pdflink = in.readString();
    }

    public static final Creator<Textbook> CREATOR = new Creator<Textbook>() {
        @Override
        public Textbook createFromParcel(Parcel in) {
            return new Textbook(in);
        }

        @Override
        public Textbook[] newArray(int size) {
            return new Textbook[size];
        }
    };

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getPdflink() {
        return pdflink;
    }

    public void setPdflink(String pdflink) {
        this.pdflink = pdflink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author_name);
        dest.writeString(pdflink);
    }
}
