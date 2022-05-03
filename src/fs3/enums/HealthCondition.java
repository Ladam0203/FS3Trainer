package fs3.enums;

public enum HealthCondition {
    PROBLEMS_WITH_PERSONAL_CARE("Problems with personal care"),
    PROBLEMS_WITH_DAILY_ACTIVITES("Problems with daily activities"),
    PROBLEMS_WITH_MOBILITY_AND_MOVEMENT("Problems with mobility and movement"),
    PROBLEMS_WITH_FLUID_INTAKE("Problems with fluid intake"),
    PROBLEMS_WITH_FOOD_INTAKE("Problems with food intake"),
    INAPPROPRIATE_WEIGHT_CHANGE("Inappropriate weight change"),
    PROBLEMS_WITH_OBESITY("Problems with obesity"),
    PROBLEMS_WITH_UNDERWEIGHT("Problems with underweight"),
    PROBLEMS_WITH_SURGICAL_WOUNDS("Problems with surgical wounds"),
    PROBLEMS_WITH_DIABETIC_ULCERS("Problems with diabetic ulcers"),
    PROBLEMS_WITH_CANCEROUS_LESIONS("Problems with cancerous lesions");

    String healthConditionName;
    HealthCondition(String healthConditionName) {
        this.healthConditionName = healthConditionName;
    }
}
