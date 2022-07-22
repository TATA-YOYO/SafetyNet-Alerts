package com.safetynet.alerts.controller;


import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.controller.dto.PersonDtoListWithChildNumberDto;
import com.safetynet.alerts.controller.dto.PersonWithLastNameAndPhoneDto;
import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FireStationControllerTest {
    @Autowired
    private FireStationController fireStationController;

    @Autowired
    private PersonController personController;

    @Autowired
    FireStationRepository fireStationRepository;

    @BeforeEach
    void setup(){
        loadData();
    }

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
        stationsList.add(3);
        stationsList.add(4);

        //Act
        Map<String, List<PersonWithLastNameAndPhoneDto>> result = fireStationController.getStringListOfPersonWithLastNameAndPhoneDtoMap(stationsList);

        //Assert
        assertNotNull(result);
    }

    @Test
    public void getEmailListTest() {
        //Act
        List<String> result = fireStationController.getEmailList("Culver");

        //Assert
        assertEquals(23, result.size());
    }

    @Test
    public void createFireStationTest() {

        //Arrange
        List<FireStationDto> fireStationDtosList = new ArrayList<>();
        FireStationDto fireStationDto = new FireStationDto();
        fireStationDto.setStation(5);
        fireStationDto.setAddress("10 rue du test des fire station");
        FireStationDto fireStationDto2 = new FireStationDto();
        fireStationDto2.setStation(6);
        fireStationDto2.setAddress("10 rue du bobo");
        fireStationDtosList.add(fireStationDto);
        fireStationDtosList.add(fireStationDto2);
        int oldSize = fireStationRepository.getList().size();

        //Act
        fireStationController.createFireStation(fireStationDtosList);
        int newSize = fireStationRepository.getList().size();

        //Assert
        assertEquals(oldSize + 2, newSize);
    }

    @Test
    public void updateFireStationTest() {
        //Arrange
        FireStationDto fireStationDto = new FireStationDto();
        fireStationDto.setStation(1);
        fireStationDto.setAddress("489 Manchester St");
        int oldStationNumber = 0;
        for (FireStation f : fireStationRepository.getList()) {
            if (f.getAddress().equals("489 Manchester St")) {
                oldStationNumber = f.getStation();
            }
        }

        //Act
        fireStationController.updateFireStation(fireStationDto);
        int newStationNumber = 0;
        for (FireStation f : fireStationRepository.getList()) {
            if (f.getAddress().equals("489 Manchester St")) {
                newStationNumber = f.getStation();
                break;
            }
        }

        //Assert
        assertNotEquals(oldStationNumber, newStationNumber);
    }

    @Test
    public void removeFireStation() {
        //Arrange
        int oldSize = fireStationRepository.getList().size();
        FireStationDto fireStationDto = new FireStationDto();
        fireStationDto.setStation(2);
        fireStationDto.setAddress("951 LoneTree Rd");
        //Act
        fireStationController.removeFireStation(fireStationDto);
        int newSize = fireStationRepository.getList().size();

        //Assert
        assertEquals(oldSize - 3, newSize);
    }

    private void loadData(){
        fireStationController.getEmailList("test");
    }
}
