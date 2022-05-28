package fs3.be;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Observations {
    private HashMap<LocalDateTime, BloodPressure> bloodPressure;
    private HashMap<LocalDateTime, Integer> hearthRate;
    private HashMap<LocalDateTime, Double> oxygen;
    private HashMap<LocalDateTime, Integer> bloodSugar; //mg/dL
    private HashMap<LocalDateTime, Integer> temperature;
    private HashMap<LocalDateTime, Double> weight;

    public Observations() {
        this.bloodPressure = new HashMap<>();
        this.hearthRate = new HashMap<>();
        this.oxygen = new HashMap<>();
        this.bloodSugar = new HashMap<>();
        this.temperature = new HashMap<>();
        this.weight = new HashMap<>();
    }

    public Observations(Observations other) {
        this.bloodPressure = new HashMap<>(other.bloodPressure);
        for (Map.Entry<LocalDateTime, BloodPressure> entry : other.getBloodPressure().entrySet()) {
            this.getBloodPressure().put(entry.getKey(), new BloodPressure(entry.getValue()));
        }
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
    }

    public HashMap<LocalDateTime, BloodPressure> getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(HashMap<LocalDateTime, BloodPressure> bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public HashMap<LocalDateTime, Integer> getHearthRate() {
        return hearthRate;
    }

    public void setHearthRate(HashMap<LocalDateTime, Integer> hearthRate) {
        this.hearthRate = hearthRate;
    }

    public HashMap<LocalDateTime, Double> getOxygen() {
        return oxygen;
    }

    public void setOxygen(HashMap<LocalDateTime, Double> oxygen) {
        this.oxygen = oxygen;
    }

    public HashMap<LocalDateTime, Integer> getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(HashMap<LocalDateTime, Integer> bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public HashMap<LocalDateTime, Integer> getTemperature() {
        return temperature;
    }

    public void setTemperature(HashMap<LocalDateTime, Integer> temperature) {
        this.temperature = temperature;
    }

    public HashMap<LocalDateTime, Double> getWeight() {
        return weight;
    }

    public void setWeight(HashMap<LocalDateTime, Double> weight) {
        this.weight = weight;
    }
}
