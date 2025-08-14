package backlog;

import java.util.*;
/**
 * A class to represent a backlog
 */
/*
 * Backlog
            items - list or set of items
            addItem() - Item
            removeItem() - Item
            save() - void [save list to a file]
            load() - void [create a list from a file (probably static)]
            contains() - boolean
            toString() - String
 */
public class Backlog {
    private final String name;
    private final Set<Item> items;

    /**
     * Initiallize a backlog with a set of items and a name.
     * @param items
     */
    public Backlog(String name, Set<Item> items) {
        this.name = name;
        this.items = items;
    }
    
    /**
     * Remove an item from the backlog.
     * @param item the name of the item to be added.
     */
    public void addItem(Item item) {
        if (this.items.add(item)) System.out.println("Added " + item);
    }

    /**
     * Remove an item from the backlog.
     * @param item the name of the item to be removed.
     */
    public void removeItem(Item item) {
        // need to modify this to work with just a name maybe
        if (this.items.remove(item)) System.out.println("Removed " + item);
    }

    /**
     * Mark an item as complete.
     * @param item the item to be marked as complete.
     */
    public void markComplete(Item item) {
        this.items.remove(item);
    }

    @Override
    public String toString() {
        return this.name + ": " + this.items.toString();
    }

    public Item getItemFromString(String name) {
        // create an item from a string
        for (Item item : this.items) {
            if (item.toString().equals(name)) {
                return item;
            }
        } 
        return null;
    }
}
