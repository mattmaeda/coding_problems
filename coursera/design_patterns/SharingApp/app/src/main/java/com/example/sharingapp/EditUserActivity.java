package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Edit preexisting User: editing a user consists of deleting the old user and adding a new user
 * with the old user's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditUserActivity extends AppCompatActivity {

    private UserList user_list = new UserList();
    private User user;
    private EditText email;
    private EditText username;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        context = getApplicationContext();
        user_list.loadUsers(context);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        user = user_list.getUser(pos);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        username.setText(user.getUsername());
        email.setText(user.getEmail());
    }

    public void saveUser(View view) {

        String email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        String username_str = username.getText().toString();

        // Reuse the user id
        String id = user.getId();

        //user_list.removeUser(user);

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!user_list.isUsernameAvailable(username_str) && !(user.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return;
        }

        User updated_user = new User(username_str, email_str, id);
        EditUserCommand edit_user_command = new EditUserCommand(user_list, updated_user, user, context);
        //user_list.addUser(updated_user);
        //user_list.saveUsers(context);
        edit_user_command.execute();
        boolean success = edit_user_command.isExecuted();

        if (!success) {
            return;
        }

         /* end EditUserActivity */
        finish();
    }

    public void deleteUser(View view) {

        //user_list.removeUser(user);
        //user_list.saveUsers(context);
        DeleteUserCommand delete_user_command = new DeleteUserCommand(user_list, user, context);
        delete_user_command.execute();
        boolean success = delete_user_command.isExecuted();

        if (!success) {
            return;
        }

         /* end EditUserActivity */
        finish();
    }

}
