package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.ListOfPersonAndTheirNumberStation;
import com.safetynet.alerts.controller.dto.PersonDtoWithAgeAndOtherMember;
import com.safetynet.alerts.controller.dto.PersonWithAddressAgeEMail;

import java.util.List;

public interface IPersonController {
    List<PersonDtoWithAgeAndOtherMember> getPersonDtoWithAgeAndOtherMember(final String address);

    ListOfPersonAndTheirNumberStation getListOfPersonAndTheirNumberStation(final String address);

    PersonWithAddressAgeEMail getPersonWithAddressAgeEMail(String firstName, String lastName);
}
