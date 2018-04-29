package com.example.sagar.companyproduct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class Login extends AppCompatActivity
{
    Button signIn,signUp;
    public static boolean isLogin;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(CONSTANTS.Login, MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean(CONSTANTS.ISLOGIN, false);
        sharedPreferences1=getSharedPreferences(CONSTANTS.USER_TYPE, Context.MODE_PRIVATE);
        final String UserType=sharedPreferences1.getString(CONSTANTS.TYPE,"LOGIN");
        if (isLogin) {
            if(Objects.equals(UserType, CONSTANTS.ADMIN)) {
                Intent i = new Intent(Login.this, AdminActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
            else
            {
                Intent i = new Intent(Login.this, MainUserActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        } else {
            setContentView(R.layout.activity_login);
            signIn = findViewById(R.id.signIn);
            signUp = findViewById(R.id.signUp);

            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, SignIn.class);
                    startActivity(intent);
                }
            });

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, SignUp.class);
                    startActivity(intent);
                }
            });
        }
    }
}
