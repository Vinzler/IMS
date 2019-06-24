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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {

    String uid;
    FirebaseUser firebaseUser;
    Button signupbtn;
    EditText ed1,ed2,ed3,ed4;
    FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("/Users");



        signupbtn = findViewById(R.id.signupbutton);
        ed1 = findViewById(R.id.newemail);
        ed2 = findViewById(R.id.newpassword);
        ed3 = findViewById(R.id.name);
        ed4 = findViewById(R.id.designation);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ed1.getText().toString().trim();
                String pass = ed2.getText().toString().trim();
                final String name = ed3.getText().toString().trim();
                final String designation = ed4.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getApplicationContext(),"Please enter email!",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(getApplicationContext(),"Please enter name!",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(getApplicationContext(),"Please enter password!",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(designation))
                {
                    Toast.makeText(getApplicationContext(),"Please enter designation!",Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                                firebaseUser = firebaseAuth.getCurrentUser();
                                uid = firebaseUser.getUid();
                                databaseReference.child(uid).child("Name").setValue(name);
                                databaseReference.child(uid).child("Designation").setValue(designation);
                                databaseReference.child(uid).child("Ideas").child("Count").setValue(0);
                                Toast.makeText(getApplicationContext(),"REGISTERED SUCCESSFULLY!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),NewprofileActivity.class));



                        }
                        else{
                            Toast.makeText(getApplicationContext(),"REGISTERATION FAILED!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
