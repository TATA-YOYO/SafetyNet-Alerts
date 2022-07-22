package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.Person;

import java.util.List;

public interface IPersonRepository {
    List<Person> getPersonList();

    Person getPerson(String firstNameAndLastName);

    List<String> getEmailList(String city);

    boolean savePerson(Person person);

    boolean updatePerson(Person person);

    boolean removePerson(Person person);
}
