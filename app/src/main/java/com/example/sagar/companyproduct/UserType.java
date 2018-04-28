package com.example.sagar.companyproduct;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sagar.companyproduct.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

public class UserType extends Fragment
{
    View view;
    MaterialEditText name,password,confirmPassword;
    Button signUp;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.activity_user_type,container,false);
        name=view.findViewById(R.id.upName);
        password=view.findViewById(R.id.upPassword);
        confirmPassword=view.findViewById(R.id.upConfirmPassword);
        signUp=view.findViewById(R.id.buttonSignUp);
        sharedPreferences=view.getContext().getSharedPreferences(CONSTANTS.USER_TYPE, Context.MODE_PRIVATE);
        final String UserType=sharedPreferences.getString(CONSTANTS.TYPE,"LOGIN");
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("User");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {
                final ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        if(Objects.equals(name.getText().toString(), ""))
                        {
                            progressDialog.dismiss();
                            Toast.makeText(view.getContext(), "Enter Your Name", Toast.LENGTH_LONG).show();
                        }
                        else if(Objects.equals(password.getText().toString(),"") || Objects.equals("", confirmPassword.getText().toString()))
                        {
                            progressDialog.dismiss();
                            Toast.makeText(view.getContext(), "Enter the Password", Toast.LENGTH_LONG).show();
                        }
                        else if(!Objects.equals(password.getText().toString(), confirmPassword.getText().toString()))
                        {
                            progressDialog.dismiss();
                            Toast.makeText(view.getContext(), "Both Passwords Do Not Match", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            User user=new User(password.getText().toString(),UserType);
                            databaseReference.child(name.getText().toString()).setValue(user);
                            Toast.makeText(view.getContext(), "SignUp Successful", Toast.LENGTH_LONG).show();
                            databaseReference.removeEventListener(this);
                            Intent intent=new Intent(view.getContext(),Login.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {
                        Toast.makeText(view.getContext(),"Error Occurred,SignUp Failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return view;
    }
}
