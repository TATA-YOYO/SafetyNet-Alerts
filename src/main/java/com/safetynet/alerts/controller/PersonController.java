package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.*;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.services.IAPIService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class PersonController implements IPersonController {
    private static final Logger logger = LogManager.getLogger("PersonController");
    @Autowired
    private IAPIService APIService;

    @Autowired
    private RequestCounter requestCounter;

    @Override
    @GetMapping("/childAlert")
    public List<PersonDtoWithAgeAndOtherMember> getPersonDtoWithAgeAndOtherMember(final String address) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "http://localhost:8080/childAlert?address=" + address);
        List<PersonDtoWithAgeAndOtherMember> personDtoWithAgeAndOtherMemberList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        for (Person person : APIService.getPersonList()) {
            if (person.getAddress().equals(address)) {
                personList.add(person);
            }
        }
        logger.debug("List of person is filtered");
        for (Person person : personList) {
            for (MedicalRecord medicalRecord : APIService.getMedicalRecordList()) {
                if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                    int age = medicalRecord.getAge();
                    if (age < 18) {
                        List<PersonDto> otherMembers = new ArrayList<>();
                        for (Person person2 : personList) {
                            if (!Objects.equals(person2.getFirstName(), person.getFirstName())) {
                                PersonDto personDto = new PersonDto();
                                personDto.setFirstName(person2.getFirstName());
                                personDto.setLastName(person2.getLastName());
                                otherMembers.add(personDto);
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
        logger.info("Response of Query N°" + requestNumber + ": " + personDtoWithAgeAndOtherMemberList);
        return personDtoWithAgeAndOtherMemberList;
    }

    @Override
    @GetMapping("/fire")
    public ListOfPersonAndTheirNumberStation getListOfPersonAndTheirNumberStation(final String address) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "http://localhost:8080/fire?address=" + address);
        List<PersonWithLastNameAndPhoneDto> personWithLastNameAndPhoneDtoList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        for (Person person : APIService.getPersonList()) {
            if (person.getAddress().equals(address)) {
                personList.add(person);
            }
        }
        for (Person person : personList) {
            for (MedicalRecord medicalRecord : APIService.getMedicalRecordList()) {
                String firstNameAndLastName = person.getFirstName() + person.getLastName();
                if (medicalRecord.getFirstNameAndLastName().equals(firstNameAndLastName)) {
                    PersonWithLastNameAndPhoneDto personWithLastNameAndPhoneDto = new PersonWithLastNameAndPhoneDto
                            (person.getLastName(), person.getPhone(),
                                    medicalRecord.getAge(), medicalRecord.getMedications(), medicalRecord.getAllergies());
                    personWithLastNameAndPhoneDtoList.add(personWithLastNameAndPhoneDto);
                    break;
                }
            }
        }
        ListOfPersonAndTheirNumberStation listOfPersonAndTheirNumberStation = new ListOfPersonAndTheirNumberStation(personWithLastNameAndPhoneDtoList, APIService.getStationNumber(address));
        logger.info("Response of Query N°" + requestNumber + ": " + listOfPersonAndTheirNumberStation);
        return listOfPersonAndTheirNumberStation;
    }

    @Override
    @GetMapping("/personInfo")
    public PersonWithAddressAgeEmail getPersonWithAddressAgeEMail(String firstName, String lastName) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "http://localhost:8080/personInfo?firstName=" + firstName + "&&" + lastName);
        if (APIService.getPerson(firstName + lastName) != null) {
            Person person = APIService.getPerson(firstName + lastName);
            for (MedicalRecord medicalRecord : APIService.getMedicalRecordList()) {
                if (medicalRecord.getFirstNameAndLastName().equals(person.getFirstName() + person.getLastName())) {
                    PersonWithAddressAgeEmail personWithAddressAgeEMail = new PersonWithAddressAgeEmail(person.getLastName(), person.getAddress(), medicalRecord.getAge(), person.getEmail());
                    logger.info("Response of Query N°" + requestNumber + ": " + personWithAddressAgeEMail);
                    return personWithAddressAgeEMail;
                }
            }
        }
        PersonWithAddressAgeEmail personWithAddressAgeEMail = new PersonWithAddressAgeEmail();
        logger.info("Response of Query N°" + requestNumber + ": " + personWithAddressAgeEMail);
        return personWithAddressAgeEMail;
    }

    @PostMapping("/person")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PDto pDto) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "POST http://localhost:8080/person " + pDto);
        if (APIService.savePerson(pDto)) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(pDto.getFirstName() + pDto.getLastName())
                    .toUri();
            logger.info("Response of Query N°" + requestNumber + ": " + "Code 201");
            return ResponseEntity.created(location).build();
        }
        logger.info("Response of Query N°" + requestNumber + ": " + "Code 204");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/person")
    public PDto updatePerson(@RequestBody PDto pDto) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "PUT http://localhost:8080/person " + pDto);
        if (APIService.updatePerson(pDto)) {
            logger.info("Response of Query N°" + requestNumber + ": " + pDto);
            return pDto;
        }
        PDto pDto1 = new PDto();
        logger.info("Response of Query N°" + requestNumber + ": " + pDto1);
        return pDto1;
    }

    @DeleteMapping("/person")
    public PDto removePerson(@RequestBody PDto pDto) {
        int requestNumber = requestCounter.addRequest();
        logger.info("Query N°" + requestNumber + " : " + "DELETE http://localhost:8080/person " + pDto);
        if (APIService.removePerson(pDto)) {
            logger.info("Response of Query N°" + requestNumber + ": " + pDto);
            return pDto;
        }
        PDto pDto1 = new PDto();
        logger.info("Response of Query N°" + requestNumber + ": " + pDto1);
        return pDto1;
    }
}
