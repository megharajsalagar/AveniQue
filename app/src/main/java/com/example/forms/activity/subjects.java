package com.example.forms.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.forms.R;
import com.example.forms.pojo.AnnualExamQuestionPapers;
import com.example.forms.pojo.Branch;
import com.example.forms.pojo.InternalQuestionPapers;
import com.example.forms.pojo.Sems;
import com.example.forms.pojo.Subject;
import com.example.forms.pojo.SubjectMaterials;
import com.example.forms.pojo.Textbook;

import java.util.ArrayList;

public class subjects extends AppCompatActivity {
    TextView txt4;
    Branch branch1;
    int sem_index;
    int num,num2;
    ListView listView;
    //RecyclerView recyclerView;
    ArrayList<Subject> subjectArrayList = new ArrayList<Subject>();
    ArrayList<Sems> semsArrayList;
    ArrayList<String> subjectNameList = new ArrayList<>();
    private BottomNavigationView navigation;
    ImageView back_button;
    //public SubjectAdapter subjectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        getSupportActionBar().setTitle("Subjects");
        //recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        listView=(ListView)findViewById(R.id.lv);
        back_button=(ImageView)findViewById(R.id.btn_back2);
        subjectArrayList = semester.Sem.getSubject();// getIntent().getParcelableArrayListExtra("subject_list");
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.actionbar_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        updatesubjects();



        navigation = (BottomNavigationView) findViewById(R.id.navigation_bar1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                subjectNameList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Subject subject = subjectArrayList.get(position);
                final SubjectMaterials subjectMaterials = subject.getMaterials();


                AlertDialog.Builder builder = new AlertDialog.Builder(subjects.this);
                builder.setTitle("Select Material")
                        .setItems(R.array.materials, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int position) {
                                switch (position){
                                    case 0 :
                                        ArrayList<Textbook> textbookArrayList = subjectMaterials.getTextbooks();
                                        Intent nextIntent = new Intent(subjects.this,list.class);
                                        nextIntent.putExtra("position",position);
                                        nextIntent.putExtra("textbook_list",textbookArrayList);
                                        startActivity(nextIntent);
                                        //send it to next
                                        break;
                                    case 1 :
                                        ArrayList<AnnualExamQuestionPapers> annualExamQuestionPapersArrayList = subjectMaterials.getAnnual_exam_question_papers();
                                        Intent nextIntent2 = new Intent(subjects.this,AnnualExamqp.class);
                                        nextIntent2.putExtra("position",position);
                                        nextIntent2.putExtra("annualqpapers_list",annualExamQuestionPapersArrayList);
                                        startActivity(nextIntent2);
                                        break;
                                }

                                dialog.cancel();
                            }
                        });
               AlertDialog dialog =  builder.create();
               dialog.show();



            }
        });


    /*    txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject= txt4.getText().toString();
                Intent intent = new Intent(getApplicationContext(), list.class);
                intent.putExtra("Subject",subject);
                intent.putExtra("Branch",branch1);
                intent.putExtra("sem",sem_index);
                startActivity(intent);
            }
        });*/

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

        private void updatesubjects() {

             // get subject names
            for (Subject subject : subjectArrayList){
                subjectNameList.add(subject.getName());
            }



        }


    public void btn_profile_page(MenuItem item) {

        startActivity(new Intent(getApplicationContext(), Profile_page.class));
    }


  /*  public void btn_academics_page(MenuItem item) {
        startActivity(new Intent(getApplicationContext(),semester.class));

    }

    public void btn_home_page(MenuItem item) {
        startActivity(new Intent(getApplicationContext(),entry.class));
    }

    public void btn_place_page(MenuItem item) {
        startActivity(new Intent(getApplicationContext(),entry.class));

    }*/
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
