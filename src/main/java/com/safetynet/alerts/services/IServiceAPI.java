package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.PersonDtoWithAddressAndPhone;
import com.safetynet.alerts.models.MedicalRecord;

import java.util.List;

public interface IServiceAPI {
    public List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(List<String> addressList);

    public List<PersonDtoWithAddressAndPhone> getPersonDtoWithAddressAndPhoneList(String address);

    public List<String> getAddress(int stationNumber);

    public List<MedicalRecord> getMedicalRecordList();

    int getStationNumber(String address);

}
