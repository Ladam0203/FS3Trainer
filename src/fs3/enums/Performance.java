package fs3.enums;

public enum Performance {
    PERFORMS_BY_THEMSELF("Performs by themself"),
    PARTLY_PERFORMS_BY_THEMSELF("Partly performs by themself"),
    DOES_NOT_PERFORM_BY_THEMSELF("Does not perform by themself");

    private final String name;

    Performance(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static Performance fromString(String performance) {
        for (Performance p : Performance.values()) {
            if (p.name.equals(performance)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Performance " + performance + " does not exist");
    }
}
