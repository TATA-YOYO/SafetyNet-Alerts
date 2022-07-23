package com.safetynet.alerts.controller.dto;

import java.util.List;

public class PersonDtoListWithChildNumberDto {
    private List<PersonDtoWithAddressAndPhone> personWithAddressAndPhoneDtoList;
    private int nbAdult;
    private int nbChild;

    @Override
    public String toString() {
        return "PersonDtoListWithChildNumberDto{" +
                "personWithAddressAndPhoneDtoList=" + personWithAddressAndPhoneDtoList +
                ", nbAdult=" + nbAdult +
                ", nbChild=" + nbChild +
                '}';
    }

    public PersonDtoListWithChildNumberDto(List<PersonDtoWithAddressAndPhone> personWithAddressAndPhoneDtoList, int nbAdult, int nbChild) {
        this.personWithAddressAndPhoneDtoList = personWithAddressAndPhoneDtoList;
        this.nbAdult = nbAdult;
        this.nbChild = nbChild;
    }

    public PersonDtoListWithChildNumberDto(){}

    public List<PersonDtoWithAddressAndPhone> getPersonDtoList() {
        return personWithAddressAndPhoneDtoList;
    }

    public int getNbAdult() {
        return nbAdult;
    }

    public int getNbChild() {
        return nbChild;
    }
}
