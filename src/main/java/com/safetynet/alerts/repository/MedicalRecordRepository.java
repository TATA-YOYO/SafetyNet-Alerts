package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {
    List<MedicalRecord> medicalRecordList = new ArrayList<>();

    @Override
    public String toString() {
        return "MedicalRecordRepository{" +
                "medicalRecordList=" + medicalRecordList +
                '}';
    }

    public List<MedicalRecord> getMedicalRecordList() {
        return medicalRecordList;
    }
}
