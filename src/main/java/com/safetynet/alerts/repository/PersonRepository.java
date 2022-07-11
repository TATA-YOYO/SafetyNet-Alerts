package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.controller.dto.PersonDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository implements IPersonRepository {
    private List<Person> personList = new ArrayList<>();

    @Override
    public List<Person> getPersonList() {
        return personList;
    }

    @Override
    public List<PersonDto> getPersonToShareList(String address) {
        List<PersonDto> personDtoList = new ArrayList<>();
        for (Person p : personList) {
            if (p.getAddress().equals(address)) {
                personDtoList.add(p.getPersonToShare());
            }
        }
        return personDtoList;
    }
}
