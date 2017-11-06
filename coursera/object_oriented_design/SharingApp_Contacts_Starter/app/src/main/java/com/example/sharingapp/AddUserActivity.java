package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Add a new item
 */
public class AddUserActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private UserList user_list = new UserList();
    private int REQUEST_CODE = 1;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        username = (EditText) findViewById(R.id.username_field);
        email = (EditText) findViewById(R.id.email_field);

        context = getApplicationContext();
        user_list.loadUsers(context);
    }

    public void addUser (View view) {

        String username_str = username.getText().toString();
        String email_str = email.getText().toString();

        if (username_str.equals("")) {
            username.setError("Empty field!");
            return;
        }

        if (!user_list.isUserNameAvailable(username_str)) {
            username.equals("");
            username.setError("Username in use!");
            return;
        }

        if (username_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        User user = new User(username_str, email_str, null );

        user_list.addUser(user);

        user_list.saveUsers(context);

        /* End AddItemActivity */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
