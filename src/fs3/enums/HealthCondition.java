package fs3.enums;

public enum HealthCondition {
    //TODO: Extend with all subcategories of health conditions
    PROBLEMS_WITH_PERSONAL_CARE("Functional level", "Problems with personal care"),
    PROBLEMS_WITH_DAILY_ACTIVITIES("Functional level", "Problems with daily activities"),
    PROBLEMS_WITH_MOBILITY_AND_MOVEMENT("Body movement", "Problems with mobility and movement"),
    PROBLEMS_WITH_FLUID_INTAKE("Nutrition", "Problems with fluid intake"),
    PROBLEMS_WITH_FOOD_INTAKE("Nutrition", "Problems with food intake"),
    INAPPROPRIATE_WEIGHT_CHANGE("Nutrition", "Inappropriate weight change"),
    PROBLEMS_WITH_OBESITY("Nutrition", "Problems with obesity"),
    PROBLEMS_WITH_UNDERWEIGHT("Nutrition", "Problems with underweight"),
    PROBLEMS_WITH_SURGICAL_WOUNDS("Skin and mucous membrane", "Problems with surgical wounds"),
    PROBLEMS_WITH_DIABETIC_ULCERS("Skin and mucous membrane", "Problems with diabetic ulcers"),
    PROBLEMS_WITH_CANCEROUS_LESIONS("Skin and mucous membrane", "Problems with cancerous lesions"),
    PROBLEMS_WITH_PRESSURE_ULCERS("Skin and mucous membrane", "Problems with pressure ulcers"),
    PROBLEMS_WITH_ARTERIAL_ULCER("Skin and mucous membrane", "Problems with arterial ulcer"),
    PROBLEMS_WITH_VENOUS_ULCER("Skin and mucous membrane", "Problems with venous ulcer"),
    PROBLEMS_WITH_MIXED_WOUNDS("Skin and mucous membrane", "Problems with mixed wounds"),
    PROBLEMS_WITH_TRAUMA_WOUNDS("Skin and mucous membrane", "Problems with trauma wounds"),
    OTHER_SKIN_AND_MUCOUS_MEMBRANE_PROBLEMS("Skin and mucous membrane", "Other skin and mucous membrane problems"),
    PROBLEMS_WITH_COMMUNICATION("Communication", "Problems with communication"),
    PROBLEMS_WITH_SOCIALIZING("Psychological-social", "Problems with socializing"),
    EMOTIONAL_PROBLEMS("Psychological-social", "Emotional problems"),
    PROBLEMS_WITH_ABUSE("Psychological-social", "Problems with abuse"),
    MENTAL_PROBLEMS("Psychological-social", "Mental problems"),
    RESPIRATORY_PROBLEMS("Respiration and circulation", "Respiratory problems"),
    CIRCULATORY_PROBLEMS("Respiration and circulation", "Circulatory problems"),
    PROBLEMS_WITH_SEXUALITY("Sexuality", "Problems with sexuality"),
    ACUTE_PAIN("Pain and sensory system", "Acute pain"),
    PERIODIC_PAIN("Pain and sensory system", "Periodic pain"),
    CHRONIC_PAIN("Pain and sensory system", "Chronic pain"),
    PROBLEMS_WITH_THE_SENSE_OF_SIGHT("Pain and sensory system", "Problems with the  sense of  sight"),
    PROBLEMS_WITH_THE_SENSE_OF_SMELL("Pain and sensory system", "Problems with the sense of smell"),
    HEARING_PROBLEMS("Pain and sensory system", "Hearing problems"),
    PROBLEMS_WITH_SENSE_OF_TASTE("Pain and sensory system", "Problems with the sense of taste"),
    PROBLEMS_WITH_SENSE_OF_TOUCH("Pain and sensory system", "Problems with the sense of touch"),
    CIRCADIAN_RHYTHM_PROBLEMS("Sleep and rest", "Circadian rhythm problems"),
    SLEEP_PROBLEMS("Sleep and rest", "Sleep problems"),
    MEMORY_PROBLEMS("Knowledge and development", "Memory problems"),
    PROBLEMS_WITH_INSIGHT_INTO_TREATMENT_PURPOSES("Knowledge and development", "Problems with insight into treatment purposes"),
    PROBLEMS_WITH_DISEASE_INSIGHT("Knowledge and development", "Problems with disease insight"),
    COGNITIVE_PROBLEMS("Knowledge and development", "Cognitive problems"),
    PROBLEMS_WITH_URINATION("Secretion of bodily waste products", "Problems with urination"),
    PROBLEMS_WITH_URINARY_INCONTINENCE("Secretion of bodily waste products", "Problems with urinary incontinence"),
    PROBLEMS_WITH_STOOL_INCONTINENCE("Secretion of bodily waste products", "Problems with stool incontinence"),
    STOMACH_AND_INTESTINAL_PROBLEMS("Secretion of bodily waste products", "Stomach and intestinal problems");

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
