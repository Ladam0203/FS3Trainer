package fs3.enums;

public enum LimitationLevel {
    NO_LIMITATION("No limitation", 0),
    SLIGHT_LIMITATION("Slight limitation", 1),
    MODERATE_LIMITATION("Moderate limitation", 2),
    SEVERE_LIMITATION("Severe limitation", 3),
    TOTAL_LIMITATION("Extreme limitation", 4),
    NOT_RELEVANT("Not relevant", 9);

    String name;
    int value;

    LimitationLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
