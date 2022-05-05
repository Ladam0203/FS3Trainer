package fs3.util;

import fs3.enums.Limitation;

public class LimitationLevelParser {
    public static Limitation parseInt(int value) {
        for (Limitation level : Limitation.values()) {
            if (level.getValue() == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid limitation level value: " + value);
    }
}
