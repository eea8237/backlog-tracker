package backlog;

/**
 * Class to represent an item in a backlog.
 * @author Esther Arimoro
 */
/*
 *  Item
            name - String
            isComplete - boolean
            series - String
            medium - Category
            markComplete() - void
            markIncomplete() - void
            toString() - String
        should it be comparable?
            if it is, sort by section, then by series, then in alphabetical order
            sort by medium too
            include sorting methods for every category maybe

            what if i just stuffed all this stuff into a map
            a map that maps strings to a list of strings
                list detailing everything other than name
                or wait...
                what if i store the items within a map!
                a map of their names, each within their own separate categories
 */
public class Item {
    private final String name;
    private boolean isComplete;
    private boolean owned; // if i own the item or not
    private final Medium medium;
    private final String series; // what series this is in
    private final Section section; // what series this is in

    public Item(String name, boolean isComplete, boolean owned, Medium medium, String series, Section section) {
        this.name = name;
        this.isComplete = isComplete;
        this.owned = owned;
        this.medium = medium;
        this.series = series;
        this.section = section;
    }

    public Item(String name, boolean owned, Medium medium, String series, Section section) {
        this(name, false, owned, medium, series, section);
    }

    /**
     * items are incomplete and owned by default
     */
    public Item(String name, Medium medium, String series, Section section) {
        this(name, false, true, medium, series, section);
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public void markIncomplete() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    /**
     * Hashes an item based on the traits that make it equal to another item
     */
    public int hashCode() {
        String string = "[" + this.name + " - section: " + this.section + ", medium: " + this.medium + ", series: " + this.series + "]";
        return string.hashCode();
    }

    public String detailedString() {
        return "[" + this.name + " - section: " + this.section + ", medium: " + this.medium + ", series: " + this.series + ", is complete?: " + this.isComplete + ", is owned?: " + this.owned + "]";
    }

    // /**
    //  * Convert a string into an item.
    //  * Format: name, section, medium, series, isComplete, isOwned
    //  * could also just be the first four
    //  * if it's five items then it's the first four + isOwned
    //  * @param string String to be converted.
    //  * @return Item from the string.
    //  */
    // public static Item stringToItem(String string) {
    //     String[] tokens = string.split(", ");
    //     if (tokens.length >= 4) {
    //         String name = tokens[1];
    //         Section section = 
    //         Item item = null;


    //     } else return null;
    // }

    @Override
    /**
     * two items are equal if they have the same name, medium, section,  series
     */
    public boolean equals(Object obj) {
        if (obj instanceof Item other) {
            if (other.name.equals(this.name) && other.medium.equals(this.medium) && other.section.equals(this.section) && other.series.equals(this.series)) return true;
            else return false;
        } else return false;
    }

    public static void main(String[] args) {
        Item item = new Item("Paper Mario: Color Splash", Medium.VIDEO_GAME, "Paper Mario", Section.REPLAY);
        System.out.println(item);
        System.out.println(item.detailedString());
    }
}
