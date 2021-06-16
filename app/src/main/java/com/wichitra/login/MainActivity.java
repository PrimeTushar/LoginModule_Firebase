package com.wichitra.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b= findViewById(R.id.login_b);
        Button b1=findViewById(R.id.signup_b);
        TextView f=findViewById(R.id.Reset_login);

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot=new Intent(v.getContext(),ResetPass.class);
                startActivity(forgot);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(v.getContext(),Signup.class);
                startActivity(i1);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView email=findViewById(R.id.email_login);
                TextView password=findViewById(R.id.Password_login);
                String email_s=email.toString().trim();
                String pass_s=password.toString().trim();
                FirebaseAuth Fauth= FirebaseAuth.getInstance();
                Intent i=new Intent(v.getContext(),Dashboard.class);
                GifImageView p=findViewById(R.id.loading_login);
                p.setVisibility(View.VISIBLE);

                Fauth.signInWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Fauth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful())
                                   {
                                       Toast.makeText(MainActivity.this, "user created chaeck for verification mail", Toast.LENGTH_SHORT).show();
                                       p.setVisibility(View.GONE);
                                       startActivity(i);
                                   }
                                   else
                                   {
                                       Toast.makeText(MainActivity.this, "Errror:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                   }


                                }
                            });

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Errror:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            p.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }



    }


