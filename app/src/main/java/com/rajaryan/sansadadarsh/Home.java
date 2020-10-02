package com.rajaryan.sansadadarsh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;

public class Home extends AppCompatActivity {
    ViewFlipper viewFlipper;
    RoundedImageView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Main_Page_Header");
        t2=findViewById(R.id.imageView2);
        t3=findViewById(R.id.imageView1);
        t1=findViewById(R.id.imageView3);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                Glide.with(getApplicationContext()).load(ds.child("1").getValue()).fitCenter().into(t1);
                Glide.with(getApplicationContext()).load(ds.child("2").getValue()).fitCenter().into(t2);
                Glide.with(getApplicationContext()).load(ds.child("3").getValue()).fitCenter().into(t3);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void open_village(View view) {
        Intent i=new Intent(Home.this,Villages.class);
        startActivity(i);
    }

    public void open_schemes(View view) {
        Intent i=new Intent(Home.this,Schemes.class);
        startActivity(i);
    }

    public void open_mp(View view) {
        Intent i=new Intent(Home.this,mp_details.class);
        startActivity(i);
    }

    public void open_officers(View view) {
        Intent i=new Intent(Home.this,NodelActivity.class);
        startActivity(i);
    }

    public void open_tech(View view) {
        Intent i=new Intent(Home.this,Technology.class);
        startActivity(i);
    }

    public void search(View view) {
        Intent i=new Intent(Home.this,Search.class);
        startActivity(i);
    }
}
