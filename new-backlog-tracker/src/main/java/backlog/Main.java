package backlog;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /**
     * loop through input until it's validated
     * @param input
     */
    public static String checkName(String input, Scanner scanner) {
        boolean valid = StringBacklog.validateName(input);
        while (!valid) {
            System.out.println("Name cannot contain " + Arrays.toString(StringBacklog.INVALID_STRINGS));
            System.out.print("Try again: ");
            input = scanner.nextLine();
        }
        return input;
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
                            item = checkName(item, scanner);
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
                        backlog.save();
                        break;
                    case "l":
                        // this command probably also takes a filename
                        // just treat the item as a filename
                        StringBacklog newBacklog = StringBacklog.load("save0.bkl");
                        backlog = newBacklog == null ? backlog : newBacklog;
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