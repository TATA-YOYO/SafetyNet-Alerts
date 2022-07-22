package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository implements IPersonRepository {
    private final List<Person> personList = new ArrayList<>();

    @Override
    public List<Person> getPersonList() {
        return personList;
    }

    @Override
    public Person getPerson(String firstNameAndLastName) {

        for (Person p : personList) {
            if ((p.getFirstName() + p.getLastName()).equals(firstNameAndLastName)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<String> getEmailList(String city) {
        List<String> eMailList = new ArrayList<>();
        for (Person person : personList) {
            if (person.getCity().equals(city)) {
                eMailList.add(person.getEmail());
            }
        }
        return eMailList;
    }

    @Override
    public boolean savePerson(Person person) {
        if (person.getFirstName() == null || person.getLastName() == null || person.getAddress() == null || person.getEmail() == null ||
                person.getPhone() == null || person.getCity() == null || person.getZip() == null || person.getFirstName().isEmpty() || person.getLastName().isEmpty() || person.getAddress().isEmpty() || person.getEmail().isEmpty() ||
                person.getPhone().isEmpty() || person.getCity().isEmpty() || person.getZip().isEmpty()) {
            return false;
        }
        boolean isPresent = false;
        for (Person p : personList) {
            if ((p.getFirstName() + p.getLastName()).equals(person.getFirstName() + person.getLastName())) {
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            personList.add(person);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePerson(Person person) {
        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            if ((p.getFirstName() + p.getLastName()).equals(person.getFirstName() + person.getLastName())) {
                personList.set(i, person);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removePerson(Person person) {
        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            if ((p.getFirstName() + p.getLastName()).equals(person.getFirstName() + person.getLastName())) {
                personList.remove(i);
                return true;
            }
        }
        return false;
    }
}
