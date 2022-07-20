package com.safetynet.alerts.repository.tool;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.models.FireStation;

public interface IFireStationFactory {
    public FireStation getFireStation(FireStationDto fireStationDto);
}
