package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository implements IPersonRepository {
    private static final Logger logger = LogManager.getLogger("PersonRepository");
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
        logger.debug("this person("+firstNameAndLastName+") is unknown");
        return null;
    }

    @Override
    public List<String> getEmailList(String city) {
        List<String> eMailList = new ArrayList<>();
        for (Person person : personList) {
            if (person.getCity().equals(city)) {
                eMailList.add(person.getEmail());
                logger.debug(person.getEmail()+"is added to the list of email");
            }
        }
        return eMailList;
    }

    @Override
    public boolean savePerson(Person person) {
        if (person.getFirstName() == null || person.getLastName() == null || person.getAddress() == null || person.getEmail() == null ||
                person.getPhone() == null || person.getCity() == null || person.getZip() == null || person.getFirstName().isEmpty() || person.getLastName().isEmpty() || person.getAddress().isEmpty() || person.getEmail().isEmpty() ||
                person.getPhone().isEmpty() || person.getCity().isEmpty() || person.getZip().isEmpty()) {
            logger.debug("this object("+person+") contains empty or a null value");
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
        logger.debug("this object("+person+") cannot be saved because is already saved");
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
        logger.debug("this object("+person+") is unknown");
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
        logger.debug("this object("+person+") is unknown");
        return false;
    }
}
