package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.MedicalRecord;

import java.util.List;

public interface IMedicalRecordRepository {
    public String toString();

    public List<MedicalRecord> getMedicalRecordList();

    boolean saveMedicalRecord(MedicalRecord medicalRecord);

    boolean updateMedicalRecord(MedicalRecord medicalRecord);

    boolean removeMedicalRecord(MedicalRecord medicalRecord);
}
