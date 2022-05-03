package fs3.util;

import fs3.be.HealthConditionData;
import fs3.enums.HealthCondition;

public class HealthConditionParser {
    public static HealthCondition StringToHealthConditionParser(String healthCondition) {
        for (HealthCondition hc : HealthCondition.values()) {
            if (hc.toString().equals(healthCondition)) {
                return hc;
            }
        }
        throw new IllegalArgumentException("Health condition string not recognized");
    }
}
