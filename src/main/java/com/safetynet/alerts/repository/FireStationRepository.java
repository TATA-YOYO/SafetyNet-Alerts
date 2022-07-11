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
    public List<String> getAddress(int nbStation) {
        List<String> stringList= new ArrayList<>();
        for (FireStation f : fireStationList) {
            if (f.getStation() == nbStation) {
               stringList.add(f.getAddress());
            }
        }
        return stringList;
    }
}
