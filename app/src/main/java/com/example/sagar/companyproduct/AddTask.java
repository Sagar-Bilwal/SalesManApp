package com.example.sagar.companyproduct;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sagar.companyproduct.model.TaskNo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTask extends AppCompatActivity {

    EditText name,companyName,contact,address;
    Button save;
    int taskNo;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        name=findViewById(R.id.add_name);
        companyName=findViewById(R.id.add_company);
        contact=findViewById(R.id.add_contact);
        address=findViewById(R.id.add_address);
        save=findViewById(R.id.button_save);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Tasks");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(AddTask.this);
                progressDialog.setMessage("Saving...");
                progressDialog.show();
                sharedPreferences=getSharedPreferences(name.getText().toString(), Context.MODE_PRIVATE);
                taskNo=sharedPreferences.getInt(CONSTANTS.TASK_NO,0);
                final String taskNO=taskNo+" "+name.getText().toString();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TaskNo Task=new TaskNo(companyName.getText().toString(),address.getText().toString(),contact.getText().toString()," "," "," ",name.getText().toString(),taskNo+"");
                        databaseReference.child(taskNO).setValue(Task);
                        Toast.makeText(AddTask.this, "Saved Successfully", Toast.LENGTH_LONG).show();
                        databaseReference.removeEventListener(this);
                        taskNo=taskNo+1;
                        sharedPreferences.edit().putInt(CONSTANTS.TASK_NO,taskNo).apply();
                        Intent intent=new Intent(AddTask.this,AdminActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(AddTask.this,"Error Occurred,Saving Failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
