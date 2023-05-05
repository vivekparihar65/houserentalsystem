package com.example.spunn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class postproperty extends AppCompatActivity {
    private Button btnroom;
    private Button btnflat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postproperty);
        btnroom=findViewById(R.id.btnroom);
        btnflat=findViewById(R.id.btnflat);
        btnroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(postproperty.this,room.class);
                startActivity(intent);
            }
        });
        btnflat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent flatintent=new Intent(postproperty.this,flat.class);
                startActivity(flatintent);
            }
        });
    }
}