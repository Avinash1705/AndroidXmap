package com.example.tab_m;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class OptActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText otp,number,address,name;
    private  Button getotp,sign;
    private String motp,mnumber,VerificatinCode,m_address,m_name;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //  updateUI(currentUser);
        if(currentUser !=null){
            startActivity(new Intent(OptActivity.this,FirstPage.class));
        }
        else{
            Toast.makeText(getApplicationContext(),"Register First",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mAuth = FirebaseAuth.getInstance();

        init();
//            motp=otp.getText().toString();
//            mnumber=number.getText().toString();

        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                VerificatinCode=s;
                Toast.makeText(getApplicationContext(),"COde Send TO number",Toast.LENGTH_SHORT).show();

            }
        };

    }

    private void init(){
        otp=findViewById(R.id.otp);number=findViewById(R.id.number);
        getotp=findViewById(R.id.getotp);sign=findViewById(R.id.signin);
        address=findViewById(R.id.address);
        name=findViewById(R.id.name);
    }
    public void sendSms(View view){
        mnumber="+91"+number.getText().toString();
        if(mnumber!=null || m_name!=null || m_address!=null){
           writeTodatabase();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    mnumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacks
        }
        else{
            Toast.makeText(getApplicationContext(),"Fill All Fields",Toast.LENGTH_SHORT).show();
        }



    }
    public void SignInWithPhone(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(getApplicationContext(),"USer Success SignIN",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OptActivity.this,FirstPage.class));
            }
        });
    }
    public void Verify(View v){
        String  input_code=otp.getText().toString();
        verifyPhoneNumber(VerificatinCode,input_code);

//        if(VerificatinCode.equals("")){
//            }
    }

    private void verifyPhoneNumber(String verificatinCode, String input_code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificatinCode,input_code);
        SignInWithPhone(credential);
    }

    private void writeTodatabase(){
        m_address=address.getText().toString();
        m_name=name.getText().toString();
        DatabaseReference myRef = database.getReference("Registered User");
        myRef.setValue(m_address);
        myRef.setValue(m_name);

    }

}