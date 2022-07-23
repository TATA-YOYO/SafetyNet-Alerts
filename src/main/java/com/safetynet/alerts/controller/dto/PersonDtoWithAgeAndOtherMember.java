package com.safetynet.alerts.controller.dto;

import java.util.List;

public class PersonDtoWithAgeAndOtherMember extends PersonDto {


    private int Age;
    private List<PersonDto> otherMembers;

    public PersonDtoWithAgeAndOtherMember(String firstName, String lastName, int age,List<PersonDto> otherMembers) {
        super(firstName, lastName);
        Age = age;
        this.otherMembers= otherMembers;
    }

    @Override
    public String toString() {
        return "PersonDtoWithAgeAndOtherMember{" +
                "Age=" + Age +
                ", otherMembers=" + otherMembers +
                '}';
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public List<PersonDto> getOtherMembers() {
        return otherMembers;
    }

    public void setOtherMembers(List<PersonDto> otherMembers) {
        this.otherMembers = otherMembers;
    }
}
