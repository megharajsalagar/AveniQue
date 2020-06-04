package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnnualExamQuestionPapers implements Parcelable {

    @Expose
    @SerializedName("Year")
    String year;
    @SerializedName("pdflink")
    String pdflink;

    public AnnualExamQuestionPapers() {
    }

    protected AnnualExamQuestionPapers(Parcel in) {
        year = in.readString();
        pdflink = in.readString();
    }

    public static final Creator<AnnualExamQuestionPapers> CREATOR = new Creator<AnnualExamQuestionPapers>() {
        @Override
        public AnnualExamQuestionPapers createFromParcel(Parcel in) {
            return new AnnualExamQuestionPapers(in);
        }

        @Override
        public AnnualExamQuestionPapers[] newArray(int size) {
            return new AnnualExamQuestionPapers[size];
        }
    };

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
        dest.writeString(year);
        dest.writeString(pdflink);
    }
}
