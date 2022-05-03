package fs3.enums;

public enum ExpectedLevel {
    DECREASE("Decrease"),
    UNCHANGED("Unchanged"),
    DISAPPEAR("Disappear");

    String expectedLevel;
    ExpectedLevel(String expectedLevel) {
        this.expectedLevel = expectedLevel;
    }
}
