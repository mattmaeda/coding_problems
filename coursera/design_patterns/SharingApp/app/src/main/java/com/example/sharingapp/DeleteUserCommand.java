package com.example.sharingapp;
import android.content.Context;


public class DeleteUserCommand extends Command {
    private UserList user_list;
    private User delete_user;
    private Context context;

    public DeleteUserCommand(UserList user_list, User delete_user, Context context) {
        this.user_list = user_list;
        this.delete_user = delete_user;
        this.context = context;
    }

    public void execute() {
        user_list.removeUser(delete_user);
        setIsExecuted(user_list.saveUsers(context));
    }
}