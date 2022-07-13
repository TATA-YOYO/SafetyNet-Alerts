package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.PersonDto;
import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.controller.dto.PersonDtoWithAgeAndOtherMember;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.services.IServiceAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    private static final Logger logger = LogManager.getLogger("PersonController");
    @Autowired
    private IServiceAPI serviceAPI;

    @GetMapping("/childAlert")
    public List<PersonDtoWithAgeAndOtherMember> getPersonDtoWithAgeAndOtherMember(final String address) {
        logger.info("childaddress " + address);
        List<PersonDtoWithAgeAndOtherMember> personDtoWithAgeAndOtherMemberList = new ArrayList<>();
        List<PersonDto> personDtoList = new ArrayList<>();
        List<PersonDto> childDtoList = new ArrayList<>();
        List<PersonDto> otherMembers = new ArrayList<>();
        for (PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone : serviceAPI.getPersonDtoWithAddressAndPhoneList(address)) {
            personDtoList.add(personDtoWithAddressAndPhone.getPersonDto(true));
            otherMembers.add(personDtoWithAddressAndPhone.getPersonDto(true));
        }
        for (PersonDto personDto : personDtoList) {
            for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
                if (medicalRecord.getFirstName().equals(personDto.getFirstName()) && medicalRecord.getLastName().equals(personDto.getLastName())) {
                    int age = medicalRecord.getAge();
                    if (age < 18) {
                        childDtoList.add(personDto);
                        otherMembers.remove(personDto);
                        PersonDtoWithAgeAndOtherMember personDtoWithAgeAndOtherMember = new PersonDtoWithAgeAndOtherMember(medicalRecord.getFirstName(),
                                medicalRecord.getLastName(), age, otherMembers);
                        personDtoWithAgeAndOtherMemberList.add(personDtoWithAgeAndOtherMember);
                        otherMembers.add(personDto);
                        break;
                    }
                    break;
                }
            }
        }
        logger.info("Result " + personDtoWithAgeAndOtherMemberList);
        return personDtoWithAgeAndOtherMemberList;
    }
}
