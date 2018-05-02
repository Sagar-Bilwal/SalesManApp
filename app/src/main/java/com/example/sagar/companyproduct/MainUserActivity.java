package com.example.sagar.companyproduct;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sagar.companyproduct.Adapters.AdminRecyclerAdapter;
import com.example.sagar.companyproduct.Adapters.UserRecyclerAdapter;
import com.example.sagar.companyproduct.model.TaskNo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainUserActivity extends AppCompatActivity implements UserRecyclerAdapter.onItemClickListener {

    RecyclerView recyclerView;
    UserRecyclerAdapter userRecyclerAdapter;
    ArrayList<TaskNo> Tasks=new ArrayList<>();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        sharedPreferences = getSharedPreferences(CONSTANTS.USER_TYPE, MODE_PRIVATE);
        fetchTasks();
    }

    private void fetchTasks()
    {
        final ProgressDialog progressDialog = new ProgressDialog(MainUserActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Tasks");
        final String userName=sharedPreferences.getString(CONSTANTS.USERNAME,"");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                progressDialog.dismiss();
                Tasks.clear();
                if(snapshot!=null) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        TaskNo task = postSnapshot.getValue(TaskNo.class);
                        if (task != null)
                        {
                            if(Objects.equals(task.getName(), userName))
                            Tasks.add(task);
                        }
                        else
                            return;
                    }
                    if (Tasks != null) {
                        userRecyclerAdapter = new UserRecyclerAdapter(Tasks, MainUserActivity.this,MainUserActivity.this);
                        recyclerView = findViewById(R.id.user_recycler_view);
                        recyclerView.setAdapter(userRecyclerAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainUserActivity.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.addItemDecoration(new DividerItemDecoration(MainUserActivity.this, DividerItemDecoration.HORIZONTAL));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(MainUserActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout)
        {
            SharedPreferences login=getSharedPreferences(CONSTANTS.Login,MODE_PRIVATE);
            login.edit().putBoolean(CONSTANTS.ISLOGIN,false).apply();
            Intent intent=new Intent(MainUserActivity.this,Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(int position)
    {
        String taskNo=Tasks.get(position).getTaskNo();
        String companyName=Tasks.get(position).getCompanyName();
        String address=Tasks.get(position).getAddress();
        String contact=Tasks.get(position).getContact();
        String date=Tasks.get(position).getDate();
        String name=Tasks.get(position).getName();
        String reason=Tasks.get(position).getReason();
        String status=Tasks.get(position).getStatus();
        Intent intent =new Intent(MainUserActivity.this,UserInput.class);
        Bundle bundle=new Bundle();
        bundle.putString(CONSTANTS.TASK_NO,taskNo);
        bundle.putString("COMPANY",companyName);
        bundle.putString("ADDRESS",address);
        bundle.putString("CONTACT",contact);
        bundle.putString("DATE",date);
        bundle.putString("REASON",reason);
        bundle.putString("NAME",name);
        bundle.putString("STATUS",status);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

