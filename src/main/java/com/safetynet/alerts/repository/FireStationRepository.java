package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.FireStation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FireStationRepository {
    private List<FireStation> fireStationList = new ArrayList<>();

    public List<FireStation> getList() {
        return fireStationList;
    }
}
