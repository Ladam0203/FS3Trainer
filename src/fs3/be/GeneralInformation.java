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

    //copy constructor
    public GeneralInformation(GeneralInformation other) {
        this.coping = new SimpleStringProperty(other.getCoping());
        this.motivation = new SimpleStringProperty(other.getMotivation());
        this.resources = new SimpleStringProperty(other.getResources());
        this.roles = new SimpleStringProperty(other.getRoles());
        this.habits = new SimpleStringProperty(other.getHabits());
        this.educationAndJobs = new SimpleStringProperty(other.getEducationAndJobs());
        this.lifeStory = new SimpleStringProperty(other.getLifeStory());
        this.healthInformation = new SimpleStringProperty(other.getHealthInformation());
        this.equipmentAids = new SimpleStringProperty(other.getEquipmentAids());
        this.homeLayout = new SimpleStringProperty(other.getHomeLayout());
        this.network = new SimpleStringProperty(other.getNetwork());
    }

    public String getCoping() {return coping.get();}

    public void setCoping(String coping) {this.coping.set(coping);}

    public String getMotivation() {
        return motivation.get();
    }

    public void setMotivation(String motivation) {
        this.motivation.set(motivation);
    }

    public String getResources() {
        return resources.get();
    }

    public void setResources(String resources) {
        this.resources.set(resources);
    }

    public String getRoles() {
        return roles.get();
    }

    public void setRoles(String roles) {
        this.roles.set(roles);
    }

    public String getHabits() {
        return habits.get();
    }

    public void setHabits(String habits) {
        this.habits.set(habits);
    }

    public String getEducationAndJobs() {
        return educationAndJobs.get();
    }

    public void setEducationAndJobs(String educationAndJobs) {
        this.educationAndJobs.set(educationAndJobs);
    }

    public String getLifeStory() {
        return lifeStory.get();
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory.set(lifeStory);
    }

    public String getHealthInformation() {
        return healthInformation.get();
    }

    public void setHealthInformation(String healthInformation) {
        this.healthInformation.set(healthInformation);
    }

    public String getEquipmentAids() {
        return equipmentAids.get();
    }

    public void setEquipmentAids(String equipmentAids) {
        this.equipmentAids.set(equipmentAids);
    }

    public String getHomeLayout() {
        return homeLayout.get();
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayout.set(homeLayout);
    }

    public String getNetwork() {
        return network.get();
    }

    public void setNetwork(String network) {
        this.network.set(network);
    }
}
