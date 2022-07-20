package com.safetynet.alerts.repository;

import com.safetynet.alerts.controller.dto.MedicalRecordDto;
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
}
