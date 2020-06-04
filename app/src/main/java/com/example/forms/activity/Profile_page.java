package com.example.forms.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forms.R;

public class Profile_page extends AppCompatActivity {

    private Button btn_logout,btn_uploadpdf;
    TextView email_textView, mobileNum_textView, collegeName_textView, fullName_textView;
    SaveSharedPreference sharedPreference = new SaveSharedPreference();
    String email, mobile_Num, collegeName, name;
    ImageView back_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        btn_logout = (Button) findViewById(R.id.logout);
        btn_uploadpdf = (Button) findViewById(R.id.txt_uploadpdf);
        email_textView = (TextView) findViewById(R.id.email);
        collegeName_textView = (TextView) findViewById(R.id.college_name);
        mobileNum_textView = (TextView) findViewById(R.id.mobile_num);
        fullName_textView = (TextView) findViewById(R.id.name);
        back_btn=(ImageView)findViewById(R.id.backbutton);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.actionbar_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        email = SaveSharedPreference.getUserEmail(Profile_page.this);
        mobile_Num = SaveSharedPreference.getUserMobilenum(Profile_page.this);
        collegeName = SaveSharedPreference.getUserCollegename(Profile_page.this);
        name = SaveSharedPreference.getUserFullname(Profile_page.this);

        email_textView.setText(email);
        mobileNum_textView.setText(mobile_Num);
        collegeName_textView.setText(collegeName);
        fullName_textView.setText(name);
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (netInfo!=null){
                if (netInfo.isConnected()) {
                    new AlertDialog.Builder(Profile_page.this)
                            //.setIcon(R.)
                            //.setTitle("Closing Activity")
                            .setMessage("Are you sure you want to logout?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SaveSharedPreference.setUserName(Profile_page.this, "");
                                    SaveSharedPreference.setUserCollegename(Profile_page.this, "");
                                    SaveSharedPreference.setUserMobilenum(Profile_page.this, " ");
                                    SaveSharedPreference.setUserEmail(Profile_page.this, "");
                                    SaveSharedPreference.setUserFullname(Profile_page.this, "");
                                    Intent intent = new Intent(Profile_page.this, Login_form.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                    }
                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Profile_page.this).create();
                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });
                    alertDialog.show();
                }
            }
        });

        btn_uploadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_page.this, pdfupload.class);
                intent.putExtra("fullname", name);
                startActivity(intent);

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
