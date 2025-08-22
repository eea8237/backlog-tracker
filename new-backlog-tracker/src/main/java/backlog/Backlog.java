package backlog;

import java.util.*;
import java.io.*;
/**
 * A class to represent a backlog
 * @author Esther Arimoro
 */
public abstract class Backlog<T> {
    protected final String name;
    protected final List<Set<T>> items;
    protected final int id;

    protected static int BACKLOG_IDS = 0;

    public static final int WATCH_SECTION_INDEX = 0;
    public static final int REWATCH_SECTION_INDEX = 1;
    public static final int PLAY_SECTION_INDEX = 2;
    public static final int REPLAY_SECTION_INDEX = 3;
    public static final int READ_SECTION_INDEX = 4;
    public static final int REREAD_SECTION_INDEX = 5;

    /**
     * Initiallize a backlog with a set of items and a name.
     * @param items
     */
    public Backlog(String name) {
        this.name = name;
        this.id = BACKLOG_IDS++;
        this.items = new LinkedList<Set<T>>();
        // each index in the items list is assigned to a section
        // 0 - watch, 1 - rewatch, 2 - play, 3 - replay, 4 - read, 5 - reread
        for (int i = 0; i < Section.values().length; i++) {
            this.items.add(new HashSet<T>());

        }
    }

    
    /**
     * Add an item to the backlog.
     * @param item the name of the item to be added.
     * @param sectionNum the section to add the item to
     */
    public void addItem(T item, int sectionNum) {
        this.items.get(sectionNum).add(item);
    }

    /**
     * Remove an item from the backlog.
     * @param item the name of the item to be removed.
     * @param sectionNum the section the item should be removed from
     */
    public void removeItem(T item, int sectionNum) {
        // if the item is in the specified section, remove the item
        if (this.items.get(sectionNum).contains(item)) {
            this.items.get(sectionNum).remove(item);
        }
    }

    /**
     * Mark an item as complete.
     * @param item the item to be marked as complete.
     * @param sectionNum the section of the item to be marked as complete
     */
    public abstract void markComplete(T item, int sectionNum); // use something similar to removing an item

    /**
     * Convert a backlog into a file.
     * @return true if save is successful, false otherwise
     */
    public abstract void save();

    /**
     * Get every item in a section from the set
     * @param sectionNum the number of the section wanted
     * @return the section requested
     */
    public Set<T> getSection(int sectionNum) {
        return this.items.get(sectionNum);
    }

    /**
     * See if a section contains an item
     * @param item the item to check for
     * @param sectionNum the section to search
     * @return true if the item is in the section, false otherwise
     */
    public boolean contains(T item, int sectionNum) {
        return this.items.get(sectionNum).contains(item);
    }

    @Override
    public String toString() {
        String sections = "";
        for (int i = 0; i < Section.values().length; i++) {
            sections += "\n" + Section.values()[i] + ": " + this.items.get(i);
        }
        // print the name, then each section line by line
        return this.name + sections;
    }

    /**
     * Checks to see if a given section number is valid
     * @param sectionNum the section to check
     * @return true if the section number is for a valid section, false otherwise
     */
    public static boolean validSection(int sectionNum) {
        return sectionNum > 0 && sectionNum < Section.values().length;
    }
}
