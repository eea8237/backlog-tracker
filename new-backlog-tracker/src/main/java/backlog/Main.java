package backlog;

import java.io.*;
import java.util.*;

public class Main {
    public static String[] YES = {"yes", "y"};
    public static String[] NO = {"no", "n"};
    /**
     * Loop until the user enters yes or no.
     * @param scanner scanner reading user input
     * @param msg message to be provided to user.
     * @return true if yes, false if no
     */
    public static boolean yesNoLoop(Scanner scanner, String msg) {
        System.out.print(msg + " [Y/N]: ");
        String answer = scanner.nextLine();
        while (true) {
            for (String yes : YES) {
                if (answer.contains(yes)) return true;
            }
            for (String no : NO) {
                if (answer.contains(no)) return false;
            }

            System.out.println("Invalid response.");
            System.out.print(msg + " [Y/N]");
            answer = scanner.nextLine();

        }
    }

    public static void save(Backlog<String> backlog, Scanner scanner) {
        File dir = new File(StringBacklog.SAVE_DIR);
        File savefile = new File(dir, "save" + backlog.getId() + ".bkl"); // add folder for saves
        if (savefile.exists()) {
            boolean yes = yesNoLoop(scanner, "Do you want to overwrite this file?");
            if (yes) {
                backlog.save();
            } else System.out.println("Didn't overwrite the file.");
        } else {
            backlog.save();
        }
    }

    public static StringBacklog load(String file) {
        if (file == null) file = "save0.bkl"; // probably check to make sure this is a bkl file? a valid bkl file?
        StringBacklog newBacklog = StringBacklog.load(file);

        if (newBacklog != null) System.out.println("Loaded " + file);
        else System.out.println("Failed to load file " + file);
        return newBacklog;
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
                        if (item != null && Backlog.validSection(sectionNum)) {
                            item = StringBacklog.checkName(item, scanner);
                            backlog.addItem(item, sectionNum);
                        }
                        else System.out.println("Invalid command: " + cmd);
                        break;
                    case "r":
                        if (item != null && Backlog.validSection(sectionNum)) {
                            if (backlog.contains(item, sectionNum)) {
                                backlog.removeItem(item, sectionNum);
                            }
                            else System.out.println(item + " was not found in " + backlog.getName() + ".");
                        }
                        else System.out.println("Invalid command: " + cmd);
                        break;
                    case "c":
                        if (item != null && Backlog.validSection(sectionNum)) {
                            if (backlog.contains(item, sectionNum)) {
                                backlog.markComplete(item, sectionNum);
                            }
                            else System.out.println(item + " was not found in " + backlog.getName() + ".");
                        }
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
                        save(backlog, scanner);
                        break;
                    case "l":
                        // just treat the item as a filename
                        StringBacklog newBacklog = load(item);
                        backlog = newBacklog == null ? backlog : newBacklog;
                        break;
                    case "q":
                        boolean ask = yesNoLoop(scanner, "Do you want to save this file?");
                        if (ask) {
                            save(backlog, scanner);
                        }
                        quit = true;
                        break;
                    case "n":
                        // so. to make use of backlog ids, i think i need to provide the user a way to create a new backlog from within the same instance of the cli
                        // what would that do?
                        // save the previous backlog if the user wants.
                        boolean yes = yesNoLoop(scanner, "Do you want to save this file?");
                        if (yes) {
                            save(backlog, scanner);
                        }
                        
                        // create a new backlog
                        System.out.print("Enter name for backlog: ");
                        String name = scanner.nextLine();
                        // figure out a way to keep track of ids
                        // like iterate through saves and check to see what the latest save is
                        backlog = new StringBacklog(name); 
                        break;
                    case "p":
                        if (item != null && Backlog.validSection(sectionNum)) {
                            Set<String> section = backlog.getSection(sectionNum);
                            for (String i : section) {
                                System.out.println(i);
                            }
                        }
                        // print contents of a section line by line
                        // maybe add a way to 'enter' a section, so all the things you do there are like that by default?
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