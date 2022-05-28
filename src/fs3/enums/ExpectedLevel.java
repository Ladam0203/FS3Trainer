package fs3.enums;

public enum ExpectedLevel {
    DECREASE("Decrease"),
    UNCHANGED("Unchanged"),
    DISAPPEAR("Disappear");

    private final String name;

    ExpectedLevel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ExpectedLevel fromString(String expectedLevel) {
        for (ExpectedLevel el : ExpectedLevel.values()) {
            if (el.name.equals(expectedLevel)) {
                return el;
            }
        }
        throw new IllegalArgumentException("Expected level " + expectedLevel + " could not be parsed");
    }
}
