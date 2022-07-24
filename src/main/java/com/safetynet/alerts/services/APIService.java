package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.controller.dto.PDto;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.repository.IFireStationRepository;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.repository.IPersonRepository;
import com.safetynet.alerts.services.util.FireStationFactory;
import com.safetynet.alerts.services.util.MedicalRecordFactory;
import com.safetynet.alerts.services.util.PersonFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIService implements IAPIService {

    private static final Logger logger = LogManager.getLogger("ServiceAPI");

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IFireStationRepository fireStationRepository;

    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;


    @Override
    public List<String> getAddressList(int stationNumber) {
        return fireStationRepository.getAddress(stationNumber);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordList() {
        return medicalRecordRepository.getMedicalRecordList();
    }

    @Override
    public int getStationNumber(String address) {
        return fireStationRepository.getStationNumber(address);
    }

    @Override
    public Person getPerson(String firstNameAndLastName) {
        return personRepository.getPerson(firstNameAndLastName);
    }

    @Override
    public List<String> getEmailList(String city) {
        return personRepository.getEmailList(city);
    }

    @Override
    public boolean savePerson(PDto pDto) {
        return personRepository.savePerson(PersonFactory.getPerson(pDto));
    }

    @Override
    public boolean updatePerson(PDto pDto) {
        return personRepository.updatePerson(PersonFactory.getPerson(pDto));
    }

    @Override
    public boolean removePerson(PDto pDto) {
        return personRepository.removePerson(PersonFactory.getPerson(pDto));
    }

    @Override
    public boolean saveListOfFireStation(List<FireStationDto> fireStationDto) {
        return fireStationRepository.saveListOfFireStation(FireStationFactory.getListOfFireStation(fireStationDto));
    }

    @Override
    public boolean updateFireStation(FireStationDto fireStationDto) {
        return fireStationRepository.updateFireStation(FireStationFactory.getFireStation(fireStationDto));
    }

    @Override
    public boolean removeFireStation(Integer station) {
        return fireStationRepository.removeFireStation(station);
    }

    @Override
    public boolean saveMedicalRecord(MedicalRecordDto medicalRecordDto) {
        return medicalRecordRepository.saveMedicalRecord(MedicalRecordFactory.getMedicalRecord(medicalRecordDto));
    }

    @Override
    public boolean updateMedicalRecord(MedicalRecordDto medicalRecordDto) {
        return medicalRecordRepository.updateMedicalRecord(MedicalRecordFactory.getMedicalRecord(medicalRecordDto));
    }

    @Override
    public boolean removeMedicalRecord(MedicalRecordDto medicalRecordDto) {
        return medicalRecordRepository.removeMedicalRecord(MedicalRecordFactory.getMedicalRecord(medicalRecordDto));
    }

    @Override
    public List<Person> getPersonList() {
        return personRepository.getPersonList();
    }
}
