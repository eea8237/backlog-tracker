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

    public String detailedString() {
        return "[" + this.name + " - section: " + this.section + ", medium: " + this.medium + ", series: " + this.series + ", isComplete?: " + this.isComplete + ", is owned?: " + this.owned + "]";
    }

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
