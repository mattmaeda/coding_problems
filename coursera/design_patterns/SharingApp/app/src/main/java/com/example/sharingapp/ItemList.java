package com.example.sharingapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
 * ItemList class: Holds ArrayList of Items
 */
public class ItemList {

    private static ArrayList<Item> items;
    private String FILENAME = "item_file.sav";

    public ItemList() {
        items = new ArrayList<Item>();
    }

    public void setItems(ArrayList<Item> item_list) {
        items = item_list;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public boolean hasItem(Item item) {
        for (Item i : items) {
            if (item.getId().equals(i.getId())) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(Item item) {
        int pos = 0;
        for (Item i : items) {
            if (item.getId().equals(i.getId())) {
                return pos;
            }
            pos = pos + 1;
        }
        return -1;
    }

    public int getSize() {
        return items.size();
    }

    public void loadItems(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Item>>() {
            }.getType();
            items = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            items = new ArrayList<Item>();
        } catch (IOException e) {
            items = new ArrayList<Item>();
        }
    }

    public boolean saveItems(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(items, osw);
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

    public ArrayList<User> getActiveBorrowers() {

        ArrayList<User> active_borrowers = new ArrayList<User>();
        for (Item i : items) {
            User borrower = i.getBorrower();
            if (borrower != null) {
                active_borrowers.add(borrower);
            }
        }
        return active_borrowers;
    }

    public ArrayList<Item> filterItemsByStatus(String status){
        ArrayList<Item> selected_items = new ArrayList<>();
        for (Item i: items) {
            if (i.getStatus().equals(status)) {
                selected_items.add(i);
            }
        }
        return selected_items;
    }

}



