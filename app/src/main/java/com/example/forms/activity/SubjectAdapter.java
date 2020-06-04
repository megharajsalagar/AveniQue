
package com.example.forms.activity;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forms.R;
import com.example.forms.pojo.Subject;
import com.example.forms.pojo.Textbook;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Textbook> textbookArrayList;
    FirebaseStorage firebaseStorage;
    int position;
    StorageReference storageReference, ref;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public Button txt_view, txt_download;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            //txt_view = (Button) view.findViewById(R.id.view);
            txt_download = (Button) view.findViewById(R.id.download);
            GradientDrawable drawable = (GradientDrawable)txt_download.getBackground();
            drawable.setStroke(2, Color.parseColor("#008080"));

        }
    }


    public SubjectAdapter(ArrayList<Textbook> list_texbooks, Context context) {
        this.textbookArrayList = list_texbooks;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Textbook textbook = textbookArrayList.get(i);
        viewHolder.title.setText(textbook.getAuthor_name());
        String pdf_link = textbook.getPdflink();
        String[] separated = pdf_link.split("/");
        final String fileName = separated[separated.length-1];
        String file_path = Environment.getExternalStorageDirectory() +"/Android/data/com.example.forms/files/Download/"+fileName;
        File file = new File (file_path);
        if(file.exists()){
            viewHolder.txt_download.setText("Read");
        }
        else{
            viewHolder.txt_download.setText("Download");
        }
        viewHolder.txt_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdf_link = textbook.getPdflink();
                String[] separated = pdf_link.split("/");
                final String fileName = separated[separated.length-1];
                if(viewHolder.txt_download.getText().equals("Read")){
                    if(context instanceof list) {
                        ((list)context).view_pdf(fileName);
                    }
                }
                else {
                    if(context instanceof list){
                        ((list)context).download(pdf_link,fileName);
                    }
                    viewHolder.txt_download.setText("Read");
                }

            }
        });
       /* viewHolder.txt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdf_link = textbook.getPdflink();
                String[] separated = pdf_link.split("/");
                final String fileName = separated[separated.length-1];
                String file_path = Environment.getExternalStorageDirectory() +"/Android/data/com.example.forms/files/Download/"+fileName;
                File file = new File (file_path);
                if(!file.exists()) {
                    //Toast.makeText(context, "File does not exist download it", Toast.LENGTH_SHORT).show();
                    ((list)context).download(pdf_link,fileName);
                }
                 else {
                    if (context instanceof list) {
                        ((list) context).view_pdf(fileName);
                    }
                }
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return textbookArrayList.size();
    }

}