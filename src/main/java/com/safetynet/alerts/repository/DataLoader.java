package com.safetynet.alerts.repository;

import com.safetynet.alerts.repository.tool.IFireStationLoader;
import com.safetynet.alerts.repository.tool.IJsonLoader;
import com.safetynet.alerts.repository.tool.IMedicalLoader;
import com.safetynet.alerts.repository.tool.IPersonLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements IDataLoader {
    private static final Logger logger = LogManager.getLogger("DataLoader");
    @Autowired
    private IJsonLoader jsonLoader;

    @Autowired
    private IPersonLoader personLoader;

    @Autowired
    private IFireStationLoader fireStationLoader;

    @Autowired
    private IMedicalLoader medicalLoader;

    public void load() throws Exception {
        JSONObject jsonObject = jsonLoader.load("C:\\Users\\Marc-black\\data.json");
        personLoader.load(jsonObject);
        fireStationLoader.load(jsonObject);
        medicalLoader.load(jsonObject);
        logger.info("All data from file are loaded in system.");
    }
}
