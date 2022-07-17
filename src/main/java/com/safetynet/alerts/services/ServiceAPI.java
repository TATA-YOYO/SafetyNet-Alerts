package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.repository.IDataLoader;
import com.safetynet.alerts.repository.IFireStationRepository;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.repository.IPersonRepository;
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
    public List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(List<String> addressList) {
        if (dataIsLoaded) {
            return personRepository.getPersonDtoWithAddressAndPhoneList(addressList);
        }
        try {
            dataLoader.load();
            dataIsLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personRepository.getPersonDtoWithAddressAndPhoneList(addressList);
    }

    @Override
    public List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(String address) {
        if (dataIsLoaded) {
            return personRepository.getPersonDtoWithAddressAndPhoneList(address);
        }
        try {
            dataLoader.load();
            dataIsLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personRepository.getPersonDtoWithAddressAndPhoneList(address);
    }

    @Override
    public List<String> getAddressList(int stationNumber) {
        if (dataIsLoaded) {
            return fireStationRepository.getAddress(stationNumber);
        }
        try {
            dataLoader.load();
            dataIsLoaded = true;
        } catch (Exception e) {
            logger.error("All Data are not loaded");
            e.printStackTrace();
        }
        return fireStationRepository.getAddress(stationNumber);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordList() {
        if (dataIsLoaded) {
            return medicalRecordRepository.getMedicalRecordList();
        }
        try {
            dataLoader.load();
            dataIsLoaded = true;
        } catch (Exception e) {
            logger.error("All Data are not loaded");
            e.printStackTrace();
        }
        return medicalRecordRepository.getMedicalRecordList();
    }

    @Override
    public int getStationNumber(String address) {
        if (dataIsLoaded) {
            return fireStationRepository.getStationNumber(address);
        }
        try {
            dataLoader.load();
            dataIsLoaded = true;
        } catch (Exception e) {
            logger.error("All Data are not loaded");
            e.printStackTrace();
        }
        return fireStationRepository.getStationNumber(address);
    }

    @Override
    public Person getPerson(String firstNameAndLastName) {
        if (dataIsLoaded) {
            return personRepository.getPerson(firstNameAndLastName);
        }
        try {
            dataLoader.load();
            dataIsLoaded = true;
        } catch (Exception e) {
            logger.error("All Data are not loaded");
            e.printStackTrace();
        }

        return personRepository.getPerson(firstNameAndLastName);
    }
}
