package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.controller.dto.MedicalRecordDto;
import com.safetynet.alerts.controller.dto.PDto;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.repository.IDataLoader;
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
public class ServiceAPI implements IServiceAPI {

    private static final Logger logger = LogManager.getLogger("ServiceAPI");

    boolean dataIsLoaded = false;

    @Autowired
    private IDataLoader dataLoader;

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IFireStationRepository fireStationRepository;

    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;


    @Override
    public List<String> getAddressList(int stationNumber) {
        loadDataIfNeeded();
        return fireStationRepository.getAddress(stationNumber);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordList() {
        loadDataIfNeeded();
        return medicalRecordRepository.getMedicalRecordList();
    }

    @Override
    public int getStationNumber(String address) {
        loadDataIfNeeded();
        return fireStationRepository.getStationNumber(address);
    }

    @Override
    public Person getPerson(String firstNameAndLastName) {
        loadDataIfNeeded();
        return personRepository.getPerson(firstNameAndLastName);
    }

    @Override
    public List<String> getEmailList(String city) {
        loadDataIfNeeded();
        return personRepository.getEmailList(city);
    }

    @Override
    public boolean savePerson(PDto pDto) {
        loadDataIfNeeded();
        return personRepository.savePerson(PersonFactory.getPerson(pDto));
    }

    @Override
    public boolean updatePerson(PDto pDto) {
        loadDataIfNeeded();
        return personRepository.updatePerson(PersonFactory.getPerson(pDto));
    }

    @Override
    public boolean removePerson(PDto pDto) {
        loadDataIfNeeded();
        return personRepository.removePerson(PersonFactory.getPerson(pDto));
    }

    @Override
    public boolean saveListOfFireStation(List<FireStationDto> fireStationDto) {
        loadDataIfNeeded();
        return fireStationRepository.saveListOfFireStation(FireStationFactory.getListOfFireStation(fireStationDto));
    }

    @Override
    public boolean updateFireStation(FireStationDto fireStationDto) {
        loadDataIfNeeded();
        return fireStationRepository.updateFireStation(FireStationFactory.getFireStation(fireStationDto));
    }


    @Override
    public boolean removeFireStation(Integer station) {
        loadDataIfNeeded();
        return fireStationRepository.removeFireStation(station);
    }

    @Override
    public boolean saveMedicalRecord(MedicalRecordDto medicalRecordDto) {
        loadDataIfNeeded();
        return medicalRecordRepository.saveMedicalRecord(MedicalRecordFactory.getMedicalRecord(medicalRecordDto));
    }

    @Override
    public boolean updateMedicalRecord(MedicalRecordDto medicalRecordDto) {
        loadDataIfNeeded();
        return medicalRecordRepository.updateMedicalRecord(MedicalRecordFactory.getMedicalRecord(medicalRecordDto));
    }

    @Override
    public boolean removeMedicalRecord(MedicalRecordDto medicalRecordDto) {
        loadDataIfNeeded();
        return medicalRecordRepository.removeMedicalRecord(MedicalRecordFactory.getMedicalRecord(medicalRecordDto));
    }

    @Override
    public List<Person> getPersonList() {
        loadDataIfNeeded();
        return personRepository.getPersonList();
    }


    private void loadDataIfNeeded() {
        if (!dataIsLoaded) {
            try {
                dataLoader.load();
                dataIsLoaded = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
