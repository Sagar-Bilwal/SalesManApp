package com.example.sagar.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    Button user;
    Button admin;
    EditText password;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user = findViewById(R.id.user);
        admin = findViewById(R.id.admin);
        password=findViewById(R.id.password);
        frameLayout=findViewById(R.id.signUp_frame);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(CONSTANTS.USER_TYPE, Context.MODE_PRIVATE);
                if(Objects.equals(password.getText().toString(), "anmolmittal021998"))
                {
                    frameLayout.removeAllViews();
                    sharedPreferences.edit().putString(CONSTANTS.TYPE, CONSTANTS.ADMIN).apply();
                    loadFragment(new UserType());
                }
                if(password.getText().toString()=="")
                {
                    Toast.makeText(SignUp.this, "Enter the Password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUp.this, "Enter Correct Password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.removeAllViews();
                SharedPreferences sharedPreferences = getSharedPreferences(CONSTANTS.USER_TYPE, Context.MODE_PRIVATE);
                sharedPreferences.edit().putString(CONSTANTS.TYPE, CONSTANTS.USER).apply();
                loadFragment(new UserType());
            }
        });
    }

    private void loadFragment(Fragment fragment)
    {
        FragmentManager fm = getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.signUp_frame, fragment);

        fragmentTransaction.commit();
    }
}
