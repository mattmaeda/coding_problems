package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Edit preexisting item: editing an item consists of deleting the old item and adding a new item
 * with the old item's id.
 * Note: invisible EditText is used to setError for status. For whatever reason I cannot .setError to
 * the status Switch so instead this is done using an additional "invisible" EditText.
 */
public class EditItemActivity extends AppCompatActivity{

    private ItemList item_list = new ItemList();
    private Item item;
    private Context context;

    private UserList user_list = new UserList();

    private Bitmap image;
    private int REQUEST_CODE = 1;
    private ImageView photo;

    private EditText title;
    private EditText maker;
    private EditText description;
    private EditText length;
    private EditText width;
    private EditText height;
    private Spinner borrower_spinner;
    private TextView  borrower_tv;
    private Switch status;
    private EditText invisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        title = (EditText) findViewById(R.id.title);
        maker = (EditText) findViewById(R.id.maker);
        description = (EditText) findViewById(R.id.description);
        length = (EditText) findViewById(R.id.length);
        width = (EditText) findViewById(R.id.width);
        height = (EditText) findViewById(R.id.height);
        borrower_spinner = (Spinner) findViewById(R.id.borrower_spinner);
        borrower_tv = (TextView) findViewById(R.id.borrower_tv);
        photo = (ImageView) findViewById(R.id.image_view);
        status = (Switch) findViewById(R.id.available_switch);
        invisible = (EditText) findViewById(R.id.invisible);

        invisible.setVisibility(View.GONE);

        context = getApplicationContext();
        item_list.loadItems(context);
        user_list.loadUsers(context);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, user_list.getAllUsernames());
        borrower_spinner.setAdapter(adapter);

        // get intent from MainActivity
        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        item = item_list.getItem(pos);

        User user = item.getBorrower();
        if (user != null){
            int user_pos = user_list.getIndex(user);
            borrower_spinner.setSelection(user_pos);
        }

        title.setText(item.getTitle());
        maker.setText(item.getMaker());
        description.setText(item.getDescription());

        Dimensions dimensions = item.getDimensions();

        length.setText(dimensions.getLength());
        width.setText(dimensions.getWidth());
        height.setText(dimensions.getHeight());

        String status_str = item.getStatus();
        if (status_str.equals("Borrowed")) {
            status.setChecked(false);
        } else {
            borrower_tv.setVisibility(View.GONE);
            borrower_spinner.setVisibility(View.GONE);
        }

        image = item.getImage();
        if (image != null) {
            photo.setImageBitmap(image);
        } else {
            photo.setImageResource(android.R.drawable.ic_menu_gallery);
        }
    }

    public void addPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void deletePhoto(View view) {
        image = null;
        photo.setImageResource(android.R.drawable.ic_menu_gallery);
    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent intent){
        if (request_code == REQUEST_CODE && result_code == RESULT_OK){
            Bundle extras = intent.getExtras();
            image = (Bitmap) extras.get("data");
            photo.setImageBitmap(image);
        }
    }

    public void deleteItem(View view) {
        DeleteItemCommand delete_item_command = new DeleteItemCommand(item_list, item, context);
        delete_item_command.execute();
        boolean success = delete_item_command.isExecuted();

        if (!success) {
            return;
        }

         /* end EditItemActivity */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveItem(View view) {

        String title_str = title.getText().toString();
        String maker_str = maker.getText().toString();
        String description_str = description.getText().toString();
        String length_str = length.getText().toString();
        String width_str = width.getText().toString();
        String height_str = height.getText().toString();


        User user = null;
        if (!status.isChecked()) {
            // means borrowed
            String borrower_str = borrower_spinner.getSelectedItem().toString();
            user = user_list.getUserByUsername(borrower_str);
        }

        if (title_str.equals("")) {
            title.setError("Empty field!");
            return;
        }

        if (maker_str.equals("")) {
            maker.setError("Empty field!");
            return;
        }

        if (description_str.equals("")) {
            description.setError("Empty field!");
            return;
        }

        if (length_str.equals("")) {
            length.setError("Empty field!");
            return;
        }

        if (width_str.equals("")) {
            width.setError("Empty field!");
            return;
        }

        if (height_str.equals("")) {
            height.setError("Empty field!");
            return;
        }

        // Reuse the item id
        String id = item.getId();
        //item_list.removeItem(item);
        Dimensions dimensions = new Dimensions(length_str, width_str, height_str);
        Item updated_item = new Item(title_str, maker_str, description_str, dimensions, image, id);

        boolean checked = status.isChecked();
        if (!checked) {
            // means borrowed
            updated_item.setStatus("Borrowed");
            updated_item.setBorrower(user);
        }

        //item_list.addItem(updated_item);

        //item_list.saveItems(context);
        //Edit item
        EditItemCommand edit_item_command = new EditItemCommand(item_list, item, updated_item, context);

        edit_item_command.execute();

        boolean success = edit_item_command.isExecuted();

        if (!success) {
            return;
        }

         /* End EditItemActivity */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Checked = Available
     * Unchecked = Borrowed
     */
    public void toggleSwitch(View view){
        if (status.isChecked()) { // status.isChecked() == true
            // means was previously borrowed, user has toggled to available
            borrower_spinner.setVisibility(View.GONE);
            borrower_tv.setVisibility(View.GONE);
            item.setBorrower(null);
            item.setStatus("Available");

        } else { // status.isChecked() == false
            // means not borrowed
            if (user_list.getSize()==0){
                // No contacts, need to add contacts to be able to add a borrower.
                invisible.setEnabled(false);
                invisible.setVisibility(View.VISIBLE);
                invisible.requestFocus();
                invisible.setError("No contacts available! Must add borrower to contacts.");
                status.setChecked(true); // set switch to available

            } else {
                // means was previously available
                borrower_spinner.setVisibility(View.VISIBLE);
                borrower_tv.setVisibility(View.VISIBLE);
            }
        }
    }
}
