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
import com.google.firebase.auth.FirebaseAuth;

import pl.droidsonroids.gif.GifImageView;

public class ResetPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        GifImageView p=findViewById(R.id.loading_reset);
        TextView email=findViewById(R.id.email_reset);
        Button  b=findViewById(R.id.reset_button);
        FirebaseAuth fauth=FirebaseAuth.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setVisibility(View.VISIBLE);
                Intent i=new Intent(v.getContext(),MainActivity.class);
                fauth.sendPasswordResetEmail(email.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {

                            Toast.makeText(ResetPass.this, "check mail", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(ResetPass.this, "Errror:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            p.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }
}