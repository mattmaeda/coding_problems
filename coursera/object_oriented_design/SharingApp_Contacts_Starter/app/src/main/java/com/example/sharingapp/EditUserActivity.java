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
import android.widget.Switch;
import android.widget.TextView;
import android.util.Log;

/**
 * Edit preexisting item: editing an item consists of deleting the old item and adding a new item
 * with the old item's id.
 * Note: invisible EditText is used to setError for status. For whatever reason I cannot .setError to
 * the status Switch so instead this is done using an additional "invisible" EditText.
 */
public class EditUserActivity extends AppCompatActivity {

    private UserList user_list = new UserList();

    private EditText username;
    private EditText email;
    private EditText description;
    private User user;
    private Context context;
    private int REQUEST_CODE = 1;
    private ItemList item_list = new ItemList();
    private boolean borrowing_item = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        context = getApplicationContext();
        user_list.loadUsers(context);
        item_list.loadItems(context);

        // get intent from HomeActivity
        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        user = user_list.getUser(pos);
        username.setText(user.getUsername());
        email.setText(user.getEmail());

        for(int idx = 0; idx < item_list.getSize(); idx++) {
            Item i = item_list.getItem(idx);
            if(i.getStatus().equals("Borrowed") && i.getBorrower().getUsername().equals(user.getUsername())) {
                borrowing_item = true;
                break;
            }
        }
    }

    public void deleteUser(View view) {
        if (borrowing_item) {
            username.setError("User is borrowing an item!");
            return;
        }

        user_list.removeUser(user);
        user_list.saveUsers(context);

         /* End EditItemActivity */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveUser(View view) {

        String username_str = username.getText().toString();
        String email_str = email.getText().toString();

        if (borrowing_item) {
            username.setError("User is borrowing an item!");
            return;
        }

        if (username_str.equals("")) {
            username.setError("Empty field!");
            return;
        }

        if (!user.getUsername().equals(username_str) && !user_list.isUserNameAvailable(username_str)) {
            username.equals("");
            username.setError("Username in use!");
            return;
        }

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        // Reuse the item id
        String id = user.getId();
        user_list.removeUser(user);

        User updated_user = new User(username_str, email_str, id);


        user_list.addUser(updated_user);

        user_list.saveUsers(context);

        /* End EditItemActivity */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
