package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.FireStation;

import java.util.List;

public interface IFireStationRepository {
    public List<FireStation> getList();
    public List<String> getAddress(int nbStation);

    int getStationNumber(String address);
}
