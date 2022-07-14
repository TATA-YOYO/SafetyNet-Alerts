package com.safetynet.alerts.repository.tool;

import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Component
public class MedicalLoader implements IMedicalLoader {

    private static final Logger logger = LogManager.getLogger("MedicalLoader");

    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;

    @Override
    public void load(JSONObject jsonObject) {
        JSONArray medicalRecordArray = (JSONArray) jsonObject.get("medicalrecords");
        for (Object o : medicalRecordArray) {
            JSONObject medicalRecordJSON = (JSONObject) o;
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setFirstName((String) medicalRecordJSON.get("firstName"));
            medicalRecord.setLastName((String) medicalRecordJSON.get("lastName"));
            medicalRecord.concatenate();
            try {
                medicalRecord.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse((String) medicalRecordJSON.get("birthdate")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<String> medications = new ArrayList<>();
            JSONArray medicationsArray = (JSONArray) medicalRecordJSON.get("medications");
            for (Object I : medicationsArray) {
                String mdct = (String) I;
                medications.add(mdct);
            }
            medicalRecord.setMedications(medications);
            List<String> allergies = new ArrayList<>();
            JSONArray allergiesArray = (JSONArray) medicalRecordJSON.get("allergies");
            for (Object I : allergiesArray) {
                String alrg = (String) I;
                allergies.add(alrg);
            }
            medicalRecord.setAllergies(allergies);
            medicalRecordRepository.getMedicalRecordList().add(medicalRecord);
        }
    }
}
