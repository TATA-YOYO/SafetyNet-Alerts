package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.repository.IFireStationRepository;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAPI implements IServiceAPI {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IFireStationRepository fireStationRepository;

    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;

    @Override
    public List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(List<String> addressList) {
        return personRepository.getPersonDtoWithAddressAndPhoneList(addressList);
    }

    @Override
    public List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(String address) {
        return personRepository.getPersonDtoWithAddressAndPhoneList(address);
    }

    @Override
    public List<String> getAddress(int stationNumber) {
        return fireStationRepository.getAddress(stationNumber);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordList() {
        return medicalRecordRepository.getMedicalRecordList();
    }
}
