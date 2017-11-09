package com.example.sharingapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Holds ArrayList of Users
 */
public class UserList {

    private static ArrayList<User> users;
    private String FILENAME = "user_file.sav";

    public UserList() {
        users = new ArrayList<User>();
    }

    public void setUsers(ArrayList<User> user_list) {
        users = user_list;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<String> getAllUsernames(){
        ArrayList<String> username_list = new ArrayList<String>();
        for (User u : users){
            username_list.add(u.getUsername());
            }
        return username_list;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User getUser(int index) {
        return users.get(index);
    }

    public int getSize() {
        return users.size();
    }

    public User getUserByUsername( String username){
        for (User u : users){
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    public boolean hasUser(User user) {
        for (User u : users) {
            if (user.getId().equals(u.getId())) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(User user) {
        int pos = 0;
        for (User u : users) {
            if (user.getId().equals(u.getId())) {
                return pos;
            }
            pos = pos+1;
        }
        return -1;
    }

    public boolean isUsernameAvailable(String username){
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public void loadUsers(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            users = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            users = new ArrayList<User>();
        } catch (IOException e) {
            users = new ArrayList<User>();
        }
    }

    public boolean saveUsers(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(users, osw);
            osw.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
