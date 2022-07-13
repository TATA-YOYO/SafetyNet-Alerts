package com.safetynet.alerts.repository.tool;

import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
public class JsonLoader implements IJsonLoader {
    private static final Logger logger = LogManager.getLogger("JsonLoader");

    private JSONObject data;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FireStationRepository fireStationRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public JSONObject load(String filePath) throws ParseException {
        try {
            data = (JSONObject) new JSONParser().parse(new FileReader(filePath));
        } catch (IOException e) {
            logger.error("Data file cannot be read");
            e.printStackTrace();
        }
        return data;
    }
}