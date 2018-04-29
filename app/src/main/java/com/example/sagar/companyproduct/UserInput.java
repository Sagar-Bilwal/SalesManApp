package com.example.sagar.companyproduct;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.sagar.companyproduct.model.TaskNo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class UserInput extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    TextView companyName;
    TextView date;
    TextView address;
    RadioButton yes;
    RadioButton no;
    EditText reason;
    String taskNo,address1,contact,name,company_name;
    int final_day ,final_month,final_year;
    Button save;
    String status;
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
        company_name=bundle.getString("COMPANY");
        taskNo=bundle.getString(CONSTANTS.TASK_NO);
        address1=bundle.getString("ADDRESS");
        contact=bundle.getString("CONTACT");
        name=bundle.getString("NAME");
        String Reason=" ";
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
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Tasks");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(UserInput.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                if (no.isChecked())
                {
                    status = "NO";
                } else if (yes.isChecked())
                {
                    status = "YES";
                } else {
                    status = " ";
                }
                Toast.makeText(UserInput.this, reason.getText().toString(), Toast.LENGTH_SHORT).show();
                if (no.isChecked() && Objects.equals(reason.getText().toString(), "")) {
                    progressDialog.dismiss();
                    Toast.makeText(UserInput.this, "Enter the Reason(Why was Meeting Unsuccessful ?)", Toast.LENGTH_SHORT).show();
                } else if (date.getText().toString() == "") {
                    progressDialog.dismiss();
                    Toast.makeText(UserInput.this, "Enter the date of your Visit", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            TaskNo Task = new TaskNo(company_name, address1, contact, status, reason.getText().toString()+" ", date.getText().toString(), name, taskNo + "");
                            databaseReference.child(taskNo+" "+name).setValue(Task);
                            Toast.makeText(UserInput.this, "Saved Successfully", Toast.LENGTH_LONG).show();
                            databaseReference.removeEventListener(this);
                            Intent intent = new Intent(UserInput.this, MainUserActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(UserInput.this, "Error Occurred,Saving Failed", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final_year = year;
                final_month = month + 1;
                final_day = dayOfMonth;
                date.setText("" + final_day + "/" + final_month + "/" + final_year);
            }

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
}