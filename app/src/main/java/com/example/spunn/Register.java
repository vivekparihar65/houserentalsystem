package com.example.spunn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spunn.Firebase.FirebaseHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText name;
    private  EditText email;
    private  EditText phone;
    private EditText pass;
    private EditText uname;
    private EditText cnfrmpass;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private Spinner spinner;
    private Button btnreg;
    private TextView signin;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseHandler firebaseHandler;
    private DatabaseReference userdatabase;
    Context context;
    String USER_TABLE="user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context=Register.this;
        firebaseHandler=new FirebaseHandler();
       // userdatabase=firebaseHandler.getFirebaseConnection(USER_TABLE);
        mAuth=FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        uname=findViewById(R.id.uname);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        pass=findViewById(R.id.pass);
        cnfrmpass=findViewById(R.id.cnfrmpass);
        progressBar=findViewById(R.id.progressBar);

        btnreg=findViewById(R.id.btnreg);
        signin=findViewById(R.id.signin);

        if(mAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        // register button
        btnreg.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final String username=uname.getText().toString().trim();
                    final String Email=email.getText().toString().trim();
                    final String password=pass.getText().toString();
                    final String confirmpassword=cnfrmpass.getText().toString();
                    //final  String role=spinnerRoles.getSelectedItem().toString();

                    if(username.isEmpty()||Email.isEmpty()||password.isEmpty()||confirmpassword.isEmpty())
                    {
                        AlertDialog.Builder alert=new AlertDialog.Builder(Register.this);
                        alert.setTitle("Failed!!");
                        alert.setMessage("FILL UP THE  ABOVE FIELDS!!!");
                        alert.show();
                    }
                    else if (!password.equals(confirmpassword))
                    {
                        AlertDialog.Builder alert=new AlertDialog.Builder(Register.this);
                        alert.setTitle("Failed!!");
                        alert.setMessage("Password does not match!!!");
                        alert.show();
                    }
                    else
                    {
                        mAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(Register.this,"User created",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }
                                else
                                {
                                    Toast.makeText(Register.this,"Error!!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);

                                }
                            }
                        });
                    }
                    progressBar.setVisibility(View.VISIBLE);

                }
            });

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainintent=new Intent(context,MainActivity.class);
                    startActivity(mainintent);
                }
            });
    }
}