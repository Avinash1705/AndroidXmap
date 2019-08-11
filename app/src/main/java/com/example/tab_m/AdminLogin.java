package com.example.tab_m;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    private TextView et_phone,et_pass;
    private Button login;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.maps_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.maps_menu:
                startActivity(new Intent(AdminLogin.this,MapsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
            init();
        final String phone="8700056622";
        final String pass="searchKaro";


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.equals(et_phone) && pass.equals(et_pass)){
                    startActivity(new Intent(AdminLogin.this,MapsActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter Value Correctly",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init(){
        et_phone=findViewById(R.id.admin_phone);
        et_pass=findViewById(R.id.admin_pass);
        login=findViewById(R.id.admin_login);
    }
}
