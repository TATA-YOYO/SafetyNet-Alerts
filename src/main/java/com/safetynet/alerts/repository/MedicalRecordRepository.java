package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {
    List<MedicalRecord> medicalRecordList = new ArrayList<>();


    @Override
    public List<MedicalRecord> getMedicalRecordList() {
        return medicalRecordList;
    }

    @Override
    public boolean saveMedicalRecord(MedicalRecord medicalRecord) {
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
        return false;
    }

    @Override
    public boolean updateMEdicalRecord(MedicalRecord medicalRecord) {
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord m = medicalRecordList.get(i);
            if (m.getFirstNameAndLastName().equals(medicalRecord.getFirstNameAndLastName())) {
                medicalRecordList.set(i, medicalRecord);
                return true;
            }
        }
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
        return false;
    }
}
