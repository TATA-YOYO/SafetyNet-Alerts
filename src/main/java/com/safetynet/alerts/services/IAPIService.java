package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.controller.dto.PDto;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;

import java.util.List;

public interface IAPIService {

    List<String> getAddressList(int stationNumber);

    List<MedicalRecord> getMedicalRecordList();

    int getStationNumber(String address);

    Person getPerson(String firstNameAndLastName);

    List<String> getEmailList(String city);

    boolean savePerson(PDto personDto);

    boolean updatePerson(PDto person);

    boolean removePerson(PDto person);

    boolean saveListOfFireStation(List<FireStationDto> fireStationDto);

    boolean updateFireStation(FireStationDto fireStationDto);

    boolean removeFireStation(Integer station);

    boolean saveMedicalRecord(MedicalRecordDto medicalRecordDto);

    boolean updateMedicalRecord(MedicalRecordDto medicalRecordDto);

    boolean removeMedicalRecord(MedicalRecordDto medicalRecordDto);

    List<Person> getPersonList();
}
