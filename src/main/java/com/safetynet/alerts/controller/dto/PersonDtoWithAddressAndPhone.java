package com.safetynet.alerts.controller.dto;

public class PersonDtoWithAddressAndPhone extends PersonDto {
    private String address;
    private String phone;

    @Override
    public String toString() {
        return "PersonDtoWithAddressAndPhone{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

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
}
