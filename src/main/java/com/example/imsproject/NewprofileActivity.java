package com.example.imsproject;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewprofileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int ideacount;
    int[] ideanums = new int[30];
    ArrayList<String> list = new ArrayList<String>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference2,databaseReference;
    TextView useremail;
    String name;
    private FirebaseAuth firebaseAuth;
    ArrayList<String> test1 = new ArrayList<>();
    ArrayList<String> test2 = new ArrayList<>();
    ArrayList<String> test3 = new ArrayList<>();
    ArrayList<String> test4 = new ArrayList<>();
    ArrayList<String> test5 = new ArrayList<>();
    ArrayList<String> test6 = new ArrayList<>();
    ArrayList<String> testkeys = new ArrayList<String>();

    private FirebaseUser currentUser;
    View mHeaderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newprofile);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference2 = firebaseDatabase.getReference("/Users");
        databaseReference = firebaseDatabase.getReference("/Submittedideas");

       // submitters.add("abcd");
       // submitters.add("hello");

        if(currentUser == null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final Recyclerviewadapter recyclerviewadapter = new Recyclerviewadapter(this,test1,test2,test3,test4,test5,test6);
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
                        databaseReference.child(dsp.getKey()).child("Ideatag").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String value6 = dataSnapshot.getValue().toString();
                                test6.add(value6);
                                Toast.makeText(getApplicationContext(),value6,Toast.LENGTH_SHORT).show();
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


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // FloatingActionButton fab = findViewById(R.id.fab);
      /*  fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //setting the username and useremail in profile
        mHeaderView = navigationView.getHeaderView(0);
        useremail = mHeaderView.findViewById(R.id.usermail);
        String uid = currentUser.getUid();

        databaseReference2.child(uid).child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue().toString();
                useremail.setText("WELCOME  " +name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }








    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.newprofile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_submitidea) {
            startActivity(new Intent(getApplicationContext(),SubmitideaActivity.class));
        } else if (id == R.id.nav_submittedideas) {
            startActivity(new Intent(getApplicationContext(),Viewsubmittedideas.class));
        } else if (id == R.id.nav_logout) {
            firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
