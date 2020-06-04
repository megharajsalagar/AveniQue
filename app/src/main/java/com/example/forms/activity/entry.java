package com.example.forms.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.forms.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class entry extends AppCompatActivity {
    Button button1,button2;
    TextView txt1,txt2;
    TextView image1,image2,image3,image4;
    String name,mobile_Num,collegeName,email;
   // ViewPager viewPager;
    ViewFlipper viewFlipper;
    ImageView imageView;
    private  BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.actionbar_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        txt1=(TextView)findViewById(R.id.acd_txt);
        txt2=(TextView)findViewById(R.id.place_txt);
        viewFlipper=(ViewFlipper)findViewById(R.id.viewFlipper);
        int images[] = {R.drawable.s_1, R.drawable.banner1};
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), semester.class));

            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PlacementApptitudeList.class));
            }
        });
        navigation = (BottomNavigationView) findViewById(R.id.navigation_bar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        for(int image:images){
            flipperImages(image);

        }
    }

    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(image);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        viewFlipper.addView(imageView);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        navigation.getMenu().getItem(1).setChecked(true);
        navigation.setItemIconTintList(iconsColorStates);
        navigation.setItemTextColor(textColorStates);
    }

    @Override
    public void onBackPressed() {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.actionbar_icon)
                    .setMessage("Are you sure you want to exit ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           /*Intent intent = new Intent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);*/
                          finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
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
                    //startActivity(new Intent(getApplicationContext(),entry.class));
                    return true;
                case R.id.placement:
                    navigation.setItemIconTintList(iconsColorStates);
                    navigation.setItemTextColor(textColorStates);
                    startActivity(new Intent(getApplicationContext(),PlacementApptitudeList.class));
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
        Intent intent = new Intent(entry.this,Profile_page.class);
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
}


