package com.example.sharingapp;
import android.content.Context;


public class EditUserCommand extends Command {
    private UserList user_list;
    private User old_user;
    private User new_user;
    private Context context;

    public EditUserCommand(UserList user_list, User old_user, User new_user, Context context) {
        this.user_list = user_list;
        this.old_user = old_user;
        this.new_user = new_user;
        this.context = context;
    }

    public void execute() {
        user_list.removeUser(old_user);
        user_list.addUser(new_user);
        setIsExecuted(user_list.saveUsers(context));
    }
}