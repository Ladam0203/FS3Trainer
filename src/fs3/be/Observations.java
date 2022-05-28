package fs3.be;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Observations {
    private List<Observation<BloodPressure>> bloodPressure; //systolic, diastolic
    /*
    private HashMap<LocalDateTime, Integer> hearthRate; //bpm
    private HashMap<LocalDateTime, Double> oxygen; //%
    private HashMap<LocalDateTime, Integer> bloodSugar; //mg/dL
    private HashMap<LocalDateTime, Integer> temperature; //°C
    private HashMap<LocalDateTime, Double> weight; //kg
    */
    public Observations() {
        this.bloodPressure = new ArrayList<Observation<BloodPressure>>();
        /*
        this.hearthRate = new HashMap<>();
        this.oxygen = new HashMap<>();
        this.bloodSugar = new HashMap<>();
        this.temperature = new HashMap<>();
        this.weight = new HashMap<>();
        */
    }

    public Observations(Observations other) {
        this.bloodPressure = new ArrayList<>();
        for (Observation<BloodPressure> obs : other.bloodPressure) {
            Observation<BloodPressure> newObs = new Observation<BloodPressure>(obs);
            newObs.setMeasurement(new BloodPressure(obs.getMeasurement()));
            this.bloodPressure.add(new Observation<BloodPressure>(newObs));
        }
        /*
        this.hearthRate = new HashMap<>(other.hearthRate);
        for (Map.Entry<LocalDateTime, Integer> entry : other.getHearthRate().entrySet()) {
            this.getHearthRate().put(entry.getKey(), entry.getValue());
        }
        this.oxygen = new HashMap<>(other.oxygen);
        for (Map.Entry<LocalDateTime, Double> entry : other.getOxygen().entrySet()) {
            this.getOxygen().put(entry.getKey(), entry.getValue());
        }
        this.bloodSugar = new HashMap<>(other.bloodSugar);
        for (Map.Entry<LocalDateTime, Integer> entry : other.getBloodSugar().entrySet()) {
            this.getBloodSugar().put(entry.getKey(), entry.getValue());
        }
        this.temperature = new HashMap<>(other.temperature);
        for (Map.Entry<LocalDateTime, Integer> entry : other.getTemperature().entrySet()) {
            this.getTemperature().put(entry.getKey(), entry.getValue());
        }
        this.weight = new HashMap<>(other.weight);
        for (Map.Entry<LocalDateTime, Double> entry : other.getWeight().entrySet()) {
            this.getWeight().put(entry.getKey(), entry.getValue());
        }
        */
    }

    public List<Observation<BloodPressure>> getBloodPressure() {
        return bloodPressure;
    }

    public void addBloodPressure(Observation<BloodPressure> obs) {
        this.bloodPressure.add(obs);
    }

    public void setBloodPressure(List<Observation<BloodPressure>> bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
}
