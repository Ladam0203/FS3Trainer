package fs3.be;

import fs3.enums.ObservationType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

public class Observation<T> {
    private ObjectProperty<ObservationType> type;
    private ObjectProperty<LocalDateTime> timestamp;
    private ObjectProperty<T> measurement;

    public Observation() {
        this.type = new SimpleObjectProperty<>();
        this.timestamp = new SimpleObjectProperty<>();
        this.measurement = new SimpleObjectProperty<>();
    }

    public Observation(ObservationType type, LocalDateTime timestamp, T measurement) {
        this.type = new SimpleObjectProperty<>(type);
        this.timestamp = new SimpleObjectProperty<>(timestamp);
        this.measurement = new SimpleObjectProperty<>(measurement);
    }

    public Observation(Observation<T> other) {
        this.type = new SimpleObjectProperty<>(other.getType());
        this.timestamp = new SimpleObjectProperty<>(other.getTimestamp());
        this.measurement = new SimpleObjectProperty<>(other.getMeasurement());
    }

    public ObservationType getType() {
        return type.get();
    }

    public ObjectProperty<ObservationType> typeProperty() {
        return type;
    }

    public void setType(ObservationType type) {
        this.type.set(type);
    }

    public LocalDateTime getTimestamp() {
        return timestamp.get();
    }

    public ObjectProperty<LocalDateTime> timestampProperty() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp.set(timestamp);
    }

    public T getMeasurement() {
        return measurement.get();
    }

    public ObjectProperty<T> measurementProperty() {
        return measurement;
    }

    public void setMeasurement(T measurement) {
        this.measurement.set(measurement);
    }
}
