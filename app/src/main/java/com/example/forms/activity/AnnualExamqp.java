package com.example.forms.activity;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.forms.R;
import com.example.forms.pojo.AnnualExamQuestionPapers;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class AnnualExamqp extends AppCompatActivity {
    FirebaseStorage firebaseStorage;
    StorageReference storageReference, ref;
    ArrayList<AnnualExamQuestionPapers> annualExamQuestionPapersArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AnnualAdapter annualAdapter;
    private  BottomNavigationView navigation;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual_examqp);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        back_button=(ImageView)findViewById(R.id.btn_back4);
        annualExamQuestionPapersArrayList = getIntent().getParcelableArrayListExtra("annualqpapers_list");
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.actionbar_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        annualAdapter = new AnnualAdapter(annualExamQuestionPapersArrayList,AnnualExamqp.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(annualAdapter);



        navigation = (BottomNavigationView) findViewById(R.id.navigation_bar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        navigation.getMenu().getItem(0).setChecked(true);
        navigation.setItemIconTintList(iconsColorStates);
        navigation.setItemTextColor(textColorStates);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void download(String pdf_link, final String fileName) {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child(pdf_link);
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();

                downloadFiles(AnnualExamqp.this, fileName, ".pdf", DIRECTORY_DOWNLOADS, url);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });



    }

    public void downloadFiles(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName );
        downloadManager.enqueue(request);

    }

    public void view_pdf(String fileName) {
        String file_path = Environment.getExternalStorageDirectory() +"/Android/data/" + getApplicationContext().getPackageName()+"/files/Download/"+fileName;
        File pdfFile = new File(file_path);
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(AnnualExamqp.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }


    public void btn_profile_page(MenuItem item) {

        startActivity(new Intent(getApplicationContext(), Profile_page.class));
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //Fragment fragment;
            switch (item.getItemId()) {
                case R.id.Academics:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    startActivity(new Intent(getApplicationContext(),semester.class));
                    return true;
                case R.id.Home:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    //Toast.makeText(entry.this, "Current is Home page", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AnnualExamqp.this,entry.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return true;
                case R.id.placement:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    startActivity(new Intent(getApplicationContext(), PlacementApptitudeList.class));
                    return true;
                case R.id.downloads:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    return true;
            }
            return false;
        }
    };



    ColorStateList iconsColorStates = new ColorStateList(
            new int[][]{
                    new int[]{-android.R.attr.state_checked},
                    new int[]{android.R.attr.state_checked}
            },
            new int[]{
                    Color.parseColor("#000000"),
                    Color.parseColor("#ff0000")
            });

    ColorStateList textColorStates = new ColorStateList(
            new int[][]{
                    new int[]{-android.R.attr.state_checked},
                    new int[]{android.R.attr.state_checked}
            },
            new int[]{
                    Color.parseColor("#000000"),
                    Color.parseColor("#ff0000")
            });
}
