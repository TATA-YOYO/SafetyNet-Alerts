package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.*;
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
import java.util.List;
import java.util.Objects;

@RestController
public class PersonController implements IPersonController {
    private static final Logger logger = LogManager.getLogger("PersonController");
    @Autowired
    private IServiceAPI serviceAPI;

    @Override
    @GetMapping("/childAlert")
    public List<PersonDtoWithAgeAndOtherMember> getPersonDtoWithAgeAndOtherMember(final String address) {
        List<PersonDtoWithAgeAndOtherMember> personDtoWithAgeAndOtherMemberList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        for (Person person : serviceAPI.getPersonList()) {
            if (person.getAddress().equals(address)) {
                personList.add(person);
            }
        }

        for (Person person : personList) {
            for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
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
        logger.info("Result " + personDtoWithAgeAndOtherMemberList);
        return personDtoWithAgeAndOtherMemberList;
    }

    @Override
    @GetMapping("/fire")
    public ListOfPersonAndTheirNumberStation getListOfPersonAndTheirNumberStation(final String address) {
        List<PersonWithLastNameAndPhoneDto> personWithLastNameAndPhoneDtoList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        for (Person person : serviceAPI.getPersonList()) {
            if (person.getAddress().equals(address)) {
                personList.add(person);
            }
        }
        for (Person person : personList) {
            for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
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
        return new ListOfPersonAndTheirNumberStation(personWithLastNameAndPhoneDtoList, serviceAPI.getStationNumber(address));
    }

    @Override
    @GetMapping("/personInfo")
    public PersonWithAddressAgeEMail getPersonWithAddressAgeEMail(String firstName, String lastName) {
        if (serviceAPI.getPerson(firstName + lastName) != null) {
            Person person = serviceAPI.getPerson(firstName + lastName);
            for (MedicalRecord medicalRecord : serviceAPI.getMedicalRecordList()) {
                if (medicalRecord.getFirstNameAndLastName().equals(person.getFirstName() + person.getLastName())) {
                    return new PersonWithAddressAgeEMail(person.getLastName(), person.getAddress(), medicalRecord.getAge(), person.getEmail());
                }
            }
        }
        return new PersonWithAddressAgeEMail();
    }

    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        if (serviceAPI.savePerson(person)) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(person.getFirstName() + person.getLastName())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/person")
    public Person updatePerson(@RequestBody Person person) {
        if (serviceAPI.updatePerson(person)) {
            return person;
        }
        return person;
    }

    @DeleteMapping("/person")
    public Person removePerson(@RequestBody Person person) {
        if (serviceAPI.removePerson(person)) {
            return person;
        }
        return person;
    }
}
