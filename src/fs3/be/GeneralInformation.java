package fs3.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GeneralInformation {
    private StringProperty coping;
    private StringProperty motivation;
    private StringProperty resources;
    private StringProperty roles;
    private StringProperty habits;
    private StringProperty educationAndJobs;
    private StringProperty lifeStory;
    private StringProperty healthInformation;
    private StringProperty equipmentAids;
    private StringProperty homeLayout;
    private StringProperty network;

    public GeneralInformation() {
        this.coping = new SimpleStringProperty();
        this.motivation = new SimpleStringProperty();
        this.resources = new SimpleStringProperty();
        this.roles = new SimpleStringProperty();
        this.habits = new SimpleStringProperty();
        this.educationAndJobs = new SimpleStringProperty();
        this.lifeStory = new SimpleStringProperty();
        this.healthInformation = new SimpleStringProperty();
        this.equipmentAids = new SimpleStringProperty();
        this.homeLayout = new SimpleStringProperty();
        this.network = new SimpleStringProperty();
    }

    public String getCoping() {
        return coping.get();
    }

    public StringProperty getCopingProperty() {
        return coping;
    }

    public void setCoping(String coping) {
        this.coping.set(coping);
    }

    public String getMotivation() {
        return motivation.get();
    }

    public StringProperty motivationProperty() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation.set(motivation);
    }

    public String getResources() {
        return resources.get();
    }

    public StringProperty resourcesProperty() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources.set(resources);
    }

    public String getRoles() {
        return roles.get();
    }

    public StringProperty rolesProperty() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles.set(roles);
    }

    public String getHabits() {
        return habits.get();
    }

    public StringProperty habitsProperty() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits.set(habits);
    }

    public String getEducationAndJobs() {
        return educationAndJobs.get();
    }

    public StringProperty educationAndJobsProperty() {
        return educationAndJobs;
    }

    public void setEducationAndJobs(String educationAndJobs) {
        this.educationAndJobs.set(educationAndJobs);
    }

    public String getLifeStory() {
        return lifeStory.get();
    }

    public StringProperty lifeStoryProperty() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory.set(lifeStory);
    }

    public String getHealthInformation() {
        return healthInformation.get();
    }

    public StringProperty healthInformationProperty() {
        return healthInformation;
    }

    public void setHealthInformation(String healthInformation) {
        this.healthInformation.set(healthInformation);
    }

    public String getEquipmentAids() {
        return equipmentAids.get();
    }

    public StringProperty equipmentAidsProperty() {
        return equipmentAids;
    }

    public void setEquipmentAids(String equipmentAids) {
        this.equipmentAids.set(equipmentAids);
    }

    public String getHomeLayout() {
        return homeLayout.get();
    }

    public StringProperty homeLayoutProperty() {
        return homeLayout;
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayout.set(homeLayout);
    }

    public String getNetwork() {
        return network.get();
    }

    public StringProperty networkProperty() {
        return network;
    }

    public void setNetwork(String network) {
        this.network.set(network);
    }
}
