package fs3.util;

import fs3.be.HealthConditionData;
import fs3.enums.HealthConditionState;

public class HealthConditionStateParser {
    public static HealthConditionState StringToHealthCondition(String healthCondtionState) {
        for (HealthConditionState state : HealthConditionState.values()) {
            if (state.toString().equals(healthCondtionState)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid health condition state: " + healthCondtionState);
    }
}
