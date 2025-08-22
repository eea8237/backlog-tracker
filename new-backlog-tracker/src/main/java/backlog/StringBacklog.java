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
        try {
            // note to self - adding '/' at the start of the filename string makes java think this is an absolute path
            File dir = new File("backlog-tracker/new-backlog-tracker/saves"); // probably still need to tweak this
            if (dir.mkdirs()) System.out.println("Created save directory.");
            else System.out.println("save directory was not created");

            File savefile = new File(dir, "save" + this.id + ".bkl"); // add folder for saves
            
            if (savefile.createNewFile()) System.out.println("Created file: " + savefile.getName());
            else System.out.println("File already exists: " + savefile.getName());
            
            PrintWriter writer = new PrintWriter(savefile);
            
            // clear the save file?
            // or check to see if this information is already there
            writer.println("[" + this.name + "]");
            writer.flush();

            for (int i = 0; i < this.items.size(); i++) {
                Set<String> section = this.items.get(i);
                // print the name of the section
                writer.println("[" + Section.values()[i] + "]");
                writer.flush();

                // print each item in the section
                for (String item : section) {
                    writer.print("\"" + item + "\", ");
                    writer.flush();
                }
                writer.print("\n");
                writer.flush();
            }
            
            writer.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
        // keep track of sections and the items in those sections
        // items can be in comma-separated lists ending with a semi-colon?
            // or sections are just separated by new lines
        // maybe keep items in quotes so that if the title has a comma in it, that doesn't cause issues
            // and include escape character for "
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
                String[] cmdTokens = cmd.split(", ");
                String command = cmdTokens[0].toLowerCase();
                String item = cmdTokens.length > 1 ? cmdTokens[1] : null;
                int sectionNum = 0;
                try {
                    sectionNum = cmdTokens.length > 2 ? Integer.parseInt(cmdTokens[2]) : -1; // probably have the user enter the section, not the number directly
                } catch (NumberFormatException e) {
                    sectionNum = -1;
                }
                
                switch (command) {
                    case "a":
                        if (item != null && Backlog.validSection(sectionNum)) backlog.addItem(item, sectionNum);
                        else System.out.println("Invalid command: " + cmd);
                        break;
                    case "r":
                        if (item != null && Backlog.validSection(sectionNum)) backlog.removeItem(item, sectionNum);
                        else System.out.println("Invalid command: " + cmd);
                        break;
                    case "c":
                        if (item != null && Backlog.validSection(sectionNum)) backlog.markComplete(item, sectionNum);
                        else System.out.println("Invalid command: " + cmd);
                        break;
                    case "i":
                        // if (item != null && Backlog.validSection(sectionNum)) backlog.markIncomplete(item, sectionNum);
                        // else System.out.println("Invalid command: " + cmd);
                        System.out.println("whoops");
                        break;

                    // commands that don't follow the same format as the other commands
                    case "h":
                        System.out.println("commands - (h)elp, (a)dd, (r)emove, mark (c)omplete, mark (i)ncomplete, (s)ave, (l)oad, (q)uit");
                        break;
                    case "s":
                        backlog.save();
                        break;
                    case "l":
                        // this command probably also takes a filename
                        // just treat the item as a filename
                        StringBacklog.load("");
                        break;
                    case "q":
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid command: " + cmd);
                        break;
                }
                System.out.println(backlog);
                
            }
        }
        
    }

}
