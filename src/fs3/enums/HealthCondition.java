package fs3.enums;

public enum HealthCondition {
    //TODO: Extend with all subcategories of health conditions
    PROBLEMS_WITH_PERSONAL_CARE("Header #1", "Problems with personal care"),
    PROBLEMS_WITH_DAILY_ACTIVITIES("Header #2", "Problems with daily activities"),
    PROBLEMS_WITH_MOBILITY_AND_MOVEMENT(null, "Problems with mobility and movement"),
    PROBLEMS_WITH_FLUID_INTAKE(null, "Problems with fluid intake"),
    PROBLEMS_WITH_FOOD_INTAKE(null, "Problems with food intake"),
    INAPPROPRIATE_WEIGHT_CHANGE(null, "Inappropriate weight change"),
    PROBLEMS_WITH_OBESITY(null, "Problems with obesity"),
    PROBLEMS_WITH_UNDERWEIGHT(null, "Problems with underweight"),
    PROBLEMS_WITH_SURGICAL_WOUNDS(null, "Problems with surgical wounds"),
    PROBLEMS_WITH_DIABETIC_ULCERS(null, "Problems with diabetic ulcers"),
    PROBLEMS_WITH_CANCEROUS_LESIONS(null, "Problems with cancerous lesions"),
    PROBLEMS_WITH_PRESSURE_ULCERS(null, "Problems with pressure ulcers"),
    PROBLEMS_WITH_ARTERIAL_ULCER(null, "Problems with arterial ulcer"),
    PROBLEMS_WITH_VENOUS_ULCER(null, "Problems with venous ulcer"),
    PROBLEMS_WITH_MIXED_WOUNDS(null, "Problems with mixed wounds"),
    PROBLEMS_WITH_TRAUMA_WOUNDS(null, "Problems with trauma wounds"),
    OTHER_SKIN_AND_MUCOUS_MEMBRANE_PROBLEMS(null, "Other skin and mucous membrane problems"),
    PROBLEMS_WITH_COMMUNICATION(null, "Problems with communication"),
    PROBLEMS_WITH_SOCIALIZING(null, "Problems with socializing"),
    EMOTIONAL_PROBLEMS(null, "Emotional problems"),
    PROBLEMS_WITH_ABUSE(null, "Problems with abuse"),
    MENTAL_PROBLEMS(null, "Mental problems"),
    RESPIRATORY_PROBLEMS(null, "Respiratory problems"),
    CIRCULATORY_PROBLEMS(null, "Circulatory problems"),
    PROBLEMS_WITH_SEXUALITY(null, "Problems with sexuality"),
    ACUTE_PAIN(null, "Acute pain"),
    PERIODIC_PAIN(null, "Periodic pain"),
    CHRONIC_PAIN(null, "Chronic pain"),
    PROBLEMS_WITH_THE_SENSE_OF_SIGHT(null, "Problems with the  sense of  sight"),
    PROBLEMS_WITH_THE_SENSE_OF_SMELL(null, "Problems with the sense of smell"),
    HEARING_PROBLEMS(null, "Hearing problems"),
    PROBLEMS_WITH_SENSE_OF_TASTE(null, "Problems with the sense of taste"),
    PROBLEMS_WITH_SENSE_OF_TOUCH(null, "Problems with the sense of touch"),
    CIRCADIAN_RHYTHM_PROBLEMS(null, "Circadian rhythm problems"),
    SLEEP_PROBLEMS(null, "Sleep problems"),
    MEMORY_PROBLEMS(null, "Memory problems"),
    PROBLEMS_WITH_INSIGHT_INTO_TREATMENT_PURPOSES(null, "Problems with insight into treatment purposes"),
    PROBLEMS_WITH_DISEASE_INSIGHT(null, "Problems with disease insight"),
    COGNITIVE_PROBLEMS(null, "Cognitive problems"),
    PROBLEMS_WITH_URINATION(null, "Problems with urination"),
    PROBLEMS_WITH_URINARY_INCONTINENCE(null, "Problems with urinary incontinence"),
    PROBLEMS_WITH_STOOL_INCONTINENCE(null, "Problems with stool incontinence"),
    STOMACH_AND_INTESTINAL_PROBLEMS(null, "Stomach and intestinal problems"),
    PROBLEMS_WITH_LIQUID_FROM_THE_DRAIN(null, "Problems with liquid from the drain");

    private String main;
    private String sub;

    HealthCondition(String main, String sub) {
        this.main = main;
        this.sub = sub;
    }

    public static HealthCondition fromString(String healthCondition) {
        for (HealthCondition hc : HealthCondition.values()) {
            if (hc.sub.equals(healthCondition)) {
                return hc;
            }
        }
        throw new IllegalArgumentException("Health condition " + healthCondition + " could not be parsed");
    }

    @Override
    public String toString() {
        return sub;
    }

    public String getMain(){
        return main;
    }
}
