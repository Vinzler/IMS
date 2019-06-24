package com.example.imsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrialActivity extends AppCompatActivity {

    ArrayList<String> test1 = new ArrayList<>();
    ArrayList<String> test2 = new ArrayList<>();
    ArrayList<String> test3 = new ArrayList<>();
    ArrayList<String> test4 = new ArrayList<>();
    ArrayList<String> test5 = new ArrayList<>();

    ArrayList<String> testkeys = new ArrayList<String>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("/Submittedideas");
/*
        final RecyclerView recyclerView = findViewById(R.id.rec1);
        final Recyclerviewadapter recyclerviewadapter = new Recyclerviewadapter(this,test1,test2,test3,test4,test5);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    if(!(dsp.getKey().equals("Count")))
                    {
                        testkeys.add(dsp.getKey());
                        databaseReference.child(dsp.getKey()).child("Submitter").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String value1 = dataSnapshot.getValue().toString();
                                test1.add(value1);
                                Toast.makeText(getApplicationContext(),value1,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        databaseReference.child(dsp.getKey()).child("Category").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String value2 = dataSnapshot.getValue().toString();
                                test2.add(value2);
                                Toast.makeText(getApplicationContext(),value2,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        databaseReference.child(dsp.getKey()).child("Type").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String value3 = dataSnapshot.getValue().toString();
                                test3.add(value3);
                                Toast.makeText(getApplicationContext(),value3,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        databaseReference.child(dsp.getKey()).child("SubmitterUID").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String value4 = dataSnapshot.getValue().toString();
                                test4.add(value4);
                                Toast.makeText(getApplicationContext(),value4,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        databaseReference.child(dsp.getKey()).child("Idea").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String value5 = dataSnapshot.getValue().toString();
                                test5.add(value5);
                                recyclerView.setAdapter(recyclerviewadapter);
                                Toast.makeText(getApplicationContext(),value5,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }
                //Toast.makeText(getApplicationContext(),arr,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




*/


    }


}
