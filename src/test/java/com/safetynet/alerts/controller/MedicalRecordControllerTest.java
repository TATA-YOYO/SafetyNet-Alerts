package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MedicalRecordControllerTest {
    private final List<String> stringList = new ArrayList<>();

    @Autowired
    private FireStationController fireStationController;

    @Autowired
    MedicalRecordController medicalRecordController;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;


    @BeforeEach
    void setup() {
        loadData();
        stringList.add("aznol:350mg");
        stringList.add("hydrapermazol:100mg");
    }

    @Test
    public void createMedicalRecordTest() {
        //Arrange
        MedicalRecordDto medicalRecordDto = new MedicalRecordDto();
        medicalRecordDto.setFirstName("momo");
        medicalRecordDto.setLastName("boss");
        medicalRecordDto.setBirthdate(new Date(1));
        medicalRecordDto.setMedications(stringList);
        int oldSize = medicalRecordRepository.getMedicalRecordList().size();

        //Act
        medicalRecordController.createMedicalRecord(medicalRecordDto);
        int newSize = medicalRecordRepository.getMedicalRecordList().size();

        //assert
        assertEquals(oldSize + 1, newSize);
    }

    @Test
    public void updateMedicalRecordTest() {
        //Arrange
        MedicalRecordDto medicalRecordDto = new MedicalRecordDto();
        medicalRecordDto.setFirstName("Allison");
        medicalRecordDto.setLastName("Boyd");
        medicalRecordDto.setBirthdate(new Date());
        medicalRecordDto.setMedications(stringList);

        //Act
        medicalRecordController.updateMedicalRecord(medicalRecordDto);
        List<String> medicationList = null;
        for (MedicalRecord m : medicalRecordRepository.getMedicalRecordList()) {
            if (m.getFirstNameAndLastName().equals(medicalRecordDto.getFirstName() + medicalRecordDto.getLastName())) {
                medicationList = m.getMedications();
            }
        }

        //Assert
        assertEquals(medicationList, stringList);
    }

    @Test
    public void removeMedicalRecordTest(){
        //Arrange
        MedicalRecordDto medicalRecordDto = new MedicalRecordDto();
        medicalRecordDto.setFirstName("Allison");
        medicalRecordDto.setLastName("Boyd");
        medicalRecordDto.setBirthdate(new Date());
        medicalRecordDto.setMedications(stringList);
        int oldSize = medicalRecordRepository.getMedicalRecordList().size();

        //Act
        medicalRecordController.removeMedicalRecord(medicalRecordDto);
        int newSize= medicalRecordRepository.getMedicalRecordList().size();

        //Assert
        assertEquals(oldSize-1,newSize);
    }

    private void loadData() {
        fireStationController.getEmailList("test");
    }

}
