package com.safetynet.alerts.services.util;

import com.safetynet.alerts.controller.dto.PDto;
import com.safetynet.alerts.models.Person;

public class PersonFactory {
    public static Person getPerson(PDto pDto) {
        Person person = new Person();
        person.setFirstName(pDto.getFirstName());
        person.setLastName(pDto.getLastName());
        person.setAddress(pDto.getAddress());
        person.setCity(pDto.getCity());
        person.setZip(pDto.getZip());
        person.setPhone(pDto.getPhone());
        person.setEmail(pDto.getEmail());
        return person;
    }

    ;
}
