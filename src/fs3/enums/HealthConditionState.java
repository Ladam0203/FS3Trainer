package fs3.enums;

public enum HealthConditionState {
    INACTIVE("Inactive"),
    POTENTIAL("Potential"),
    ACTIVE("Active");

    private final String name;

    HealthConditionState(String healthConditionState) {
        this.name = healthConditionState;
    }

    @Override
    public String toString() {
        return name;
    }

    public static HealthConditionState fromString(String healthConditionState) {
        for (HealthConditionState hcs : HealthConditionState.values()) {
            if (hcs.name.equals(healthConditionState)) {
                return hcs;
            }
        }
        throw new IllegalArgumentException("Health condition state " + healthConditionState + " cannot be parsed");
    }
}
