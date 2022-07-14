package com.safetynet.alerts.controller.dto;

public class PersonDtoWithAddressAndPhone extends PersonDto {
    private String address;
    private String phone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PersonDto getPersonDto(boolean hasTheRight) {
        if (hasTheRight) {
            return new PersonDto(super.getFirstName(), super.getLastName());
        }
        return new PersonDto();
    }
}
