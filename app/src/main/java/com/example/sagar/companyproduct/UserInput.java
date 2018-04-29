package com.example.sagar.companyproduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

public class UserInput extends AppCompatActivity {

    TextView companyName;
    TextView date;
    TextView address;
    RadioButton yes;
    RadioButton no;
    EditText Reason;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);

    }
}
