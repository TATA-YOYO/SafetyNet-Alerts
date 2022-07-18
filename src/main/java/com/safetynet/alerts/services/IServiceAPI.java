package com.safetynet.alerts.services;

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
}
