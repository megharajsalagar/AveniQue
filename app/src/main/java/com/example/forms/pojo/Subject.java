package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Subject implements Parcelable {

    @SerializedName("name")
    String name;
    @SerializedName("materials")
    SubjectMaterials materials;

    public Subject() {
    }

    protected Subject(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectMaterials getMaterials() {
        return materials;
    }

    public void setMaterials(SubjectMaterials materials) {
        this.materials = materials;
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
