package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.PersonDtoListWithChildNumberDto;
import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.services.IServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {
    @Autowired
    private IServiceAPI serviceAPI;

    @GetMapping("/firestation")
    public PersonDtoListWithChildNumberDto getPersonListWithChildNumberDto(final Integer stationNumber) {
        if (stationNumber != null) {
            List<PersonDtoWithAddressAndPhone> personWithAddressAndPhoneDtoList = serviceAPI.getPersonDtoWithAddressAndPhoneList(serviceAPI.getAddress(stationNumber));
            int nbAdult = 0;
            int nbChild = 0;
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
        return new PersonDtoListWithChildNumberDto();
    }
}
