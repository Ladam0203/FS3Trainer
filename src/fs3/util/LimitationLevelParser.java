package fs3.util;

import fs3.enums.LimitationLevel;

public class LimitationLevelParser {
    public static LimitationLevel parseInt(int value) {
        for (LimitationLevel level : LimitationLevel.values()) {
            if (level.getValue() == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid limitation level value: " + value);
    }
}
