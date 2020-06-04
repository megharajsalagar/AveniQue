package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InternalQuestionPapers implements Parcelable {

    @Expose
    @SerializedName("paper_name")
    String paper_name;
    @SerializedName("pdflink")
    String pdflink;

    public InternalQuestionPapers() {
    }

    protected InternalQuestionPapers(Parcel in) {
        paper_name = in.readString();
        pdflink = in.readString();
    }

    public static final Creator<InternalQuestionPapers> CREATOR = new Creator<InternalQuestionPapers>() {
        @Override
        public InternalQuestionPapers createFromParcel(Parcel in) {
            return new InternalQuestionPapers(in);
        }

        @Override
        public InternalQuestionPapers[] newArray(int size) {
            return new InternalQuestionPapers[size];
        }
    };

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
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
        dest.writeString(paper_name);
        dest.writeString(pdflink);
    }
}
