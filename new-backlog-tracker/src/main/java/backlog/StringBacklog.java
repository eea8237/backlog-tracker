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

    public static void main(String[] args) {
        // for testing out string backlog
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter name for backlog: ");
            String cmd = scanner.nextLine();
            Backlog<String> backlog = new StringBacklog(cmd);

            boolean quit = false;
            while (!quit) {
                System.out.print(">> ");
                cmd = scanner.nextLine(); // format - "command, item, sectionNum"
                if (cmd.equals("q")) {
                    break;
                }
                String[] cmdTokens = cmd.split(", ");
                String command = cmdTokens[0].toLowerCase();
                String item = cmdTokens[1];
                int sectionNum = Integer.parseInt(cmdTokens[2]); // probably have the user enter the section, not the number directly
                switch (command) {
                    case "h":
                        System.out.println("commands - (h)elp, (a)dd, (r)emove, mark (c)omplete, mark (i)ncomplete, (q)uit");
                        break;
                    case "a":
                        backlog.addItem(item, sectionNum);
                        break;
                    case "r":
                        backlog.removeItem(item, sectionNum);
                        break;
                    case "c":
                        backlog.markComplete(item, sectionNum);
                        break;
                    case "i":
                        // backlog.markIncomplete(item, sectionNum);
                        System.out.println("whoops");
                        break;
                    case "q":
                        quit = true;
                        break;
                }
                System.out.println(backlog);
                
            }
        }
        
    }

}
