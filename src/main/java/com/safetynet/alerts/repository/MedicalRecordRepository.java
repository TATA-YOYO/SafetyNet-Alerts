package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {
    private static final Logger logger = LogManager.getLogger("MedicalRecordRepository");
   private final List<MedicalRecord> medicalRecordList = new ArrayList<>();


    @Override
    public List<MedicalRecord> getMedicalRecordList() {
        return medicalRecordList;
    }

    @Override
    public boolean saveMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord.getLastName()==null|| medicalRecord.getLastName().isEmpty()
                ||medicalRecord.getFirstName()==null||medicalRecord.getFirstName().isEmpty()
                ||medicalRecord.getAge()==0) {
            logger.debug("this object contains empty or a null value");
            return false;
        }
        int age = medicalRecord.getAge();
        boolean isPresent = false;
        for (MedicalRecord m : medicalRecordList) {
            if (m.getFirstNameAndLastName().equals(medicalRecord.getFirstNameAndLastName())) {
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            medicalRecordList.add(medicalRecord);
            return true;
        }
        logger.debug("this object("+medicalRecord+") cannot be saved because is already saved");
        return false;
    }

    @Override
    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord m = medicalRecordList.get(i);
            if (m.getFirstNameAndLastName().equals(medicalRecord.getFirstNameAndLastName())) {
                medicalRecordList.set(i, medicalRecord);
                return true;
            }
        }
        logger.debug("this object("+medicalRecord+") is unknown");
        return false;
    }

    @Override
    public boolean removeMedicalRecord(MedicalRecord medicalRecord) {
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord m = medicalRecordList.get(i);
            if (m.getFirstNameAndLastName().equals(medicalRecord.getFirstNameAndLastName())) {
                medicalRecordList.remove(i);
                return true;
            }
        }
        logger.debug("this object("+medicalRecord+") is unknown");
        return false;
    }
}
