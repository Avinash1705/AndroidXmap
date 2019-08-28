package com.example.tab_m;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UserProfile extends ListActivity {
private TextView userPro;
static   final String[] FRUITS = new String[] { "Avinash","8700056622","Bama","1234567890" };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.userprofile_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_profile:
                startActivity(new Intent(UserProfile.this,Privatepage.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //ll_userProfile=findViewById(R.id.ll_userProfile);
        userPro=findViewById(R.id.tv_userProfile);


        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_user_profile,FRUITS));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           // userPro.setText(((TextView)view.getText()));
              userPro.setText(FRUITS.toString());

          }
      });




    }
}
