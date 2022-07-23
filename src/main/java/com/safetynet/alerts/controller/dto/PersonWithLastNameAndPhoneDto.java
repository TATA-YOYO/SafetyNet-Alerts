package com.safetynet.alerts.controller.dto;

import java.util.List;

public class PersonWithLastNameAndPhoneDto {
    private String lastName;
    private String Phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;

    public PersonWithLastNameAndPhoneDto(String lastName, String phone, int age, List<String> medications, List<String> allergies) {
        this.lastName = lastName;
        Phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "PersonWithLastNameAndPhoneDto{" +
                "lastName='" + lastName + '\'' +
                ", Phone='" + Phone + '\'' +
                ", age=" + age +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
