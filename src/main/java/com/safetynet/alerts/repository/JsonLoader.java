package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonLoader implements CommandLineRunner {

    private JSONObject data;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FireStationRepository fireStationRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            data = (JSONObject) new JSONParser().parse(new FileReader("C:\\Users\\Marc-black\\data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Load all Persons
        JSONArray personArray = (JSONArray) data.get("persons");
        for (Object o : personArray) {
            JSONObject personJSON = (JSONObject) o;
            Person person = new Person();
            person.setFirstName((String) personJSON.get("firstName"));
            person.setLastName((String) personJSON.get("lastName"));
            person.setAddress((String) personJSON.get("address"));
            person.setCity((String) personJSON.get("city"));
            person.setZip((String) personJSON.get("zip"));
            person.setPhone((String) personJSON.get("phone"));
            person.setEmail((String) personJSON.get("email"));
            personRepository.getPersonList().add(person);
        }
        //Load all fire Stations
        JSONArray fireStationArray = (JSONArray) data.get("firestations");
        for (Object o : fireStationArray) {
            JSONObject fireStationJSON = (JSONObject) o;
            FireStation fireStation = new FireStation();
            fireStation.setAddress((String) fireStationJSON.get("address"));
            fireStation.setStation(Integer.parseInt((String) fireStationJSON.get("station")));
            fireStationRepository.getList().add(fireStation);
        }
        //Load all medical records
        JSONArray medicalRecordArray = (JSONArray) data.get("medicalrecords");
        for (Object o : medicalRecordArray) {
            JSONObject medicalRecordJSON = (JSONObject) o;
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setFirstName((String) medicalRecordJSON.get("firstName"));
            medicalRecord.setLastName((String) medicalRecordJSON.get("lastName"));
            medicalRecord.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse((String) medicalRecordJSON.get("birthdate")));
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
                medications.add(alrg);
            }
            medicalRecord.setAllergies(allergies);
            medicalRecordRepository.getMedicalRecordList().add(medicalRecord);
        }
        System.out.println("wait!");
    }
}