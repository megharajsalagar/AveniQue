package com.example.forms.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SubjectMaterials implements Parcelable {

    @Expose
    @SerializedName("textbooks")
    ArrayList<Textbook> textbooks;
    @Expose
    @SerializedName("internal_question_papers")
    ArrayList<InternalQuestionPapers> internal_question_papers;
    @Expose
    @SerializedName("annual_exam_question_papers")
    ArrayList<AnnualExamQuestionPapers> annual_exam_question_papers;
    public SubjectMaterials() {
    }

    protected SubjectMaterials(Parcel in) {
        textbooks = in.createTypedArrayList(Textbook.CREATOR);
        internal_question_papers = in.createTypedArrayList(InternalQuestionPapers.CREATOR);
        annual_exam_question_papers = in.createTypedArrayList(AnnualExamQuestionPapers.CREATOR);
    }

    public static final Creator<SubjectMaterials> CREATOR = new Creator<SubjectMaterials>() {
        @Override
        public SubjectMaterials createFromParcel(Parcel in) {
            return new SubjectMaterials(in);
        }

        @Override
        public SubjectMaterials[] newArray(int size) {
            return new SubjectMaterials[size];
        }
    };

    public ArrayList<Textbook> getTextbooks() {
        return textbooks;
    }

    public void setTextbooks(ArrayList<Textbook> textbooks) {
        this.textbooks = textbooks;
    }

    public ArrayList<InternalQuestionPapers> getInternal_question_papers() {
        return internal_question_papers;
    }

    public void setInternal_question_papers(ArrayList<InternalQuestionPapers> internal_question_papers) {
        this.internal_question_papers = internal_question_papers;
    }

    public ArrayList<AnnualExamQuestionPapers> getAnnual_exam_question_papers() {
        return annual_exam_question_papers;
    }

    public void setAnnual_exam_question_papers(ArrayList<AnnualExamQuestionPapers> annual_exam_question_papers) {
        this.annual_exam_question_papers = annual_exam_question_papers;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(textbooks);
        dest.writeTypedList(internal_question_papers);
        dest.writeTypedList(annual_exam_question_papers);

    }
}
