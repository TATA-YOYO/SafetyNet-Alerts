package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.controller.dto.PersonDtoListWithChildNumberDto;
import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.controller.dto.PersonWithLastNameAndPhoneDto;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.services.IServiceAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger("FireStationController");

    @Autowired
    private RequestCounter requestCounter;

    @Autowired
    private IServiceAPI serviceAPI;

    @Autowired
    private IPersonController personController;

    @GetMapping("/firestation")
    public PersonDtoListWithChildNumberDto getPersonListWithChildNumberDto(final Integer stationNumber) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "http://localhost:8080/firestation?stationNumber=" + stationNumber);
        if (stationNumber != null) {
            List<PersonDtoWithAddressAndPhone> personDtoWithAddressAndPhoneList = new ArrayList<>();
            for (Person person : serviceAPI.getPersonList()) {
                for (String address : serviceAPI.getAddressList(stationNumber)) {
                    if (person.getAddress().equals(address)) {
                        PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone = new PersonDtoWithAddressAndPhone();
                        personDtoWithAddressAndPhone.setFirstName(person.getFirstName());
                        personDtoWithAddressAndPhone.setLastName(person.getLastName());
                        personDtoWithAddressAndPhone.setAddress(person.getAddress());
                        personDtoWithAddressAndPhone.setPhone(person.getPhone());
                        personDtoWithAddressAndPhoneList.add(personDtoWithAddressAndPhone);
                    }
                }
            }
            int nbAdult = 0;
            int nbChild = 0;
            for (PersonDtoWithAddressAndPhone personWithAddressAndPhoneDto : personDtoWithAddressAndPhoneList) {
                for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
                    if (medicalRecord.getFirstName().equals(personWithAddressAndPhoneDto.getFirstName()) && medicalRecord.getLastName().equals(personWithAddressAndPhoneDto.getLastName())) {
                        if (medicalRecord.getAge() >= 18) {
                            nbAdult++;
                        } else if (medicalRecord.getAge() < 18) nbChild++;
                        break;
                    }
                }
            }
            PersonDtoListWithChildNumberDto personDtoListWithChildNumberDto = new PersonDtoListWithChildNumberDto(personDtoWithAddressAndPhoneList, nbAdult, nbChild);
            logger.info("Response of Query N°" + requestNumber + ": " + personDtoListWithChildNumberDto);
            return personDtoListWithChildNumberDto;
        }
        PersonDtoListWithChildNumberDto personDtoListWithChildNumberDto = new PersonDtoListWithChildNumberDto();
        logger.info("Response of Query N°" + requestNumber + ": " + personDtoListWithChildNumberDto);
        return personDtoListWithChildNumberDto;
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhoneList(final Integer stationNumber) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "http://localhost:8080/phoneAlert?firestation=" + stationNumber);
        if (stationNumber != null) {
            List<String> phoneList = new ArrayList<>();
            List<PersonDtoWithAddressAndPhone> personDtoWithAddressAndPhoneList = new ArrayList<>();
            for (Person person : serviceAPI.getPersonList()) {
                for (String address : serviceAPI.getAddressList(stationNumber)) {
                    if (person.getAddress().equals(address)) {
                        PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone = new PersonDtoWithAddressAndPhone();
                        personDtoWithAddressAndPhone.setFirstName(person.getFirstName());
                        personDtoWithAddressAndPhone.setLastName(person.getLastName());
                        personDtoWithAddressAndPhone.setAddress(person.getAddress());
                        personDtoWithAddressAndPhone.setPhone(person.getPhone());
                        personDtoWithAddressAndPhoneList.add(personDtoWithAddressAndPhone);
                    }
                }
            }
            Map<String, Integer> phoneMap = new HashMap<>();
            for (PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone : personDtoWithAddressAndPhoneList) {
                if (phoneMap.size() == 0) {
                    phoneMap.put(personDtoWithAddressAndPhone.getPhone(), 0);
                } else if ((phoneMap.get(personDtoWithAddressAndPhone.getPhone()) == null) || phoneMap.get(personDtoWithAddressAndPhone.getPhone()) != 0) {
                    phoneMap.put(personDtoWithAddressAndPhone.getPhone(), 0);
                }
            }
            for (PersonDtoWithAddressAndPhone personDtoWithAddressAndPhone : personDtoWithAddressAndPhoneList) {
                if (phoneMap.get(personDtoWithAddressAndPhone.getPhone()) != 2) {
                    phoneList.add(personDtoWithAddressAndPhone.getPhone());
                    phoneMap.put(personDtoWithAddressAndPhone.getPhone(), 2);
                }
            }
            logger.info("Response of Query N°" + requestNumber + ": " + phoneList);
            return phoneList;
        }
        List<String> phoneList = new ArrayList<>();
        logger.info("Response of Query N°" + requestNumber + ": " + phoneList);
        return phoneList;

    }

    @GetMapping("/flood/stations")
    public Map<String, List<PersonWithLastNameAndPhoneDto>> getStringListOfPersonWithLastNameAndPhoneDtoMap(@RequestParam List<Integer> stationNumbersList) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "http://localhost:8080/phoneAlert?firestation=" + stationNumbersList);
        Map<String, List<PersonWithLastNameAndPhoneDto>> listOfPersonWithLastNameAndPhoneDtoMap = new HashMap<>();
        for (int stationNumber : stationNumbersList) {
            String address = serviceAPI.getAddressList(stationNumber).get(0);
            List<PersonWithLastNameAndPhoneDto> personWithLastNameAndPhoneDtoList = personController.getListOfPersonAndTheirNumberStation(address).getPersonWithLastNameAndPhoneDtoList();
            listOfPersonWithLastNameAndPhoneDtoMap.put(address, personWithLastNameAndPhoneDtoList);
        }
        logger.info("Response of Query N°" + requestNumber + ": " + listOfPersonWithLastNameAndPhoneDtoMap);
        return listOfPersonWithLastNameAndPhoneDtoMap;
    }

    @GetMapping("/communityEmail")
    public List<String> getEmailList(String city) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "  http://localhost:8080/communityEmail?city=" + city);
        List<String> emailList = serviceAPI.getEmailList(city);
        logger.info("Response of Query N°" + requestNumber + ": " + emailList);
        return emailList;
    }

    @PostMapping("/firestation")
    public ResponseEntity<List<FireStationDto>> createFireStation(@RequestBody List<FireStationDto> fireStationDtoList) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "POST http://localhost:8080/firestation" + fireStationDtoList);
        if (serviceAPI.saveListOfFireStation(fireStationDtoList)) {

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(fireStationDtoList)
                    .toUri();
            logger.info("Response of Query N°" + requestNumber + ": " + "Code 201");
            return ResponseEntity.created(location).build();
        }
        logger.info("Response of Query N°" + requestNumber + ": " + "Code 204");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/firestation")
    public FireStationDto updateFireStation(@RequestBody FireStationDto fireStationDto) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "PUT http://localhost:8080/firestation" + fireStationDto);
        if (serviceAPI.updateFireStation(fireStationDto)) {
            logger.info("Response of Query N°" + requestNumber + ": " + fireStationDto);
            return fireStationDto;
        }
        FireStationDto fireStationDto01 = new FireStationDto();
        logger.info("Response of Query N°" + requestNumber + ": " + fireStationDto01);
        return fireStationDto01;
    }

    @DeleteMapping("/firestation")
    public FireStationDto removeFireStation(@RequestBody FireStationDto fireStationDto) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "DELETE http://localhost:8080/firestation" + fireStationDto);
        if (serviceAPI.removeFireStation(fireStationDto.getStation())) {
            logger.info("Response of Query N°" + requestNumber + ": " + fireStationDto);
            return fireStationDto;
        }
        FireStationDto fireStationDto01 = new FireStationDto();
        logger.info("Response of Query N°" + requestNumber + ": " + fireStationDto01);
        return fireStationDto01;
    }
}
