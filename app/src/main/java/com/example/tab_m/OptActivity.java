package com.example.tab_m;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OptActivity extends AppCompatActivity {
    private EditText otp,phoneNum;
    private Button v_code,login;
   private FirebaseAuth mAuth;
   private String CodeSend, ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt);
            init();
        mAuth=FirebaseAuth.getInstance();

        v_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            SendVerification();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifySignIn();
            }
        });

    }
    private void VerifySignIn(){
        String code=otp.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(CodeSend, code);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(OptActivity.this,MapsActivity.class));


                        } else {

                            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    private void SendVerification() {
        ph = phoneNum.getText().toString();

        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                CodeSend = s;

            }
        };


        if (ph.isEmpty() || ph.length() < 10) {
            phoneNum.setError("Phone Number is Req");
            phoneNum.requestFocus();
            return;
        } else {

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    ph,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacksPhoneAuthActivity.java
        }




    }
    private  void init(){
    otp=findViewById(R.id.otp);
    phoneNum=findViewById(R.id.PhoneNumber);
    v_code=findViewById(R.id.getVerificationCode);
    login=findViewById(R.id.login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login_menu:
                startActivity(new Intent(OptActivity.this,FirstPage.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
