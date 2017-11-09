package com.example.sharingapp;
import android.content.Context;

/**
 * Command used to edit pre-existing item: editing an item consists of deleting * the old item and adding a new item with the old item's id.
 */

public class DeleteItemCommand extends Command {
    private ItemList item_list;
    private Item item_to_delete;
    private Context context;

    public DeleteItemCommand(ItemList item_list, Item item_to_delete, Context context) {
        this.item_list = item_list;
        this.item_to_delete = item_to_delete;
        this.context = context;
    }

    public void execute() {
        item_list.removeItem(item_to_delete);
        setIsExecuted(item_list.saveItems(context));
    }
}