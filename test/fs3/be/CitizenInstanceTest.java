package fs3.be;

import fs3.enums.ObservationType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CitizenInstanceTest {
    @Test
    void createBasedOnTemplate() {
        CitizenTemplate ct = new CitizenTemplate();
        Observations o = new Observations();

        Observation<BloodPressure> bp = new Observation<>(ObservationType.BLOOD_PRESSURE, LocalDateTime.now(), new BloodPressure(120, 80));

        o.getBloodPressure().add(bp);

        ct.setObservations(o);

        CitizenInstance ci = new CitizenInstance(ct);

        ct.getObservations().getBloodPressure().get(0).getMeasurement().setSystolic(100);

        assertEquals(120, ci.getObservations().getBloodPressure().get(0).getMeasurement().getSystolic());
    }
}