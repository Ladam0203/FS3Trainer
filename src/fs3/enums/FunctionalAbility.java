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
    ACQUIRING_GOODS_AND_SERVICES("Acquiring goods and services"),
    COOKING("Cooking"),
    HOUSEWORK("Housework"),
    CHANGING_BODY_POSITION("Changing body position"),
    MOVEMENT("Movement"),
    LIFT_AND_CARRY("Lift and carry"),
    WALKING("Walking"),
    MOVING_AROUND("Moving around"),
    MOVING_INTO_DIFFERENT_SURROUNDINGS("Moving into different surroundings"),
    USE_MEANS_OF_TRANSPORTATION("Use means of transportation"),
    ENDURANCE("Endurance"),
    STRENGTH("Strength"),
    LEARNING_SKILLS("Learning skills"),
    PROBLEM_SOLVING("Problem solving"),
    USE_OF_COMMUNICATION_TOOLS_AND_TECHNIQUES("Use of communication tools and techniques"),
    ORIENTATION_ABILITY("Orientation ability"),
    ENERGY_AND_WILLPOWER("Energy and willpower"),
    MEMORY("Memory"),
    EMOTIONAL_ABILITIES("Emotional abilities"),
    COGNITIVE_ABILITIES("Cognitive abilities"),
    ABILITY_TO_WORK("Ability to work");


    private String name;
    FunctionalAbility(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
    public static FunctionalAbility fromString(String functionalAbility) {
        for (FunctionalAbility fa : FunctionalAbility.values()) {
            if (fa.name.equals(functionalAbility)) {
                return fa;
            }
        }
        throw new IllegalArgumentException("Functional ability " + functionalAbility + " could not be parsed");
    }
}
