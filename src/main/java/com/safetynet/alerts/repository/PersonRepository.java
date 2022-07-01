package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {
    List<Person> personList = new ArrayList<>();

    public List<Person> getPersonList() {
        return personList;
    }
}
