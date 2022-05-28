package fs3.be;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CitizenInstanceTest {
    @Test
    void createBasedOnTemplate() {
        CitizenTemplate ct = new CitizenTemplate();
        Observations o = new Observations();
        BloodPressure bp = new BloodPressure();
        bp.setSystolic(120);
        bp.setDiastolic(80);
        LocalDateTime now = LocalDateTime.now();
        o.getBloodPressure().put(now, bp);

        ct.setObservations(o);

        CitizenInstance ci = new CitizenInstance(ct);

        ct.getObservations().getBloodPressure().get(now).setSystolic(90);

        assertEquals(120, ci.getObservations().getBloodPressure().get(now).getSystolic());
    }
}