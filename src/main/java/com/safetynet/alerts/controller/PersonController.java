package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.*;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.services.IServiceAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class PersonController {
    private static final Logger logger = LogManager.getLogger("PersonController");
    @Autowired
    private IServiceAPI serviceAPI;

    @GetMapping("/childAlert")
    public List<PersonDtoWithAgeAndOtherMember> getPersonDtoWithAgeAndOtherMember(final String address) {
        List<PersonDtoWithAgeAndOtherMember> personDtoWithAgeAndOtherMemberList = new ArrayList<>();
        List<PersonDto> personDtoList = new ArrayList<>();
        for (PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone : serviceAPI.getPersonDtoWithAddressAndPhoneList(address)) {
            personDtoList.add(personDtoWithAddressAndPhone.getPersonDto(true));
        }
        for (PersonDto personDto : personDtoList) {
            for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
                if (medicalRecord.getFirstName().equals(personDto.getFirstName()) && medicalRecord.getLastName().equals(personDto.getLastName())) {
                    int age = medicalRecord.getAge();
                    if (age < 18) {
                        List<PersonDto> otherMembers = new ArrayList<>();
                        for (PersonDto personDto2 : personDtoList) {
                            if (!Objects.equals(personDto2.getFirstName(), personDto.getFirstName())) {
                                otherMembers.add(personDto2);
                            }
                        }
                        PersonDtoWithAgeAndOtherMember personDtoWithAgeAndOtherMember = new PersonDtoWithAgeAndOtherMember(medicalRecord.getFirstName(),
                                medicalRecord.getLastName(), age, otherMembers);
                        personDtoWithAgeAndOtherMemberList.add(personDtoWithAgeAndOtherMember);
                        break;
                    }
                    break;
                }
            }
        }
        logger.info("Result " + personDtoWithAgeAndOtherMemberList);
        return personDtoWithAgeAndOtherMemberList;
    }

    @GetMapping("/fire")
    public ListOfPersonAndTheirNumberStation getListOfPersonAndTheirNumberStation(final String address) {
        List<PersonWithLastNameAndPhoneDto> personWithLastNameAndPhoneDtoList = new ArrayList<>();
        for (PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone : serviceAPI.getPersonDtoWithAddressAndPhoneList(address)) {
            for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
                String firstNameAndLastName = personDtoWithAddressAndPhone.getFirstName() + personDtoWithAddressAndPhone.getLastName();
                if (medicalRecord.getFirstNameAndLastName().equals(firstNameAndLastName)) {
                    PersonWithLastNameAndPhoneDto personWithLastNameAndPhoneDto = new PersonWithLastNameAndPhoneDto
                            (personDtoWithAddressAndPhone.getLastName(), personDtoWithAddressAndPhone.getPhone(),
                                    medicalRecord.getAge(), medicalRecord.getMedications(), medicalRecord.getAllergies());
                    personWithLastNameAndPhoneDtoList.add(personWithLastNameAndPhoneDto);
                    break;
                }
            }
        }
        return new ListOfPersonAndTheirNumberStation(personWithLastNameAndPhoneDtoList, serviceAPI.getStationNumber(address));
    }

    @GetMapping("/personInfo")
    public PersonWithAddressAgeEMail getPersonWithAddressAgeEMail(String firstName, String lastName) {
        if (serviceAPI.getPerson(firstName + lastName) != null) {
            Person person = serviceAPI.getPerson(firstName + lastName);
            for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
                if (medicalRecord.getFirstNameAndLastName().equals(person.getFirstName()+person.getLastName())){
                    return new PersonWithAddressAgeEMail(person.getLastName(), person.getAddress(), medicalRecord.getAge(), person.getEmail());
                }
            }
        }
        return new PersonWithAddressAgeEMail();
    }
}
