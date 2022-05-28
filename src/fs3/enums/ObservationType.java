package fs3.enums;

public enum ObservationType {
    BLOOD_PRESSURE("Blood pressure"),
    HEARTH_RATE("Heart rate"),
    OXYGEN("Oxygen"),
    BLOOD_SUGAR("Blood sugar"),
    TEMPERATURE("Temperature"),
    WEIGHT("Weight");

    String name;
    ObservationType(String name) {
        this.name = name;
    }

}
