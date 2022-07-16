package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.ListOfPersonAndTheirNumberStation;
import com.safetynet.alerts.controller.dto.PersonDtoWithAgeAndOtherMember;
import com.safetynet.alerts.controller.dto.PersonWithAddressAgeEMail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

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
}
