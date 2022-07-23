package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.ListOfPersonAndTheirNumberStation;
import com.safetynet.alerts.controller.dto.PDto;
import com.safetynet.alerts.controller.dto.PersonDtoWithAgeAndOtherMember;
import com.safetynet.alerts.controller.dto.PersonWithAddressAgeEmail;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void setup(){
        loadData();
    }

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
        PersonWithAddressAgeEmail result = personController.getPersonWithAddressAgeEMail("Lily", "Cooper");

        //Assert
        assertEquals(28, result.getAge());
    }

    @Test
    void createPersonTest() throws Exception {
        //Arrange
        PDto pDto = new PDto();
        pDto.setFirstName("momo");
        pDto.setLastName("boss");
        pDto.setPhone("123-123-546");
        pDto.setEmail("test@test.com");
        pDto.setAddress("10 rue de test");
        pDto.setZip("75012");
        pDto.setCity("Paris");
        int size = personRepository.getPersonList().size();
       personController.createPerson(pDto);

        //Act
        List<Person> result = personRepository.getPersonList();

        //Assert
        assertEquals((size + 1), result.size());
    }

    @Test
    void updatePersonTest() {

        //Arrange
        PDto person = new PDto();
        person.setFirstName("Zach");
        person.setLastName("Zemicks");
        person.setPhone("123-123-546");
        person.setEmail("test@test.com");
        person.setAddress("908 73rd St");
        person.setZip("97451");
        person.setCity("Culver");
        Person p = personRepository.getPerson(person.getFirstName() + person.getLastName());

        //Act
       personController.updatePerson(person);
        Person result = personRepository.getPerson(person.getFirstName() + person.getLastName());
        //assert
        assertNotEquals(p.getPhone(), result.getPhone());

    }

    @Test
    public void removePersonTest() {

        //Arrange
        PDto person = new PDto();
        person.setFirstName("Zach");
        person.setLastName("Zemicks");
        person.setPhone("123-123-546");
        person.setEmail("test@test.com");
        person.setAddress("908 73rd St");
        person.setZip("97451");
        person.setCity("Culver");
        int size = personRepository.getPersonList().size();

        //Act
        personController.removePerson(person);
        int result = personRepository.getPersonList().size();

        //Assert
        assertEquals(size-1,result);
    }

    private void loadData(){
        personController.getListOfPersonAndTheirNumberStation("test");
    }
}
