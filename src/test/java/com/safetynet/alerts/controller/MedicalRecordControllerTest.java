package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MedicalRecordControllerTest {

    @Autowired
    private FireStationController fireStationController;

    @Autowired
    MedicalRecordController medicalRecordController;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    void setup() {
        loadData();
    }

@Test
public void createMedicalRecordTest(){
        //Arrange
    MedicalRecordDto medicalRecordDto = new MedicalRecordDto();
    medicalRecordDto.setFirstName("momo");
    medicalRecordDto.setLastName("boss");
    medicalRecordDto.setBirthdate(new Date());
    int oldSize = medicalRecordRepository.getMedicalRecordList().size();

    //Act
    medicalRecordController.createMedicalRecord(medicalRecordDto);
    int newSize = medicalRecordRepository.getMedicalRecordList().size();

    //assert
   assertEquals(oldSize+1,newSize);
}

    private void loadData() {
        fireStationController.getEmailList("test");
    }

}
