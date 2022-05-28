package fs3.enums;

public enum LimitationLevel {
    NO_LIMITATION("No limitation", 0),
    SLIGHT_LIMITATION("Slight limitation", 1),
    MODERATE_LIMITATION("Moderate limitation", 2),
    SEVERE_LIMITATION("Severe limitation", 3),
    TOTAL_LIMITATION("Extreme limitation", 4),
    NOT_RELEVANT("Not relevant", 9);

    private final String name;
    private final int value;

    LimitationLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static LimitationLevel fromInt(int value) {
        for (LimitationLevel l : LimitationLevel.values()) {
            if (l.getValue() == value) {
                return l;
            }
        }
        throw new IllegalArgumentException("Limitation level + " + value + " could not be parsed");
    }
}
