package ClientSide;

public enum Commands {
    PRESS_MOUSE(-1),
    RELEASE_MOUSE(-2),
    DRAG_MOUSE(-3),
    PRESS_KEY(-4),
    RELEASE_KEY(-5),
    MOVE_MOUSE(-6);

    private int abbrev;

    Commands(int abbrev) {
        this.abbrev = abbrev;
    }

    public int getAbbrev() {
        return abbrev;
    }
}