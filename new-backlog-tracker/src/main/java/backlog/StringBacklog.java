package backlog;

import java.util.*;
import java.io.*;
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
public class StringBacklog extends Backlog<String> {

    /**
     * Initiallize a backlog with a set of items and a name.
     * @param items
     */
    public StringBacklog(String name) {
        super(name);
    }

    @Override
    /**
     * Mark an item as complete (by removing it from the section)
     * @param item the item to be marked as complete.
     */
    public void markComplete(String item, int sectionNum) {
        this.removeItem(item, sectionNum);
    }

    @Override
    /**
     * Convert a backlog into a file.
     * @return true if save is successful, false otherwise
     */
    public void save() {
        return;
    }

    /**
     * Load a backlog from a file
     * @param file the path of the file to be loaded
     * @return the backlog parsed from the file backlog
     */
    public static StringBacklog load(String filename) {
        return null;

    }


}
