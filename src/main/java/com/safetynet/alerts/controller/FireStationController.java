package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.controller.dto.PersonDtoListWithChildNumberDto;
import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.controller.dto.PersonWithLastNameAndPhoneDto;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.services.IServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FireStationController {
    @Autowired
    private IServiceAPI serviceAPI;

    @Autowired
    private IPersonController personController;

    @GetMapping("/firestation")
    public PersonDtoListWithChildNumberDto getPersonListWithChildNumberDto(final Integer stationNumber) {
        if (stationNumber != null) {
            List<PersonDtoWithAddressAndPhone> personWithAddressAndPhoneDtoList = serviceAPI.getPersonDtoWithAddressAndPhoneList(serviceAPI.getAddressList(stationNumber));
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

    @GetMapping("/phoneAlert")
    public List<String> getPhoneList(final Integer stationNumber) {
        List<String> phoneList = new ArrayList<>();
        List<PersonDtoWithAddressAndPhone> personWithAddressAndPhoneDtoList = serviceAPI.getPersonDtoWithAddressAndPhoneList(serviceAPI.getAddressList(stationNumber));
        Map<String, Integer> phoneMap = new HashMap<>();
        for (PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone : personWithAddressAndPhoneDtoList) {
            if (phoneMap.size() == 0) {
                phoneMap.put(personDtoWithAddressAndPhone.getPhone(), 0);
            } else if ((phoneMap.get(personDtoWithAddressAndPhone.getPhone()) == null) || phoneMap.get(personDtoWithAddressAndPhone.getPhone()) != 0) {
                phoneMap.put(personDtoWithAddressAndPhone.getPhone(), 0);
            }
        }
        for (PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone : personWithAddressAndPhoneDtoList) {
            if (phoneMap.get(personDtoWithAddressAndPhone.getPhone()) != 2) {
                phoneList.add(personDtoWithAddressAndPhone.getPhone());
                phoneMap.put(personDtoWithAddressAndPhone.getPhone(), 2);
            }
        }
        return phoneList;
    }

    @GetMapping("/flood/stations")
    public Map<String, List<PersonWithLastNameAndPhoneDto>> getStringListOfPersonWithLastNameAndPhoneDtoMap(List<Integer> stationNumbersList) {
        Map<String, List<PersonWithLastNameAndPhoneDto>> listOfPersonWithLastNameAndPhoneDtoMap = new HashMap<>();
        for (int stationNumber : stationNumbersList) {
            String address = serviceAPI.getAddressList(stationNumber).get(0);
            List<PersonWithLastNameAndPhoneDto> personWithLastNameAndPhoneDtoList = personController.getListOfPersonAndTheirNumberStation(address).getPersonWithLastNameAndPhoneDtoList();
            listOfPersonWithLastNameAndPhoneDtoMap.put(address, personWithLastNameAndPhoneDtoList);
        }
        return listOfPersonWithLastNameAndPhoneDtoMap;
    }

    @GetMapping("/communityEmail")
    public List<String> getEmailList(String city) {
        return serviceAPI.getEmailList(city);
    }

    @PostMapping("/firestation")
    public ResponseEntity<List<FireStationDto>> createFireStation(@RequestBody List<FireStationDto> fireStationDtoList) {
        if (serviceAPI.saveListOfFireStation(fireStationDtoList)) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(fireStationDtoList)
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/firestation")
    public FireStationDto updateFireStation(@RequestBody FireStationDto fireStationDto) {
        if (serviceAPI.updateFireStation(fireStationDto)) {
            return fireStationDto;
        }
        return fireStationDto;
    }

    @DeleteMapping("/firestation")
    public FireStationDto removeFireStation(@RequestBody FireStationDto fireStationDto) {
        if (serviceAPI.removeFireStation(fireStationDto.getStation())) {
            return fireStationDto;
        }
        return fireStationDto;
    }
}
