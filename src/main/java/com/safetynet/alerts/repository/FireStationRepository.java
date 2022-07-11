package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.FireStation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FireStationRepository implements IFireStationRepository {
    private List<FireStation> fireStationList = new ArrayList<>();

    @Override
    public List<FireStation> getList() {
        return fireStationList;
    }

    @Override
    public String getAddress(int nbStation) {
        String address = null;
        for (FireStation f : fireStationList) {
            if (f.getStation() == nbStation) {
                address = f.getAddress();
                break;
            }
        }
        return address;
    }
}
