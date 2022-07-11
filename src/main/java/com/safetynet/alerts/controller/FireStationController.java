package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.PersonDto;
import com.safetynet.alerts.controller.dto.PersonListWithChildNumberDto;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.services.IServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {
    private int nbAdult = 0;
    private int nbChild = 0;
    @Autowired
    private IServiceAPI serviceAPI;

    @GetMapping("/firestation")
    public PersonListWithChildNumberDto getPersonToShareObject(final Integer stationNumber) {
        if (stationNumber != null) {
            List<PersonDto> personDtoList = serviceAPI.getPersonToShareList(serviceAPI.getAddress(stationNumber));
            nbAdult = 0;
            nbChild = 0;
            for (PersonDto personDto : personDtoList) {
                for (MedicalRecord m : serviceAPI.getMedicalRecordList()) {
                    if (m.getFirstName().equals(personDto.getFirstName()) && m.getLastName().equals(personDto.getLastName())) {
                        if (m.getAge() >= 18) {
                            nbAdult++;
                        } else if (m.getAge() < 18) nbChild++;
                        break;
                    }
                }
            }
            return new PersonListWithChildNumberDto(personDtoList, nbAdult, nbChild);
        }
        return null;
    }
}
