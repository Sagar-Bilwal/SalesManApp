package com.example.sagar.companyproduct;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class UserType extends Fragment
{
    View view;
    MaterialEditText name,password,confirmPassword;
    Button signUp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.activity_user_type,container,false);
        name=view.findViewById(R.id.upName);
        password=view.findViewById(R.id.upPassword);
        confirmPassword=view.findViewById(R.id.upConfirmPassword);
        signUp=view.findViewById(R.id.buttonSignUp);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("User");
        return view;
    }
}
