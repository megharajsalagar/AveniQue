package com.example.forms.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forms.R;
import com.example.forms.pojo.Allbranch;
import com.example.forms.pojo.Branch;
import com.example.forms.pojo.Sems;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class semester extends AppCompatActivity {
    public Spinner spinner1, spinner2;
    public Button button;
    Firebase ref1, ref2;
    ArrayList<Branch> branchArrayList = new ArrayList<>();
    public static  Sems Sem = new Sems();
    int branch_index=0,sem_index=-1;
    private BottomNavigationView navigation;
    ImageView back_button;
    TextView progressbar_textview;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.actionbar_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        spinner1 = (Spinner) findViewById(R.id.branch_spi);
        spinner2 = (Spinner) findViewById(R.id.semester_spi);
        button = (Button) findViewById(R.id.btn_sub);
        back_button=(ImageView)findViewById(R.id.btn_back);
        progressBar=(ProgressBar)findViewById(R.id.progressBar1);
        progressbar_textview=(TextView)findViewById(R.id.txt_progressbar);
        updatebranch();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                branch_index = spinner1.getSelectedItemPosition();
                sem_index = spinner2.getSelectedItemPosition();
                Intent intent = new Intent(getApplicationContext(), subjects.class);
                Branch branch1 = branchArrayList.get(branch_index);
                Sem = branch1.getSems().get(sem_index);
                startActivity(intent);
            }

        });

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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(semester.this,entry.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Academics:
                  /*  navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);*/
                    return true;
                case R.id.Home:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    Intent intent = new Intent(semester.this,entry.class);
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









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void updatebranch() {
        progressBar.setVisibility(View.VISIBLE);
        progressbar_textview.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("");
        databaseReference.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {



                Allbranch allbranch = dataSnapshot.getValue(Allbranch.class);
                branchArrayList = allbranch.getBranch();


                List<String> branchNameList = new ArrayList<String>();
                List<String> semsList = new ArrayList<String>();


                for (Branch branch : branchArrayList) {
                    branchNameList.add(branch.getName());

                }

                // fetching sem list from single branch item.
                for (Sems sems : branchArrayList.get(0).getSems()) {
                    semsList.add(sems.getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(semester.this,
                        R.layout.spinner_dropdown_item, branchNameList);
                adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                spinner1.setAdapter(adapter);

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(semester.this,
                        R.layout.spinner_dropdown_item, semsList);
                adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                spinner2.setAdapter(adapter2);
                progressBar.setVisibility(View.INVISIBLE);
                progressbar_textview.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(semester.this, "database error", Toast.LENGTH_SHORT).show();
            }
        });


    }

   public void btn_profile_page(MenuItem item) {

        startActivity(new Intent(getApplicationContext(), Profile_page.class));
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
}
