package com.safetynet.alerts.controller;


import com.safetynet.alerts.controller.dto.PersonDtoListWithChildNumberDto;
import com.safetynet.alerts.controller.dto.PersonWithLastNameAndPhoneDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FireStationControllerTest {
    @Autowired
    private FireStationController fireStationController;

    @Autowired
    private PersonController personController;

    @Test
    public void getPhoneListTest() {
        List<String> resultList = fireStationController.getPhoneList(1);

        //Assert
        assertEquals(4, resultList.size());
    }

    @Test
    public void getPersonListWithChildNumberDtoTest() {
        //Act
        PersonDtoListWithChildNumberDto resultList = fireStationController.getPersonListWithChildNumberDto(1);

        //Assert
        assertEquals(1, resultList.getNbChild());
    }

    @Test
    public void getStringListOfPersonWithLastNameAndPhoneDtoMapTest() {

        //Arrange
        List<Integer> stationsList = new ArrayList<>();
        stationsList.add(1);
        stationsList.add(2);
        stationsList.add(3);
        stationsList.add(4);

        //Act
        Map<String, List<PersonWithLastNameAndPhoneDto>> result = fireStationController.getStringListOfPersonWithLastNameAndPhoneDtoMap(stationsList);

        //Assert
        assertNotNull(result);
    }

    @Test
    public void getEmailListTest(){
        //Act
        List<String> result = fireStationController.getEmailList("Culver");

        //Assert
        assertEquals(23,result.size());
    }
}
