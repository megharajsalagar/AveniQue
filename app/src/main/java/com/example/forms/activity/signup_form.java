package com.example.forms.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.forms.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup_form extends AppCompatActivity {
    EditText txtEmail,txtPassword,txtConfirmPassword,txtName,mobileNum,college_name;
    Button btn_register;
    private FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.hide();
        txtName=(EditText)findViewById(R.id.txt_Name);
        txtEmail=(EditText)findViewById(R.id.txt_email);
        txtPassword=(EditText)findViewById(R.id.txt_password);
        txtConfirmPassword=(EditText)findViewById(R.id.txt_confirmpassword);
        mobileNum=(EditText)findViewById(R.id.txt_mobile_num);
        college_name=(EditText)findViewById(R.id.txt_college_name);
        btn_register=(Button)findViewById(R.id.btn_register);
        firebaseAuth=FirebaseAuth.getInstance();


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=txtEmail.getText().toString().trim();
                final String password=txtPassword.getText().toString().trim();
                String confirmpassword=txtConfirmPassword.getText().toString().trim();
                final String mobile_Num=mobileNum.getText().toString().trim();
                 final String name=txtName.getText().toString().trim();
                final String collegeName=college_name.getText().toString().trim();
               /* DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference userNameRef =  myRootRef.child(name+"Details");
                DatabaseReference cadidateNameRef=userNameRef.child("Name");
                cadidateNameRef.setValue(name);
                DatabaseReference mobileNumberRef=userNameRef.child("MobileNumber");
                mobileNumberRef.setValue(mobil_Num);
                DatabaseReference collegeNameRef=userNameRef.child("CollegeName");
                collegeNameRef.setValue(collegeName);
                DatabaseReference emailIdRef=userNameRef.child("EmailId");
                emailIdRef.setValue(email);*/
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(signup_form.this, " Enter Name", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(signup_form.this, " Enter E-mail", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(signup_form.this, " Enter password", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(mobile_Num)){
                    Toast.makeText(signup_form.this, " Enter mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(collegeName)){
                    Toast.makeText(signup_form.this, " Enter college name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(confirmpassword)){
                    Toast.makeText(signup_form.this, " Enter Confirm Password", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<6){
                    Toast.makeText(signup_form.this, "Password should be more than or equal to 6 characters", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.equals(confirmpassword)) {
                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(signup_form.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            SaveSharedPreference.setUserName(signup_form.this,email);
                                            SaveSharedPreference.setUserFullname(signup_form.this,name);
                                            SaveSharedPreference.setUserEmail(signup_form.this,email);
                                            SaveSharedPreference.setUserMobilenum(signup_form.this,mobile_Num);
                                            SaveSharedPreference.setUserCollegename(signup_form.this,collegeName);
                                            DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference("users");
                                            DatabaseReference userNameRef =  myRootRef.child(email.substring(0,email.toString().length()-4));
                                            DatabaseReference cadidateNameRef=userNameRef.child("name");
                                            cadidateNameRef.setValue(name);
                                            DatabaseReference mobileNumberRef=userNameRef.child("mobile_number");
                                            mobileNumberRef.setValue(mobile_Num);
                                            DatabaseReference collegeNameRef=userNameRef.child("college_name");
                                            collegeNameRef.setValue(collegeName);
                                            DatabaseReference emailIdRef=userNameRef.child("email_id");
                                            emailIdRef.setValue(email);
                                            DatabaseReference passwordRef=userNameRef.child("password");
                                            passwordRef.setValue(password);
                                            Intent intent = new Intent(signup_form.this,entry.class);
                                            intent.putExtra("fullname",name);
                                            intent.putExtra("email",email);
                                            intent.putExtra("mobileNumber",mobile_Num);
                                            intent.putExtra("collegeName",collegeName);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            Toast.makeText(signup_form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(signup_form.this, "Registration Failed/E-mail already exist", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }


            }
        });
    }
}
