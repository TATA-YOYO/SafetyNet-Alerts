package com.safetynet.alerts.repository;

import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.models.Person;

import java.util.List;

public interface IPersonRepository {
    List<Person> getPersonList();

    List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(List<String> addressList);

    List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(String address);

    Person getPerson(String firstNameAndLastName);

}
