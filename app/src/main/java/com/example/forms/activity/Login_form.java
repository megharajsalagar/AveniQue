package com.example.forms.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.forms.R;
import com.example.forms.pojo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_form extends AppCompatActivity {
    EditText txt_email, txt_password;
    Button Lbutton;
    private FirebaseAuth firebaseAuth;
    String mobile_Num, collegeName, name;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.hide();
        /*getSupportActionBar().setTitle("Login Form");
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.actionbar_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/

        txt_email = (EditText) findViewById(R.id.txt_email2);
        txt_password = (EditText) findViewById(R.id.txt_password2);
        Lbutton = (Button) findViewById(R.id.btn_login);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        Lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = txt_email.getText().toString().trim();
                final String password = txt_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_form.this, "Please Enter E-mail", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_form.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(Login_form.this, "Password should be more than or equal to 6 characters", Toast.LENGTH_SHORT).show();
                } else {

                    if (netInfo!=null) {
                        if(netInfo.isConnected()) {
                            Lbutton.setVisibility(View.INVISIBLE);
                            progressBar.setVisibility(View.VISIBLE);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = database.getReference("/users/" + email.substring(0, email.length() - 4) + "");
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    if (user != null) {
                                        if (password.equals(user.getPassword())) {
                                            SaveSharedPreference.setUserName(Login_form.this, user.getEmail_id());
                                            SaveSharedPreference.setUserPassword(Login_form.this, user.getPassword());
                                            SaveSharedPreference.setUserFullname(Login_form.this, user.getName());
                                            SaveSharedPreference.setUserEmail(Login_form.this, user.getEmail_id());
                                            SaveSharedPreference.setUserMobilenum(Login_form.this, user.getMobile_number());
                                            SaveSharedPreference.setUserCollegename(Login_form.this, user.getCollege_name());
                                            Toast.makeText(Login_form.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                            startActivity(new Intent(getApplicationContext(), entry.class));
                                            finish();
                                        } else {
                                            Toast.makeText(Login_form.this, "Login failed", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(Login_form.this, "Login failed", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(Login_form.this, "Login failed", Toast.LENGTH_SHORT).show();
                                }


                            });

                        }

                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(Login_form.this).create();
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

            }

        });
    }


    public void btn_signup_form(View view) {
        startActivity(new Intent(getApplicationContext(), signup_form.class));

    }

}