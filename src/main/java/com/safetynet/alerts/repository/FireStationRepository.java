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
        List<String> stringList = new ArrayList<>();
        for (FireStation f : fireStationList) {
            if (f.getStation() == nbStation) {
                stringList.add(f.getAddress());
            }
        }
        return stringList;
    }

    @Override
    public int getStationNumber(String address) {
        for (FireStation f : fireStationList) {
            if (address.equals(f.getAddress())) {
                return f.getStation();
            }
        }
        return 0;
    }

    @Override
    public boolean saveListOfFireStation(List<FireStation> listOfFireStation) {
        return fireStationList.addAll(listOfFireStation);
    }

    @Override
    public boolean updateFireStation(FireStation fireStation) {
        for (int i = 0; i < fireStationList.size(); i++) {
            FireStation f = fireStationList.get(i);
            if (f.getAddress().equals(fireStation.getAddress())) {
                fireStationList.set(i, fireStation);
                return true;
            }
        }
        return false;
    }
}
