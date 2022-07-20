package com.safetynet.alerts.repository.tool;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.models.FireStation;
import org.springframework.stereotype.Component;

@Component
public class FireStationFactory implements IFireStationFactory{

    public FireStation getFireStation(FireStationDto fireStationDto) {
        FireStation fireStation = new FireStation();
        fireStation.setStation(fireStationDto.getStation());
        fireStation.setAddress(fireStationDto.getAddress());
        return fireStation;
    }
}
