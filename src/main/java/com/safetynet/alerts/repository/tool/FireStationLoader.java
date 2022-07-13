package com.safetynet.alerts.repository.tool;

import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FireStationLoader implements IFireStationLoader {

    private static final Logger logger = LogManager.getLogger("FireStationLoader");

    @Autowired
    private FireStationRepository fireStationRepository;

    @Override
    public void load(JSONObject jsonObject) {
        JSONArray fireStationArray = (JSONArray) jsonObject.get("firestations");
        for (Object o : fireStationArray) {
            JSONObject fireStationJSON = (JSONObject) o;
            FireStation fireStation = new FireStation();
            fireStation.setAddress((String) fireStationJSON.get("address"));
            fireStation.setStation(Integer.parseInt((String) fireStationJSON.get("station")));

            fireStationRepository.getList().add(fireStation);
        }
    }
}
