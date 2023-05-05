package com.example.spunn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    public TextView profile;
    public TextView user;
    public TextView email;
    public TextView FullName;
    public  TextView Contact;
    public TextView Gender;
    public TextView DOB;
    public TextView Add;
    protected Button btnUpdate;
    protected Button btnDelete;
    private DatabaseReference userdatabase;
    private DatabaseReference rentdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        user=findViewById(R.id.user);
       profile=findViewById(R.id.Profile);
       email=findViewById(R.id.email);
        FullName=findViewById(R.id.FullName);
        Contact=findViewById(R.id.Contact);
        Gender=findViewById(R.id.Gender);
        DOB=findViewById(R.id.DOB);
        Add=findViewById(R.id.Add);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
       // ProfileUserRole.setText("LogIn As: "+availability.currentuser.getRole());
        user.setText(availability.currentuser.getUsername());
        email.setText(availability.currentuser.getEmail());
        FullName.setText(availability.currentuser.getName());
        Contact.setText(availability.currentuser.getPhone());
        Gender.setText(availability.currentuser.getGender());
        DOB.setText(availability.currentuser.getDateofbirth());
        Add.setText(availability.currentuser.getAddress());
        Gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] gender={"Male","Female"};
                AlertDialog.Builder alert=new AlertDialog.Builder(Profile.this);
                alert.setTitle("Select the Gender: ");
                alert.setSingleChoiceItems(gender, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (gender[which]=="Male")
                        {
                            Gender.setText("Male");
                        }
                        else if (gender[which]=="Female")
                        {
                            Gender.setText("Female");
                        }
                    }
                });
                alert.show();
            }
        });

        final Calendar mycalendar=Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mycalendar.set(Calendar.YEAR,year);
                mycalendar.set(Calendar.MONTH,month);
                mycalendar.set(Calendar.DAY_OF_WEEK,dayOfMonth);
                String myformat="dd/mm/yyyy";
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat(myformat);
                DOB.setText(simpleDateFormat.format(mycalendar.getTime()));
            }
        };
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  DatePickerDialog(Profile.this,date,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),
                        mycalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String,Object> userChildUpdates=new HashMap<>();
                        userChildUpdates.put("Name ",FullName.getText().toString().trim());
                        userChildUpdates.put("Address ",Add.getText().toString().trim());
                        userChildUpdates.put("Email : ",email.getText().toString().trim());
                        userChildUpdates.put("Gender : ",Gender.getText().toString().trim());
                        userChildUpdates.put("Phone : ",Contact.getText().toString().trim());
                        userChildUpdates.put("Date of Birth : ",DOB.getText().toString().trim());
                        userdatabase.child(availability.currentuser.getUsername()).updateChildren(userChildUpdates);
                        new AlertDialog.Builder(Profile.this).setTitle("Success!").
                                setIcon(R.drawable.ic_done_black_24dp)
                                .setMessage("Updated Successfully!").show();
                    }

                    @Override
                    public void onCancelled( @NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Profile.this).setTitle("!!WARNING!!")
                        .setMessage("Do you really want to Delete?\n Your ads will be also deleted.")
                        .setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Query rentsquery=rentdatabase.orderByChild("userName").equalTo(availability.currentuser.getUsername());
                        Query usersquery=userdatabase.child(availability.currentuser.getUsername());
                        rentsquery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange( @NonNull DataSnapshot snapshot) {
                                for (DataSnapshot rentsnapshot:snapshot.getChildren())
                                {
                                    rentsnapshot.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull  DatabaseError error) {

                            }
                        });
                        usersquery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                                for (DataSnapshot usersnapshot:snapshot.getChildren())
                                {
                                    usersnapshot.getRef().removeValue();
                                }
                                new AlertDialog.Builder(Profile.this).setTitle("Success")
                                        .setMessage("Your account has been deleted!").show();
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull  DatabaseError error) {
                                Log.e("Tag "," onCancelled ", error.toException());
                            }
                        });
                    }
                })
                        .setNegativeButton(android.R.string.no,null).show();
            }
        });
        }

    }
