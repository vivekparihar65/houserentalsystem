package com.example.spunn;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.spunn.databinding.ActivityFlatBinding;

public class flat extends AppCompatActivity {

    ImageView uploadimg;
    Button choosebtn;
    Button btnpstad;
    private BottomNavigationView btmnav_view;

    private static  final int image_pick_code=1000;
    private static  final int PERMISSION_CODE=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        uploadimg=findViewById(R.id.uploadimg);
        choosebtn=findViewById(R.id.choosebtn);
        btmnav_view=findViewById(R.id.btmnav_view);
        btnpstad=findViewById(R.id.btnpstad);

        //for posting add
        btnpstad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(flat.this, "Uploaded flat detail succesfully!!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(flat.this,dashboard.class);
                startActivity(intent);
            }
        });

        //handle button click.
        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check  runtime permission
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    {
                        //permission not granted ,request it
                        String [] permission={Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup fro runtime permission
                        requestPermissions(permission,PERMISSION_CODE);
                    }
                    else {
                        //permission already granted
                        pickImageFromGallery();
                    }
                }else {
                    //system os is less then oreo
                    pickImageFromGallery();
                }
            }
        });
        //set POST selected
        btmnav_view.setSelectedItemId(R.id.btnpost);
        //perform item selected listener
        btmnav_view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    //for moving on home page/or it will stay in same page (dasboard).
                    case R.id.btnhome:
                        startActivity(new Intent(getApplicationContext(),dashboard.class));

                        //for moving on profile page.
                    case R.id.btnprofile:
                        startActivity(new Intent());

                        //for moving on post property page.
                    case R.id.btnpost:
                        startActivity(new Intent(getApplicationContext(),postproperty.class));

                        //for moving on my post page.
                    case R.id.btnMyPosts:
                        startActivity(new Intent());
                        //for exit from the application.
                    case R.id.btnexit:
                        //it will show a dialog box with the given message.
                        AlertDialog.Builder builder=new AlertDialog.Builder(flat.this);
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
    // for menu options


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. //
        int id=item.getItemId();
        // it will tel about the developer.
        if(id==R.id.about)
        {
            AlertDialog about=new AlertDialog.Builder(flat.this).setTitle("About Developer").
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
    private void pickImageFromGallery() {
        // for pick image
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,image_pick_code);
    }
    //handle result of runtime permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case PERMISSION_CODE:{
                if (grantResults.length>0 && grantResults[0]==
                        PackageManager.PERMISSION_GRANTED){
                    //permision was granted
                    pickImageFromGallery();
                }
                else {
                    //permission was denied
                    Toast.makeText(this,"Permission denied.......!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    //handel result of picked image


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==image_pick_code){
            //set image to image view
            uploadimg.setImageURI(data.getData());
        }
    }





}