package com.example.spunn;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.spunn.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;


public class dashboard extends AppCompatActivity {
    private Spinner spinnercities;
    private MaterialButtonToggleGroup togglegroup;
    private MaterialButtonToggleGroup togglegroup2;
    private MaterialButton btnstudent;
    private MaterialButton btnfamily;
    private MaterialButton btnmale;
    private MaterialButton btnfemale;
    private MaterialButton btnroom;
    private MaterialButton btnflat;
    private Button btnsearch;
    private TextView pstprpty;
    private ImageButton imgbtnig;
    private ImageButton imgbtnfb;
    private ImageButton imgbtnwhat;
    private ImageButton imgbtntwit;
    private BottomNavigationView btmnav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        spinnercities=findViewById(R.id.spinnercities);
        togglegroup=findViewById(R.id.togglegroup);
        togglegroup2=findViewById(R.id.togglegroup2);
        btnfamily=findViewById(R.id.btnfamily);
        btnstudent=findViewById(R.id.btnstudent);
        btnmale=findViewById(R.id.btnmale);
        btnfemale=findViewById(R.id.btnfemale);
        btnroom=findViewById(R.id.btnroom);
        btnflat=findViewById(R.id.btnflat);
        btnsearch=findViewById(R.id.btnsearch);
        pstprpty=findViewById(R.id.pstprpty);
        imgbtnfb=findViewById(R.id.imgbtnfb);
        imgbtnig=findViewById(R.id.imgbtnig);
        imgbtntwit=findViewById(R.id.imgbtntwit);
        imgbtnwhat=findViewById(R.id.imgbtnwhat);


        btmnav_view=findViewById(R.id.btmnav_view);


        //for search button;
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(dashboard.this,search.class));
            }
        });


        //for posting property;
        pstprpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,postproperty.class));
            }
        });
        // for navigation system;

    //set home selected
        btmnav_view.setSelectedItemId(R.id.btnhome);
        //perform item selected listener
        btmnav_view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                switch (item.getItemId())
                {
                    //for moving on home page/or it will stay in same page (dasboard).
                    case R.id.btnhome:
                        startActivity(new Intent(getApplicationContext(),dashboard.class));

                    //for moving on profile page.
                    case R.id.btnprofile:
                        Intent i=new Intent(dashboard.this,Profile.class);
                        startActivity(i);
                      //  startActivity(new Intent(getApplicationContext(),Profile.class));

                    //for moving on post property page.
                    case R.id.btnpost:
                        startActivity(new Intent(getApplicationContext(),postproperty.class));

                    //for moving on my post page.
                    case R.id.btnMyPosts:
                        startActivity(new Intent());
                    //for exit from the application.
                    case R.id.btnexit:
                        //it will show a dialog box with the given message.
                        AlertDialog.Builder builder=new AlertDialog.Builder(dashboard.this);
                        builder.setMessage("Do You Want To Exit?");
                        builder.setCancelable(true);

                        //if user click yes this will close the application.
                        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        //if user click no this will remain open the application.
                        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                }
                return true;
            }
        });

    }

    // for Facebook button
    public void facebook(View view)
    {
        Intent fb= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
        startActivity(fb);

    }
    // for Instagram button
    public void instagram(View view)
    {
        Intent ig= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"));
        startActivity(ig);

    }
    // for twitter button
    public void twitter(View view)
    {
        Intent twt= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com"));
        startActivity(twt);

    }
    // for whatsapp button
    public void whatsapp(View view)
    {
        Intent twt= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.whatsapp.com"));
        startActivity(twt);

    }
    // for menu options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. //
        int id=item.getItemId();
        // it will tel about the developer.
        if(id==R.id.about)
        {
            AlertDialog about=new AlertDialog.Builder(dashboard.this).setTitle("About Developer").
                    setIcon(android.R.drawable.ic_menu_agenda).setMessage
                    ("Name : VIVEK PARIHAR\n"+" Email : vivekparihar576@gmail.com\n").show();
        }
        //this for sign out  from the the application
        else if (id==R.id.logout)
        {
           super.onBackPressed();
           Intent intent=new Intent(getApplicationContext(),MainActivity.class);
           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
