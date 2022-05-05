package fs3.enums;

public enum PerceivedLimitationLevel {
    EXPERIENCES_LIMITATION("Experiences limitation"),
    DOES_NOT_EXPERIENCE_LIMITATION("Does not experience limitation");

    String name;
    PerceivedLimitationLevel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
