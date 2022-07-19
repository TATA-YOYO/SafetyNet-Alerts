package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.ListOfPersonAndTheirNumberStation;
import com.safetynet.alerts.controller.dto.PersonDtoWithAgeAndOtherMember;
import com.safetynet.alerts.controller.dto.PersonWithAddressAgeEMail;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    PersonRepository personRepository;

    @Test
    void getPersonDtoWithAgeAndOtherMemberTest() {
        //Act
        List<PersonDtoWithAgeAndOtherMember> personDtoWithAgeAndOtherMemberList = personController.getPersonDtoWithAgeAndOtherMember("1509 Culver St");

        //Assert
        assertEquals(2, personDtoWithAgeAndOtherMemberList.size());
    }

    @Test
    void getListOfPersonAndTheirNumberStationTest() {
        //Act
        ListOfPersonAndTheirNumberStation listOfPersonAndTheirNumberStation = personController.getListOfPersonAndTheirNumberStation("1509 Culver St");

        //Assert
        assertEquals(3, listOfPersonAndTheirNumberStation.getStation());
    }

    @Test
    void getPersonWithAddressAgeEMail() {
        //Act
        PersonWithAddressAgeEMail result = personController.getPersonWithAddressAgeEMail("Lily", "Cooper");

        //Assert
        assertEquals(28, result.getAge());
    }

    @Test
    void createPersonTest() throws Exception {
        //Arrange
        Person person = new Person();
        person.setFirstName("momo");
        person.setLastName("boss");
        person.setPhone("123-123-546");
        person.setEmail("test@test.com");
        person.setAddress("10 rue de test");
        person.setZip("75012");
        person.setCity("Paris");
        personController.getListOfPersonAndTheirNumberStation("test");
        int size = personRepository.getPersonList().size();
        personController.createPerson(person);

        //Act
        List<Person> result = personRepository.getPersonList();

        //Assert
        assertEquals((size + 1), result.size());
    }

    @Test
    void updatePerson() {

        //Arrange
        Person person = new Person();
        person.setFirstName("Zach");
        person.setLastName("Zemicks");
        person.setPhone("123-123-546");
        person.setEmail("test@test.com");
        person.setAddress("908 73rd St");
        person.setZip("97451");
        person.setCity("Culver");
        personController.getListOfPersonAndTheirNumberStation("test");
        Person p = personRepository.getPerson(person.getFirstName() + person.getLastName());

        //Act
        personController.updatePerson(person);
        Person result = personRepository.getPerson(person.getFirstName() + person.getLastName());
        //assert
        assertNotEquals(p.getPhone(), result.getPhone());

    }
}
