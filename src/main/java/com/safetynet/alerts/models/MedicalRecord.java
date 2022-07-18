package com.safetynet.alerts.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.safetynet.alerts.constants.Time;

import java.util.Date;
import java.util.List;

public class MedicalRecord {
    private String firstName;
    private String lastName;
    private Date birthdate;
    private List<String> medications;
    private List<String> allergies;

    @JsonIgnore
    private String firstNameAndLastName;

    public int getAge() {
        return (int) ((new Date().getTime() - birthdate.getTime()) / (Time.MILLISECOND_PER_DAY * Time.DAYS_IN_YEAR));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getFirstNameAndLastName() {
        return firstNameAndLastName;
    }

    public void concatenate() {
        if (firstName != null && lastName != null) {
            firstNameAndLastName = firstName + lastName;
        }
    }
}
