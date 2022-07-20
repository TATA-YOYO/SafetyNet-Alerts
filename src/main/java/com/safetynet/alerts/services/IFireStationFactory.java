package com.safetynet.alerts.services;

import com.safetynet.alerts.controller.dto.FireStationDto;
import com.safetynet.alerts.models.FireStation;

import java.util.List;

public interface IFireStationFactory {
    List<FireStation> getListOfFireStation(List<FireStationDto> fireStationDtoList);

    FireStation getFireStation(FireStationDto fireStationDto);
}
