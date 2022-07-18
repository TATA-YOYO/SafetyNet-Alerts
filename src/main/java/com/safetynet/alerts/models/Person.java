package com.safetynet.alerts.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;

import java.util.Date;
import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    @JsonIgnore
    public PersonDtoWithAddressAndPhone getPersonDtoWithAddressAndPhone() {
        PersonDtoWithAddressAndPhone personWithAdressandPhoneDto = new PersonDtoWithAddressAndPhone();
        personWithAdressandPhoneDto.setFirstName(firstName);
        personWithAdressandPhoneDto.setLastName(lastName);
        personWithAdressandPhoneDto.setAddress(address);
        personWithAdressandPhoneDto.setPhone(phone);
        return personWithAdressandPhoneDto;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
