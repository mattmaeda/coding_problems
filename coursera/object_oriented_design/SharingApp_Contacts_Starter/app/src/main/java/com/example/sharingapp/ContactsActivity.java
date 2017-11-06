package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Home Activity of the App
 */
public class ContactsActivity extends AppCompatActivity {
    private UserList user_list = new UserList();
    private UserList active_borrowers_list;
    private ItemList item_list;
    private ArrayAdapter<User> adapter;
    private ListView my_contacts;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);

        my_contacts = (ListView) findViewById(R.id.my_contacts);
        my_contacts.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {

                User user = adapter.getItem(pos);

                int meta_pos = user_list.getIndex(user);

                if (meta_pos >= 0) {

                    Intent edit = new Intent(context, EditUserActivity.class);
                    edit.putExtra("position", meta_pos);
                    startActivity(edit);
                }
                return true;
            }
        });

        context = getApplicationContext();
        user_list.loadUsers(context);

        ArrayList<User> users = new ArrayList<User>();
        for(int i = 0; i < user_list.getSize(); i++) {
            User u = user_list.getUser(i);
            users.add(u);
        }

        adapter = new UserAdapter(context, users);
        my_contacts.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.contacts:
                startActivity(new Intent(this, ContactsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addUserActivity(View view) {
        startActivity(new Intent(this, AddUserActivity.class));
    }
}
