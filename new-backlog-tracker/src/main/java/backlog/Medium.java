package backlog;
/**
 * An enum to denote which medium an item falls under
 * @author Esther Arimoro
 */
public enum Medium {
    // add toString for this class
    CARTOON("Cartoon"),
    ANIME("Anime"),
    LIVE_ACTION_TV("Live Action TV"),
    ANIMATED_MOVIE("Animated Movie"),
    LIVE_ACTION_MOVIE("Live Action Movie"),
    MANGA("Manga"),
    BOOK("Book"),
    GUIDE("Guide"),
    WEBCOMIC("Webcomic"),
    VIDEO_GAME("Video Game");

    private final String name;

    private Medium(String name) {
        this.name = name;
    }

}
