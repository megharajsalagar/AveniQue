package com.example.forms.activity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.forms.R;
import com.example.forms.pojo.Allbranch;
import com.example.forms.pojo.AptitudeQuestions;
import com.example.forms.pojo.Branch;
import com.example.forms.pojo.Placement;
import com.example.forms.pojo.Sems;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class PlacementApptitudeList extends AppCompatActivity {
    private  BottomNavigationView navigation;
    ImageView back_button;
    ArrayList<AptitudeQuestions> aptitudeQuestionsArrayList = new ArrayList<>();
    ListView listView;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference, ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_apptitude_list);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.actionbar_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        listView=(ListView)findViewById(R.id.lv);
        back_button=(ImageView)findViewById(R.id.button_back);
        navigation = (BottomNavigationView) findViewById(R.id.navigation_bar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        updatebranch();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void updatebranch() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("/placement");
        databaseReference.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {


                Placement placement = dataSnapshot.getValue(Placement.class);
                aptitudeQuestionsArrayList = placement.getAptitudeQuestions();


                final List<String> companyNameList = new ArrayList<String>();
                final List<String> pdflinkList = new ArrayList<String>();


                for (AptitudeQuestions aptitudeQuestions : aptitudeQuestionsArrayList) {
                    companyNameList.add(aptitudeQuestions.getName());

                }
                for (AptitudeQuestions aptitudeQuestions : aptitudeQuestionsArrayList) {
                    pdflinkList.add(aptitudeQuestions.getPdfLink());

                }


                listView.setAdapter(new ArrayAdapter<String>(PlacementApptitudeList.this,R.layout.list_item, companyNameList));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        AptitudeQuestions aptitudeQuestions = new AptitudeQuestions();
                        String pdf_link = pdflinkList.get(position);
                        /*String[] separated = pdf_link.split("/");
                        final String fileName = separated[separated.length - 1];*/
                        String file_path = Environment.getExternalStorageDirectory() + "/Android/data/com.example.forms/files/Download/" + companyNameList.get(position);
                        File file = new File(file_path);
                        if (file.exists()) {
                          view_pdf(companyNameList.get(position));
                        } else {
                            download(pdf_link,companyNameList.get(position));
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(PlacementApptitudeList.this, "database error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation.getMenu().getItem(2).setChecked(true);
        navigation.setItemIconTintList(iconsColorStates);
        navigation.setItemTextColor(textColorStates);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                    Intent intent=new Intent(PlacementApptitudeList.this,entry.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return true;
                case R.id.placement:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void btn_profile_page(MenuItem item) {
        /*name=getIntent().getStringExtra("fullname");
        email=getIntent().getStringExtra("email");
        mobile_Num=getIntent().getStringExtra("mobileNumber");
        collegeName=getIntent().getStringExtra("collegeName");*/
        Intent intent = new Intent(PlacementApptitudeList.this,Profile_page.class);
        /*intent.putExtra("fullname",name);
        intent.putExtra("email",email);
        intent.putExtra("mobileNumber",mobile_Num);
        intent.putExtra("collegeName",collegeName);*/
        startActivity(intent);
    }

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
    public void download(String pdf_link, final String fileName) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Downloading.");
        progressDialog.show();
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child(pdf_link);
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(PlacementApptitudeList.this, fileName,DIRECTORY_DOWNLOADS, url);
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });




    }

    public void downloadFiles(Context context, String fileName, String destinationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName);
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
            Toast.makeText(PlacementApptitudeList.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PlacementApptitudeList.this,entry.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
