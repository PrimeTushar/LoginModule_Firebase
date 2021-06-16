package com.wichitra.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pl.droidsonroids.gif.GifImageView;



public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button b=findViewById(R.id.Signup_b_signup);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView username=findViewById(R.id.Uname_Signup);
                TextView name=findViewById(R.id.Name_Signup);
                TextView password=findViewById(R.id.password_Signup);
                TextView email=findViewById(R.id.Email_signup);
                TextView contact=findViewById(R.id.Contact_Signup);
                GifImageView g=findViewById(R.id.Dashboard_loding);
                FirebaseAuth Fauth= FirebaseAuth.getInstance();
                //to check if user is loogedin or exist
                Intent i= new Intent(v.getContext(),Dashboard.class);
                if(Fauth.getCurrentUser()!=null)
                {
                    startActivity(i);
                }

                //Validations

                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    email.setError("Email cant be empty");
                    return;
                };
                if(TextUtils.isEmpty(username.getText().toString()))
                {
                    username.setError("Email cant be empty");
                    return;
                };
                if(TextUtils.isEmpty(name.getText().toString()))
                {
                    name.setError("Email cant be empty");
                    return;
                };
                if(TextUtils.isEmpty(password.getText().toString()))
                {
                    password.setError("Password cant be empty");
                    return;
                };
                if(TextUtils.isEmpty(contact.getText().toString()))
                {
                    if(!TextUtils.isDigitsOnly(contact.getText().toString())){

                        contact.setError("Only digits are allowed");
                        return;
                    }
                    contact.setError("contact no. cant be empty");
                    return;
                }

                g.setVisibility(View.VISIBLE);

                //Adding the user to firebase

                Fauth.createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Signup.this, "User Created", Toast.LENGTH_SHORT).show();
                            g.setVisibility(View.GONE);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(Signup.this, "Error Occurred:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            g.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }



   /* public void getData(View v){
        TextView username=findViewById(R.id.Uname_Signup);
        TextView name=findViewById(R.id.Name_Signup);
        TextView password=findViewById(R.id.password_Signup);
        TextView email=findViewById(R.id.Email_signup);
        TextView contact=findViewById(R.id.Contact_Signup);
        GifImageView g=findViewById(R.id.loading_signup);
        FirebaseAuth Fauth= FirebaseAuth.getInstance();
        //to check if user is loogedin or exist
        Intent i=new Intent(this,Dashboard.class);
        if(Fauth.getCurrentUser()!=null)
        {
            startActivity(i);
        }

        //Validations

            if(TextUtils.isEmpty(email.getText().toString()))
            {
                email.setError("Email cant be empty");
                return;
            };
            if(TextUtils.isEmpty(username.getText().toString()))
            {
                username.setError("Email cant be empty");
                return;
            };
            if(TextUtils.isEmpty(name.getText().toString()))
            {
                name.setError("Email cant be empty");
                return;
            };
            if(TextUtils.isEmpty(password.getText().toString()))
            {
                password.setError("Password cant be empty");
                return;
            };
             if(TextUtils.isEmpty(contact.getText().toString()))
            {
                if(!TextUtils.isDigitsOnly(contact.getText().toString())){

                    contact.setError("Only digits are allowed");
                    return;
                }
                contact.setError("contact no. cant be empty");
                return;
            }

            g.setVisibility(View.VISIBLE);

            //Adding the user to firebase

            Fauth.createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Signup.this, "User Created", Toast.LENGTH_SHORT).show();
                        g.setVisibility(View.GONE);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(Signup.this, "Error Occurred:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        g.setVisibility(View.GONE);
                    }
                }
            });
    }*/
}