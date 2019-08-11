package com.example.tab_m;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class FirstPage extends AppCompatActivity {
private ImageView home,ads,coupans,contact,newItems,userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        init();
//         <ImageView
//        android:id="@+id/home"
//        android:layout_width="122dp"
//        android:layout_height="146dp"
//        app:srcCompat="@drawable/home" />
//        <ImageView
//        android:id="@+id/ads"
//        android:layout_width="131dp"
//        android:layout_height="131dp"
//        android:foregroundGravity="bottom"
//        app:srcCompat="@drawable/ads" />
//
//
//
//        <ImageView
//        android:id="@+id/coupons"
//        android:layout_width="140dp"
//        android:layout_height="140dp"
//        app:srcCompat="@drawable/coupans" />
//
//        <ImageView
//        android:id="@+id/contact"
//        android:layout_width="138dp"
//        android:layout_height="148dp"
//        app:srcCompat="@drawable/contact" />
//        <ImageView
//        android:id="@+id/new_item"
//        android:layout_width="138dp"
//        android:layout_height="148dp"
//        app:srcCompat="@drawable/new_item" />
//        <ImageView
//        android:id="@+id/user_profile"
//        android:layout_width="138dp"
//        android:layout_height="148dp"
//        app:srcCompat="@drawable/user_profile" />
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstPage.this,MapsActivity.class));
            }
        });
        ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"ADs In Progress",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(FirstPage.this,OptActivity.class));
            }
        });
        coupans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Coupans In Progress",Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(FirstPage.this,OptActivity.class));
            }
        });
        newItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstPage.this,Privatepage.class));
            }
        });
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstPage.this,UserProfile.class));
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstPage.this,ContactActivity.class));
            }
        });

    }

    private void init(){
        home=findViewById(R.id.home);
        ads=findViewById(R.id.ads);
        coupans=findViewById(R.id.coupons);
        contact=findViewById(R.id.contact);
        newItems=findViewById(R.id.new_item);
        userProfile=findViewById(R.id.user_profile);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.display_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Display_menu:
                startActivity(new Intent(FirstPage.this,DisplayActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
