package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;

public class CitizenFactory {
    public static Citizen createCitizen(boolean isTemplate){
        return isTemplate ? new CitizenTemplate() : new CitizenInstance();
    }
}
