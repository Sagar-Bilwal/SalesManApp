package com.example.sagar.companyproduct;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity
{
    Button signIn,signUp;
    public static boolean isLogin;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(CONSTANTS.Login, MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean(CONSTANTS.ISLOGIN, false);
        if (isLogin) {
            Intent i = new Intent(Login.this, AdminActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
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
