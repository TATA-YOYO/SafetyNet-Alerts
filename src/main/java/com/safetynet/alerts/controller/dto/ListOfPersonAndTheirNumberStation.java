package com.safetynet.alerts.controller.dto;

import java.util.List;

public class ListOfPersonAndTheirNumberStation {
    private List<PersonWithLastNameAndPhoneDto> personWithLastNameAndPhoneDtoList;
    private int station;

    @Override
    public String toString() {
        return "ListOfPersonAndTheirNumberStation{" +
                "personWithLastNameAndPhoneDtoList=" + personWithLastNameAndPhoneDtoList +
                ", station=" + station +
                '}';
    }

    public ListOfPersonAndTheirNumberStation(List<PersonWithLastNameAndPhoneDto> personWithLastNameAndPhoneDtoList, int station) {
        this.personWithLastNameAndPhoneDtoList = personWithLastNameAndPhoneDtoList;
        this.station = station;
    }
    public ListOfPersonAndTheirNumberStation(List<PersonWithLastNameAndPhoneDto> personWithLastNameAndPhoneDtoList) {
        this.personWithLastNameAndPhoneDtoList = personWithLastNameAndPhoneDtoList;
    }

    public List<PersonWithLastNameAndPhoneDto> getPersonWithLastNameAndPhoneDtoList() {
        return personWithLastNameAndPhoneDtoList;
    }

    public void setPersonWithLastNameAndPhoneDtoList(List<PersonWithLastNameAndPhoneDto> personWithLastNameAndPhoneDtoList) {
        this.personWithLastNameAndPhoneDtoList = personWithLastNameAndPhoneDtoList;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }
}
