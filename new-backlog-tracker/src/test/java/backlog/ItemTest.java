package backlog;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

public class ItemTest {
    @Test
    public void createItemTest() {
        String name = "TestName";
        String series = "TestSeries";
        Boolean owned = true;
        Boolean isComplete = true;
        Medium medium = Medium.CARTOON;
        Section section = Section.WATCH;
        Item item = new Item(name, isComplete, owned, medium, series, section);
        String expected = "[TestName - section: WATCH, medium: CARTOON, series: TestSeries, is complete?: true, is owned?: true]";

        String actual = item.detailedString();

        assertEquals(expected, actual);
    }

    @Test
    public void createItemTestOverload1() {
        String name = "TestName";
        String series = "TestSeries";
        Boolean owned = true;
        Medium medium = Medium.CARTOON;
        Section section = Section.WATCH;
        Item item = new Item(name, owned, medium, series, section);
        String expected = "[TestName - section: WATCH, medium: CARTOON, series: TestSeries, is complete?: false, is owned?: true]";

        String actual = item.detailedString();

        assertEquals(expected, actual);
    }

    @Test
    public void createItemTestOverload2() {
        String name = "TestName";
        String series = "TestSeries";
        Medium medium = Medium.CARTOON;
        Section section = Section.WATCH;
        Item item = new Item(name, medium, series, section);
        String expected = "[TestName - section: WATCH, medium: CARTOON, series: TestSeries, is complete?: false, is owned?: true]";

        String actual = item.detailedString();

        assertEquals(expected, actual);
    }

    @Test
    public void itemEqualsTrue() {
        Item item1 = new Item("TestName", Medium.ANIME, "TestSeries", Section.WATCH);
        Item item2 = new Item("TestName", false, Medium.ANIME, "TestSeries", Section.WATCH);

        assertTrue(item1.equals(item2));
    }

    @Test
    public void itemEqualsFalseName() {
        Item item1 = new Item("TestName", Medium.ANIME, "TestSeries", Section.WATCH);
        Item item2 = new Item("TestName1", Medium.ANIME, "TestSeries", Section.WATCH);

        assertFalse(item1.equals(item2));
    }

    @Test
    public void itemEqualsFalseSeries() {
        Item item1 = new Item("TestName", Medium.ANIME, "TestSeries", Section.WATCH);
        Item item2 = new Item("TestName", Medium.ANIME, "TestSeries1", Section.WATCH);

        assertFalse(item1.equals(item2));
    }

    @Test
    public void itemEqualsFalseMedium() {
        Item item1 = new Item("TestName", Medium.ANIME, "TestSeries", Section.WATCH);
        Item item2 = new Item("TestName", Medium.CARTOON, "TestSeries", Section.WATCH);

        assertFalse(item1.equals(item2));
    }

    @Test
    public void itemEqualsFalseSection() {
        Item item1 = new Item("TestName", Medium.ANIME, "TestSeries", Section.WATCH);
        Item item2 = new Item("TestName", Medium.ANIME, "TestSeries", Section.PLAY);

        assertFalse(item1.equals(item2));
    }
}
