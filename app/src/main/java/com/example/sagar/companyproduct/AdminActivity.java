package com.example.sagar.companyproduct;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.sagar.companyproduct.Adapters.AdminRecyclerAdapter;
import com.example.sagar.companyproduct.model.TaskNo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity
{

    RecyclerView recyclerView;
    AdminRecyclerAdapter adminRecyclerAdapter;
    ArrayList<TaskNo> Tasks=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        fetchTasks();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminActivity.this,AddTask.class);
                startActivity(intent);
            }
        });
    }

    private void fetchTasks()
    {
        final ProgressDialog progressDialog = new ProgressDialog(AdminActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Tasks");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                Tasks.clear();
                if(snapshot!=null) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        TaskNo task = postSnapshot.getValue(TaskNo.class);
                        if (task != null)
                            Tasks.add(task);
                        else
                            return;
                    }
                    if (Tasks != null) {
                        adminRecyclerAdapter = new AdminRecyclerAdapter(Tasks, AdminActivity.this);
                        recyclerView = findViewById(R.id.admin_recycler_view);
                        recyclerView.setAdapter(adminRecyclerAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(AdminActivity.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.addItemDecoration(new DividerItemDecoration(AdminActivity.this, DividerItemDecoration.HORIZONTAL));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}
