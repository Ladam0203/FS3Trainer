package fs3.util;

import fs3.enums.ExpectedLevel;

public class ExpectedLevelParser {
    public static ExpectedLevel StringToExpectedLevel(String expectedLevel) {
        for (ExpectedLevel level : ExpectedLevel.values()) {
            if (level.toString().equals(expectedLevel)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid expected level: " + expectedLevel);
    }
}
