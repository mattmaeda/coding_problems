package com.example.sharingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Item Adapter is responsible for what information is displayed in ListView entries.
 */
public class UserAdapter extends ArrayAdapter<User> {

    private LayoutInflater inflater;
    private Fragment fragment;

    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);
        String username = "Username: " + user.getUsername();
        String email = "Email: " + user.getEmail();

        // Check if an existing view is being reused, otherwise inflate the view.
        if (convertView == null) {
            convertView = inflater.from(getContext()).inflate(R.layout.list_user, parent, false);
        }
        //convertView = inflater.from(getContext()).inflate(R.layout.list_user, parent, false);

        TextView user_fld = (TextView) convertView.findViewById(R.id.username_field);
        TextView email_fld = (TextView) convertView.findViewById(R.id.email_field);


        user_fld.setText(username);
        email_fld.setText(email);

        return convertView;
    }
}
