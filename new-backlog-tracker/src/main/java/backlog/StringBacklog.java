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
    public static final String[] INVALID_STRINGS = {"--", "\","};
    // public static final String SAVE_DIR = "backlog-tracker/new-backlog-tracker/saves"; // probably still need to tweak this
    public static final String SAVE_DIR = new File("new-backlog-tracker").getAbsolutePath() + "\\saves"; // figure out a way to handle file separator
    /**
     * Initiallize a backlog with  and a name.
     * @param items
     */
    public StringBacklog(String name) {
        super(name);
    }

    public StringBacklog(String name, int id) {
        super(name, id);
    }

    public StringBacklog(String name, int id, List<Set<String>> items) {
        super(name, id, items);
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
            File dir = new File(SAVE_DIR);
            if (dir.mkdirs()) System.out.println("Created save directory.");
            // else System.out.println("save directory was not created");

            File savefile = new File(dir, "save" + this.getId() + ".bkl"); // add folder for saves
            
            if (savefile.createNewFile()) System.out.println("Created file: " + savefile.getName());
            else System.out.println("File already exists: " + savefile.getName());
            
            PrintWriter writer = new PrintWriter(savefile);
            
            // clear the save file?
            // or check to see if this information is already there
            writer.println("--" + this.getName() + "," + this.getId());
            writer.flush();

            for (int i = 0; i < this.items.size(); i++) {
                Set<String> section = this.items.get(i);
                // print the name of the section
                writer.println("--" + Section.values()[i]);
                writer.flush();

                // print each item in the section
                for (String item : section) {
                    writer.print("\"" + item + "\",");
                    writer.flush();
                }
                writer.println();
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
    }

    /**
     * Strip a line containing special data
     * @param line
     * @return
     */
    private static String stripLine(String line) {
        return line.substring(2).strip();
    }

    /**
     * get a list of string items from a string of text
     * @param itemString the string to convert
     * @return list of items
     */
    // we'll have some string in the format "aaa", "bbb", "ccc", 
    private static Set<String> stringToSet(String itemString) {
        String[] itemsTemp = itemString.split("\",");
        Set<String> itemsSet = new HashSet<>();
        
        for (String item : itemsTemp) {
            // strip this of its leading "
            if (item.length() < 2) continue;
            item = item.substring(1);
            itemsSet.add(item);
        }

        return itemsSet;
    }

    /**
     * Load a backlog from a file
     * @param filename the path of the file to be loaded
     * @return the backlog parsed from the file backlog
     */
    public static StringBacklog load(String filename) {
        try (Scanner scanner = new Scanner(new File(SAVE_DIR + "/" + filename))) {
            // if line starts with --, it's data that isn't an item

            // special case for first line
            String name = "";
            int id = -1;
            List<Set<String>> items = new LinkedList<Set<String>>(); // same as before
            for (int i = 0; i < Section.values().length; i++) items.add(null);

            if (scanner.hasNext()) {
                String identifier = stripLine(scanner.nextLine()); // get rid of the first -- and any whitespace
                String[] identifierTokens = identifier.split(",");
                
                name = identifierTokens[0];
                id = Integer.parseInt(identifierTokens[1]);
            }
            // go through the sections 
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.equals("\n")) continue;
                if (line.length() > 1 && line.charAt(0) == '-' && line.charAt(1) == '-') {
                    // this isn't an item, it's a section header
                    line = stripLine(line);
                    Section section = Section.valueOf(line);

                    // next line should be a list of items
                    if (scanner.hasNext()) line = scanner.nextLine();
                    else { // this is the last section, which is presumably empty, so... just add an empty set to that section
                        items.set(Section.values().length-1, new HashSet<String>()); // this works but it feels sort of messy
                        continue;
                    }
                    switch (section) {
                        case WATCH:
                            // is there a way to not have to hard code this index
                            items.set(WATCH_SECTION_INDEX, stringToSet(line));
                            break;
                        case REWATCH:
                            items.set(REWATCH_SECTION_INDEX, stringToSet(line));
                            break;
                        case PLAY:
                            items.set(PLAY_SECTION_INDEX, stringToSet(line));
                            break;
                        case REPLAY:
                            items.set(REPLAY_SECTION_INDEX, stringToSet(line));
                            break;
                        case READ:
                            items.set(READ_SECTION_INDEX, stringToSet(line));
                            break;
                        case REREAD:
                            items.set(REREAD_SECTION_INDEX, stringToSet(line));
                            break;
                        default:
                            System.out.println("Invalid section.");
                            break;
                    }
                }
            }
            return new StringBacklog(name, id, items);
        
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
        /*
         * --git,0
         * --WATCH
         *
         * --REWATCH
         * --PLAY
         * "vvv", "aaaa"
         * --REPLAY
         * 
         * --READ
         * 
         * --REREAD
         */
    }

    /**
     * Checks to see if a string has any invalid characters in it.
     * @return true if the string entered doesn't have any invalid characters, false otherwise
     */
    private static Boolean validateName(String name) {
        for (String str : INVALID_STRINGS) {
            if (name.contains(str)) return false;
        }
        return true;
    }

    /**
     * loop through input until it's validated
     * @param input
     */
    public static String checkName(String input, Scanner scanner) {
        boolean valid = validateName(input);
        while (!valid) {
            System.out.println("Name cannot contain " + Arrays.toString(INVALID_STRINGS));
            System.out.print("Try again: ");
            input = scanner.nextLine();
            valid = validateName(input);
        }
        return input;
    }

}
