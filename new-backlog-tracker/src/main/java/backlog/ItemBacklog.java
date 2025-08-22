package backlog;

import java.util.*;
import java.io.*;
/**
 * A class to represent a backlog
 * @author Esther Arimoro
 */
public class ItemBacklog extends Backlog<Item>{

    public ItemBacklog(String name) {
        super(name);
    }

    @Override
    /**
     * Mark an item as complete.
     * @param item the item to be marked as complete.
     */
    public void markComplete(Item item, int sectionNum) {
        return;
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
     * @return a backlog to be loaded
     */
    public static ItemBacklog load(String file) {
        return null;
    }
}
