package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.models.FireStation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FireStationFactory implements IFireStationFactory {

    @Override
    public List<FireStation> getListOfFireStation(List<FireStationDto> fireStationDtoList) {
        List<FireStation> fireStationList = new ArrayList<>();
        for (FireStationDto f : fireStationDtoList) {
            FireStation fireStation = new FireStation();
            fireStation.setStation(f.getStation());
            fireStation.setAddress(f.getAddress());
            fireStationList.add(fireStation);
        }

        return fireStationList;
    }

    @Override
    public FireStation getFireStation(FireStationDto fireStationDto) {
        FireStation fireStation = new FireStation();
        fireStation.setStation(fireStationDto.getStation());
        fireStation.setAddress(fireStationDto.getAddress());
        return fireStation;
    }
}
