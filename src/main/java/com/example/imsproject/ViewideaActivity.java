package com.example.imsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewideaActivity extends AppCompatActivity {

    TextView tsubmitter,ttype,tcategory,tidea;
    String submitter,category,type,idea;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewidea);

        ttype = findViewById(R.id.typeview);
        tcategory = findViewById(R.id.categoryview);
        tidea = findViewById(R.id.ideaview);
        tsubmitter = findViewById(R.id.submitterview);

        Intent intent = getIntent();
        String data = intent.getStringExtra("ideatag");
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        databaseReference = firebaseDatabase.getReference("/Submittedideas");

        databaseReference.child(data).child("Submitter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     submitter = dataSnapshot.getValue().toString();
                     tsubmitter.setText(submitter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child(data).child("Type").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                type = dataSnapshot.getValue().toString();
                ttype.setText(type);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child(data).child("Category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                category = dataSnapshot.getValue().toString();
                tcategory.setText(category);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child(data).child("Idea").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                idea = dataSnapshot.getValue().toString();
                tidea.setText(idea);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
