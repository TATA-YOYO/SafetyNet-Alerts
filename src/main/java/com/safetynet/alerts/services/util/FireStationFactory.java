package com.safetynet.alerts.services.util;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.models.FireStation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class FireStationFactory  {

    public static List<FireStation> getListOfFireStation(List<FireStationDto> fireStationDtoList) {
        List<FireStation> fireStationList = new ArrayList<>();
        for (FireStationDto f : fireStationDtoList) {
            FireStation fireStation = new FireStation();
            fireStation.setStation(f.getStation());
            fireStation.setAddress(f.getAddress());
            fireStationList.add(fireStation);
        }

        return fireStationList;
    }

    public static FireStation getFireStation(FireStationDto fireStationDto) {
        FireStation fireStation = new FireStation();
        fireStation.setStation(fireStationDto.getStation());
        fireStation.setAddress(fireStationDto.getAddress());
        return fireStation;
    }
}
