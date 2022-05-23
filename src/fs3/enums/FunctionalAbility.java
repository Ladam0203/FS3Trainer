package fs3.enums;

public enum FunctionalAbility {
    BATHING("Self care", "Bathing"),
    GOING_TO_THE_BATHROOM("Self care", "Going to the bathroom"),
    BODY_CARE("Self care", "Body care"),
    UNDRESSING_AND_DRESSING("Self care", "Undressing and dressing"),
    EATING("Self care", "Eating"),
    DRINKING("Self care", "Drinking"),
    TAKING_CARE_OF_OWN_HEALTH("Self care", "Taking care of own health"),
    FOOD_INTAKE("Self care", "Food intake"),
    PERFORMING_DAILY_ROUTINES("Practical tasks", "Performing daily routines"),
    ACQUIRING_GOODS_AND_SERVICES("Practical tasks", "Acquiring goods and services"),
    COOKING("Practical tasks", "Cooking"),
    HOUSEWORK("Practical tasks", "Housework"),
    CHANGING_BODY_POSITION("Mobility", "Changing body position"),
    MOVEMENT("Mobility", "Movement"),
    LIFT_AND_CARRY("Mobility", "Lift and carry"),
    WALKING("Mobility", "Walking"),
    MOVING_AROUND("Mobility", "Moving around"),
    MOVING_INTO_DIFFERENT_SURROUNDINGS("Mobility", "Moving into different surroundings"),
    USE_MEANS_OF_TRANSPORTATION("Mobility", "Use means of transportation"),
    ENDURANCE("Mobility", "Endurance"),
    STRENGTH("Mobility", "Strength"),
    LEARNING_SKILLS("Mental capacity", "Learning skills"),
    PROBLEM_SOLVING("Mental capacity", "Problem solving"),
    USE_OF_COMMUNICATION_TOOLS_AND_TECHNIQUES("Mental capacity", "Use of communication tools and techniques"),
    ORIENTATION_ABILITY("Mental capacity", "Orientation ability"),
    ENERGY_AND_WILLPOWER("Mental capacity", "Energy and willpower"),
    MEMORY("Mental capacity", "Memory"),
    EMOTIONAL_ABILITIES("Mental capacity", "Emotional abilities"),
    COGNITIVE_ABILITIES("Mental capacity", "Cognitive abilities"),
    ABILITY_TO_WORK("Participation in society", "Ability to work");


    private String main;
    private String sub;

    FunctionalAbility(String main, String sub) {
        this.main = main;
        this.sub = sub;
    }

    @Override
    public String toString() {
        return sub;
    }

    public static FunctionalAbility fromString(String functionalAbility) {
        for (FunctionalAbility fa : FunctionalAbility.values()) {
            if (fa.sub.equals(functionalAbility)) {
                return fa;
            }
        }
        throw new IllegalArgumentException("Functional ability " + functionalAbility + " could not be parsed");
    }

    public String getMain() {
        return main;
    }
}
