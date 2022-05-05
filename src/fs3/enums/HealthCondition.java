package fs3.enums;

public enum HealthCondition {
    //TODO: Extend with all subcategories of health conditions
    PROBLEMS_WITH_PERSONAL_CARE("Problems with personal care"),
    PROBLEMS_WITH_DAILY_ACTIVITIES("Problems with daily activities"),
    PROBLEMS_WITH_MOBILITY_AND_MOVEMENT("Problems with mobility and movement"),
    PROBLEMS_WITH_FLUID_INTAKE("Problems with fluid intake"),
    PROBLEMS_WITH_FOOD_INTAKE("Problems with food intake"),
    INAPPROPRIATE_WEIGHT_CHANGE("Inappropriate weight change"),
    PROBLEMS_WITH_OBESITY("Problems with obesity"),
    PROBLEMS_WITH_UNDERWEIGHT("Problems with underweight"),
    PROBLEMS_WITH_SURGICAL_WOUNDS("Problems with surgical wounds"),
    PROBLEMS_WITH_DIABETIC_ULCERS("Problems with diabetic ulcers"),
    PROBLEMS_WITH_CANCEROUS_LESIONS("Problems with cancerous lesions"),
    PROBLEMS_WITH_PRESSURE_ULCERS("Problems with pressure ulcers"),
    PROBLEMS_WITH_ARTERIAL_ULCER("Problems with arterial ulcer"),
    PROBLEMS_WITH_VENOUS_ULCER("Problems with venous ulcer"),
    PROBLEMS_WITH_MIXED_WOUNDS("Problems with mixed wounds"),
    PROBLEMS_WITH_TRAUMA_WOUNDS("Problems with trauma wounds"),
    OTHER_SKIN_AND_MUCOUS_MEMBRANE_PROBLEMS("Other skin and mucous membrane problems"),
    PROBLEMS_WITH_COMMUNICATION("Problems with communication"),
    PROBLEMS_WITH_SOCIALIZING("Problems with socializing"),
    EMOTIONAL_PROBLEMS("Emotional problems"),
    PROBLEMS_WITH_ABUSE("Problems with abuse"),
    MENTAL_PROBLEMS("Mental problems"),
    RESPIRATORY_PROBLEMS("Respiratory problems"),
    CIRCULATORY_PROBLEMS("Circulatory problems"),
    PROBLEMS_WITH_SEXUALITY("Problems with sexuality"),
    ACUTE_PAIN("Acute pain"),
    PERIODIC_PAIN("Periodic pain"),
    CHRONIC_PAIN("Chronic pain"),
    PROBLEMS_WITH_THE_SENSE_OF_SIGHT("Problems with the  sense of  sight"),
    PROBLEMS_WITH_THE_SENSE_OF_SMELL("Problems with the sense of smell"),
    HEARING_PROBLEMS("Hearing problems"),
    PROBLEMS_WITH_SENSE_OF_TASTE("Problems with the sense of taste"),
    PROBLEMS_WITH_SENSE_OF_TOUCH("Problems with the sense of touch"),
    CIRCADIAN_RHYTHM_PROBLEMS("Circadian rhythm problems"),
    SLEEP_PROBLEMS("Sleep problems"),
    MEMORY_PROBLEMS("Memory problems"),
    PROBLEMS_WITH_INSIGHT_INTO_TREATMENT_PURPOSES("Problems with insight into treatment purposes"),
    PROBLEMS_WITH_DISEASE_INSIGHT("Problems with disease insight"),
    COGNITIVE_PROBLEMS("Cognitive problems"),
    PROBLEMS_WITH_URINATION("Problems with urination"),
    PROBLEMS_WITH_URINARY_INCONTINENCE("Problems with urinary incontinence"),
    PROBLEMS_WITH_STOOL_INCONTINENCE("Problems with stool incontinence"),
    STOMACH_AND_INTESTINAL_PROBLEMS("Stomach and intestinal problems"),
    PROBLEMS_WITH_LIQUID_FROM_THE_DRAIN("Problems with liquid from the drain");

    private String name;
    HealthCondition(String name) {
        this.name = name;
    }
    public static HealthCondition fromString(String healthCondition) {
        for (HealthCondition hc : HealthCondition.values()) {
            if (hc.name.equals(healthCondition)) {
                return hc;
            }
        }
        throw new IllegalArgumentException("Health condition " + healthCondition + " could not be parsed");
    }

    @Override
    public String toString() {
        return name;
    }
}
