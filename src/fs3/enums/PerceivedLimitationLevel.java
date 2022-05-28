package fs3.enums;

public enum PerceivedLimitationLevel {
    EXPERIENCES_LIMITATION("Experiences limitation"),
    DOES_NOT_EXPERIENCE_LIMITATION("Does not experience limitation");

    private final String name;

    PerceivedLimitationLevel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static PerceivedLimitationLevel fromString(String perceivedLimitationLevel) {
        for (PerceivedLimitationLevel b : PerceivedLimitationLevel.values()) {
            if (b.name.equals(perceivedLimitationLevel)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Perceived limitation level " + perceivedLimitationLevel + " could not be parsed");
    }
}
