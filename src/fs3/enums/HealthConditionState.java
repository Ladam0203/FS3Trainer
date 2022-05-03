package fs3.enums;

public enum HealthConditionState {
    INACTIVE("Inactive"),
    POTENTIAL("Potential"),
    ACTIVE("Active");

    String healthConditionState;
    HealthConditionState(String healthConditionState) {
        this.healthConditionState = healthConditionState;
    }

    @Override
    public String toString() {
        return healthConditionState;
    }
}
