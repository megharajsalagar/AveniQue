package com.example.forms.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forms.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.File;
import java.util.ArrayList;

import static android.provider.Telephony.ServiceStateTable.AUTHORITY;


public class MainActivity extends AppCompatActivity {

    ListView lv;
    private BottomNavigationView navigation;
    ImageView backbutton;
    ArrayList<String> FileName=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.actionbar_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        backbutton=(ImageView)findViewById(R.id.backBtn);
        lv = (ListView)findViewById(R.id.filelist);
        FileName = GetFiles(Environment.getExternalStorageDirectory()+"/Android/data/" + getApplicationContext().getPackageName()+"/files/Download");
        lv.setAdapter(new ArrayAdapter<String>(this,R.layout.list_item, FileName));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,View v, int position, long id) {
              //  view_pdf( FileName.get(position));
                File file = new File(Environment.getExternalStorageDirectory() +"/Android/data/" + getApplicationContext().getPackageName()+"/files/Download/"+FileName.get(position));
                //File pdfFile = new File(file_path);
                Uri path = Uri.fromFile(file);
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setDataAndType(path, "application/pdf");
                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    startActivity(pdfIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        navigation = (BottomNavigationView) findViewById(R.id.navigationBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public ArrayList<String> GetFiles(String DirectoryPath) {
        ArrayList<String> MyFiles = new ArrayList<String>();
        File f = new File(DirectoryPath);
        f.mkdirs();
        File[] files = f.listFiles();
        if (files.length == 0)
            return null;
        else {
            for (int i=0; i<files.length; i++ )
                MyFiles.add(files[i].getName());
        }
        return MyFiles;
    }

   /* public void view_pdf(String fileName) {
        String file_path = Environment.getExternalStorageDirectory() +"/Android/data/" + getApplicationContext().getPackageName()+"/files/Download/"+fileName;
        File pdfFile = new File(file_path);
        //Uri photoURI = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".my.package.name.provider",pdfFile);
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        navigation.getMenu().getItem(3).setChecked(true);
        navigation.setItemIconTintList(iconsColorStates);
        navigation.setItemTextColor(textColorStates);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Academics:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    startActivity(new Intent(getApplicationContext(), semester.class));
                    return true;
                case R.id.Home:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    //Toast.makeText(entry.this, "Current is Home page", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),entry.class));
                    return true;
                case R.id.placement:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    startActivity(new Intent(getApplicationContext(), PlacementApptitudeList.class));
                    return true;
                case R.id.downloads:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

