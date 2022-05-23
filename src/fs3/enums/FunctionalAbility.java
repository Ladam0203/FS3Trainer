package fs3.enums;

public enum FunctionalAbility {
    BATHING("Bathing header", "Bathing"),
    GOING_TO_THE_BATHROOM(null, "Going to the bathroom"),
    BODY_CARE(null, "Body care"),
    UNDRESSING_AND_DRESSING("Another header", "Undressing and dressing"),
    EATING(null, "Eating"),
    DRINKING(null, "Drinking"),
    TAKING_CARE_OF_OWN_HEALTH(null, "Taking care of own health"),
    FOOD_INTAKE(null, "Food intake"),
    PERFORMING_DAILY_ROUTINES(null, "Performing daily routines"),
    ACQUIRING_GOODS_AND_SERVICES(null, "Acquiring goods and services"),
    COOKING(null, "Cooking"),
    HOUSEWORK(null, "Housework"),
    CHANGING_BODY_POSITION(null, "Changing body position"),
    MOVEMENT(null, "Movement"),
    LIFT_AND_CARRY(null, "Lift and carry"),
    WALKING(null, "Walking"),
    MOVING_AROUND(null, "Moving around"),
    MOVING_INTO_DIFFERENT_SURROUNDINGS(null, "Moving into different surroundings"),
    USE_MEANS_OF_TRANSPORTATION(null, "Use means of transportation"),
    ENDURANCE(null, "Endurance"),
    STRENGTH(null, "Strength"),
    LEARNING_SKILLS(null, "Learning skills"),
    PROBLEM_SOLVING(null, "Problem solving"),
    USE_OF_COMMUNICATION_TOOLS_AND_TECHNIQUES(null, "Use of communication tools and techniques"),
    ORIENTATION_ABILITY(null, "Orientation ability"),
    ENERGY_AND_WILLPOWER(null, "Energy and willpower"),
    MEMORY(null, "Memory"),
    EMOTIONAL_ABILITIES(null, "Emotional abilities"),
    COGNITIVE_ABILITIES(null, "Cognitive abilities"),
    ABILITY_TO_WORK(null, "Ability to work");


    private String name;
    private String header;

    FunctionalAbility(String header, String name) {
        this.header = header;
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

    public String getHeader() {
        return header;
    }
}
