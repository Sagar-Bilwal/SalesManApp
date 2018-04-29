package com.example.sagar.companyproduct;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Random;

public class UserInput extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    TextView companyName;
    TextView date;
    TextView address;
    RadioButton yes;
    RadioButton no;
    EditText reason;
    int final_day ,final_month,final_year;
    Button save;
    int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        companyName=findViewById(R.id.user_input_company);
        date=findViewById(R.id.user_input_date);
        address=findViewById(R.id.user_input_address);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);
        reason=findViewById(R.id.user_input_reason);
        save=findViewById(R.id.user_input_save);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String company_name=bundle.getString("COMPANY");
        String taskNo=bundle.getString(CONSTANTS.TASK_NO);
        String address1=bundle.getString("ADDRESS");
        String contact=bundle.getString("CONTACT");
        String name=bundle.getString("NAME");
        companyName.setText(company_name);
        address.setText(address1);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(UserInput.this,UserInput.this,year,month,day);
                datePickerDialog.show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        final_year=year;
        final_month=month+1;
        final_day=dayOfMonth;
        date.setText(""+final_day+"/"+final_month+"/"+final_year);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}
