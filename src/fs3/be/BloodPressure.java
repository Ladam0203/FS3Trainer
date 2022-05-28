package fs3.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BloodPressure {
    private IntegerProperty systolic;
    private IntegerProperty diastolic;

    public BloodPressure() {
        this.systolic = new SimpleIntegerProperty();
        this.diastolic = new SimpleIntegerProperty();
    }

    public BloodPressure(BloodPressure other) {
        this.systolic = new SimpleIntegerProperty(other.systolic.get());
        this.diastolic = new SimpleIntegerProperty(other.diastolic.get());
    }

    public int getSystolic() {
        return systolic.get();
    }

    public IntegerProperty systolicProperty() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic.set(systolic);
    }

    public int getDiastolic() {
        return diastolic.get();
    }

    public IntegerProperty diastolicProperty() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic.set(diastolic);
    }

    public static BloodPressure fromString(String s) {
        String[] parts = s.split("/");
        BloodPressure bp = new BloodPressure();
        bp.setSystolic(Integer.parseInt(parts[0]));
        bp.setDiastolic(Integer.parseInt(parts[1]));
        return bp;
    }

    @Override
    public String toString() {
        return systolic.get() + "/" + diastolic.get();
    }
}
