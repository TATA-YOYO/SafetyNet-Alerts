package com.safetynet.alerts.controller.dto;

import java.util.List;

public class PersonListWithChildNumberDto {
    private List<PersonDto> personDtoList;
    private int nbAdult;
    private int nbChild;



    public PersonListWithChildNumberDto(List<PersonDto> personDtoList, int nbAdult, int nbChild) {
        this.personDtoList = personDtoList;
        this.nbAdult = nbAdult;
        this.nbChild = nbChild;
    }
    public List<PersonDto> getPersonDtoList() {
        return personDtoList;
    }

    public int getNbAdult() {
        return nbAdult;
    }

    public int getNbChild() {
        return nbChild;
    }
}
