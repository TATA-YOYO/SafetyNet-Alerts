package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;

import java.util.List;

public interface IServiceAPI {
    List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(List<String> addressList);

    List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(String address);

    List<String> getAddressList(int stationNumber);

    List<MedicalRecord> getMedicalRecordList();

    int getStationNumber(String address);

    Person getPerson(String firstNameAndLastName);

    List<String> getEmailList(String city);

    boolean savePerson(Person person);

    boolean updatePerson(Person person);

    boolean removePerson(Person person);

    boolean saveListOfFireStation(List<FireStationDto> fireStationDto);

    boolean updateFireStation(FireStationDto fireStationDto);

    boolean removeFireStation(Integer station);

    boolean saveMedicalRecord(MedicalRecordDto medicalRecordDto);

    boolean updateMedicalRecord(MedicalRecordDto medicalRecordDto);
}
