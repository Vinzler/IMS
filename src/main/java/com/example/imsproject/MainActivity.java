package com.example.imsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button loginbtn;
    EditText email,pass;
    ImageView logo1;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),NewprofileActivity.class));
        }

        loginbtn = findViewById(R.id.loginbutton);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        signup = findViewById(R.id.signuptext);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString().trim();
                String pass1 = pass.getText().toString().trim();

                if(TextUtils.isEmpty(email1))
                {
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER EMAIL ID",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pass1))
                {
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER PASSWORD",Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {


                            Toast.makeText(getApplicationContext(),"WELCOME!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),NewprofileActivity.class));
                        }
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
            }
        });

    }
}
