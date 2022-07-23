package com.safetynet.alerts.controller.dto;

public class PersonWithAddressAgeEmail {
    private String lastName;
    private String address;
    private int age;
    private String eMail;

    public PersonWithAddressAgeEmail(String lastName, String address, int age, String eMail) {
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.eMail = eMail;
    }
    public PersonWithAddressAgeEmail() {

    }

    @Override
    public String toString() {
        return "PersonWithAddressAgeEmail{" +
                "lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", eMail='" + eMail + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
