package com.safetynet.alerts.repository;

import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.models.Person;

import java.util.List;

public interface IPersonRepository {
    public List<Person> getPersonList();
    public List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(List<String> addressList);
    public List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(String address);
}
