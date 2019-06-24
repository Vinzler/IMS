package com.example.imsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubmitideaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String setname;
    String uid1;
    Double count,submittedcount;
    String selection,idea1,category1;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference1;
    TextView category,ideatext;
    Button submitbtn;
    ArrayList<String> types = new ArrayList<String>();
    Spinner ideatype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitidea);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("/Users");
        databaseReference1 = firebaseDatabase.getReference("/Submittedideas");
        uid1 = firebaseAuth.getUid();
        databaseReference.child(uid1).child("Ideas").child("Count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = dataSnapshot.getValue(Double.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference1.child("Count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                submittedcount = dataSnapshot.getValue(Double.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child(uid1).child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setname = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        submitbtn = findViewById(R.id.ideasubmitbutton);
        category = findViewById(R.id.categorytext);
        ideatext = findViewById(R.id.ideatext);
        ideatype = findViewById(R.id.ideatype);
        ideatype.setOnItemSelectedListener(this);

        types.add("New Product Idea");
        types.add("Technology lead Product Idea");
        types.add("Idea to enhance existing Product");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, types);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ideatype.setAdapter(arrayAdapter);

        //category1 = category.getText().toString().trim();
        //idea1 = ideatext.getText().toString().trim();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = firebaseAuth.getUid();
                count = count +1;
                int num = count.intValue();
                String setideanumber = "idea " + num;
                databaseReference.child(uid).child("Ideas").child(setideanumber).child("Type").setValue(selection);
                databaseReference.child(uid).child("Ideas").child(setideanumber).child("Category").setValue(category.getText().toString());
                databaseReference.child(uid).child("Ideas").child(setideanumber).child("Idea").setValue(ideatext.getText().toString());
                databaseReference.child(uid).child("Ideas").child(setideanumber).child("Submitter").setValue(setname);
                databaseReference.child(uid).child("Ideas").child("Count").setValue(count);

                submittedcount =submittedcount +1;
                int submitnum = submittedcount.intValue();
                String setsubmittedideanumber = "idea" + submitnum;
                databaseReference1.child(setsubmittedideanumber).child("Type").setValue(selection);
                databaseReference1.child(setsubmittedideanumber).child("Category").setValue(category.getText().toString());
                databaseReference1.child(setsubmittedideanumber).child("Idea").setValue(ideatext.getText().toString());
                databaseReference1.child(setsubmittedideanumber).child("Submitter").setValue(setname);
                databaseReference1.child(setsubmittedideanumber).child("SubmitterUID").setValue(uid);
                //Add ideatag attribute fro submitted idea for later viewing while upvoting
                databaseReference1.child(setsubmittedideanumber).child("Ideatag").setValue(setsubmittedideanumber);
                databaseReference1.child("Count").setValue(submittedcount);

                Toast.makeText(getApplicationContext(),"SUBMITTED SUCCESSFULLY!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),NewprofileActivity.class));

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selection = adapterView.getItemAtPosition(i).toString();

       // Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
