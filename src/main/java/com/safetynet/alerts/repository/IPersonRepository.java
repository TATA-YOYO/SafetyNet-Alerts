package com.safetynet.alerts.repository;

import com.safetynet.alerts.controller.dto.PersonDto;
import com.safetynet.alerts.models.Person;

import java.util.List;

public interface IPersonRepository {
    public List<Person> getPersonList();
    public List<PersonDto> getPersonToShareList(List<String> addressList);
}
