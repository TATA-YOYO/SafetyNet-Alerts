package com.safetynet.alerts.repository;

import com.safetynet.alerts.repository.tool.IFireStationLoader;
import com.safetynet.alerts.repository.tool.IJsonLoader;
import com.safetynet.alerts.repository.tool.IMedicalLoader;
import com.safetynet.alerts.repository.tool.IPersonLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger("DataLoader");
    @Autowired
    private IJsonLoader jsonLoader;

    @Autowired
    private IPersonLoader personLoader;

    @Autowired
    private IFireStationLoader fireStationLoader;

    @Autowired
    private IMedicalLoader medicalLoader;

    @Override
    public void run(String... args) throws Exception {
        JSONObject jsonObject = jsonLoader.load("./src/main/resources/data.json");
        personLoader.load(jsonObject);
        fireStationLoader.load(jsonObject);
        medicalLoader.load(jsonObject);
        logger.debug("All data from file are loaded.");
    }
}
