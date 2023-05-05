package com.example.spunn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spunn.Firebase.FirebaseHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;

    private Button btnsignin;
    private TextView reg;

    FirebaseAuth mAuth;
    int counter=0;
    Timer t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);

        reg=findViewById(R.id.reg);
        btnsignin=findViewById(R.id.btnSignIn);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString().trim();
                String Password=pass.getText().toString().trim();
                if(Email.isEmpty())
                {
                    email.setError("email is required");
                }
                if (Password.isEmpty())
                {
                    pass.setError("password is required");
                }

               // progressBar.setVisibility(View.VISIBLE);
                //authenticate the user.
                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Logged in Successfull!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),dashboard.class));
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Error!!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();



                        }
                    }
                });
            }
        });
        //for regstration
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }
}