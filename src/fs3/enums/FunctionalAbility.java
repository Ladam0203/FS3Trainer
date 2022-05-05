package fs3.enums;

public enum FunctionalAbility {
    BATHING("Bathing"),
    GOING_TO_THE_BATHROOM("Going to the bathroom"),
    BODY_CARE("Body care"),
    UNDRESSING_AND_DRESSING("Undressing and dressing"),
    EATING("Eating"),
    DRINKING("Drinking"),
    TAKING_CARE_OF_OWN_HEALTH("Taking care of own health"),
    FOOD_INTAKE("Food intake"),
    PERFORMING_DAILY_ROUTINES("Performing daily routines"),
    ACQUIRING_GOODS_AND_SERVICES("Acquiring goods and services");

    String name;
    FunctionalAbility(String name) {
        this.name = name;
    }
}
