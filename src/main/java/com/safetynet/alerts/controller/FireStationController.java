package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.PersonDto;
import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.controller.dto.PersonDtoListWithChildNumberDto;
import com.safetynet.alerts.controller.dto.PersonDtoWithAgeAndOtherMember;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.services.IServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FireStationController {
    private int nbAdult = 0;
    private int nbChild = 0;

    @Autowired
    private IServiceAPI serviceAPI;

    @GetMapping("/firestation")
    public PersonDtoListWithChildNumberDto getPersonListWithChildNumberDto(final Integer stationNumber) {
        if (stationNumber != null) {
            List<PersonDtoWithAddressAndPhone> personWithAddressAndPhoneDtoList = serviceAPI.getPersonDtoWithAddressAndPhoneList(serviceAPI.getAddress(stationNumber));
            nbAdult = 0;
            nbChild = 0;
            for (PersonDtoWithAddressAndPhone personWithAddressAndPhoneDto : personWithAddressAndPhoneDtoList) {
                for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
                    if (medicalRecord.getFirstName().equals(personWithAddressAndPhoneDto.getFirstName()) && medicalRecord.getLastName().equals(personWithAddressAndPhoneDto.getLastName())) {
                        if (medicalRecord.getAge() >= 18) {
                            nbAdult++;
                        } else if (medicalRecord.getAge() < 18) nbChild++;
                        break;
                    }
                }
            }
            return new PersonDtoListWithChildNumberDto(personWithAddressAndPhoneDtoList, nbAdult, nbChild);
        }
        return null;
    }

    @GetMapping("/childAlert")
    public List<PersonDtoWithAgeAndOtherMember> getPersonDtoWithAgeAndOtherMember(final String address) {
        List<PersonDtoWithAgeAndOtherMember> PersonDtoWithAgeAndOtherMemberList = new ArrayList<>();
        List<PersonDto> personDtoList = new ArrayList<>();
        List<PersonDto> childDtoList = new ArrayList<>();
        List<PersonDto> otherMembers = new ArrayList<>();
        for (PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone : serviceAPI.getPersonDtoWithAddressAndPhoneList("1509 Culver St")) {
            personDtoList.add(personDtoWithAddressAndPhone.getPersonDto());
            otherMembers.add(personDtoWithAddressAndPhone.getPersonDto());
        }
        for (PersonDto personDto : personDtoList) {
            for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
                if (medicalRecord.getFirstName().equals(personDto.getFirstName()) && medicalRecord.getLastName().equals(personDto.getLastName())) {
                    double age = medicalRecord.getAge();
                    if (age < 18) {
                        childDtoList.add(personDto);
                        otherMembers.remove(personDto);
                        PersonDtoWithAgeAndOtherMember personDtoWithAgeAndOtherMember = new PersonDtoWithAgeAndOtherMember(medicalRecord.getFirstName(),
                                medicalRecord.getLastName(), age, otherMembers);
                        PersonDtoWithAgeAndOtherMemberList.add(personDtoWithAgeAndOtherMember);
                        otherMembers.add(personDto);
                        break;
                    }
                    break;
                }
            }
        }
        if (childDtoList.size() != 0) {
            return PersonDtoWithAgeAndOtherMemberList;
        }
        return null;
    }
}
