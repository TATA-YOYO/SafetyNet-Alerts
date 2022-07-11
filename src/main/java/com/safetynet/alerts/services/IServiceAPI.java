package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.PersonDto;
import com.safetynet.alerts.models.MedicalRecord;

import java.util.List;

public interface IServiceAPI {
    public List<PersonDto> getPersonToShareList(List<String> addressList);

    public List<String> getAddress(int stationNumber);

    public List<MedicalRecord> getMedicalRecordList();

}
